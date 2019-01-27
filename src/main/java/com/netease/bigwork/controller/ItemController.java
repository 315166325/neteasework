package com.netease.bigwork.controller;

import com.netease.bigwork.Model.HostHolder;
import com.netease.bigwork.pojo.*;
import com.netease.bigwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ItemController {

    @Autowired
    UserService userService;
    @Autowired
    HostHolder hostHolder;

    @RequestMapping("/seller/publish")
    String publish(Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
       return "publish";
    }

    @ResponseBody
    @RequestMapping("/seller/upload")
    public Result picUpLoad(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result = new Result();
        if (file != null) {// 判断上传的文件是否为空
            String path = null;// 文件路径
            String type = null;// 文件类型
            String fileName = file.getOriginalFilename();// 文件原名称
            // 判断文件类型
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/image/");
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                    path = realPath + System.getProperty("file.separator") + trueFileName;
                    file.transferTo(new File(path));
                    result.setResult("/image/" + trueFileName);
                    return result;
                }
            }
        }
        response.setStatus(202);//出现错误的文件格式
        return result;
    }

    @RequestMapping(value = "/seller/publicSubmit", method = RequestMethod.POST)
    public String itemMessage(Goods goods, Model model) {//SellerItem sellerItem
        Date date1 = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String buyTime = format1.format(date1.getTime());

        Date date2 = new Date();
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sellTime = format2.format(date1.getTime());

        goods.setBuyPrice(0);
        goods.setBuyTime(buyTime);
        goods.setTime(sellTime);
        goods.setTotalCount(1000);
        goods.setTotalCount(0);
        goods.setBuyCount(0);
        goods.setSell(false);
        goods.setBuy(false);
        try {
            userService.insertGoods(goods);
            model.addAttribute("flag", "success");
        } catch (java.lang.Exception e) {
            model.addAttribute("flag", "error");
            System.out.println(e.getMessage());
        }
        model.addAttribute("id", userService.getIdByTitle(goods.getTitle()));
        return "publicSubmit";
    }

    @RequestMapping("/show")
    public String sellerShow(@RequestParam("id") int id, Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Goods sellerItem = userService.getItemById(id);
        boolean isSelled = sellerItem.isSell();
        if (sellerItem != null){
            model.addAttribute("sellerItem", sellerItem);
            model.addAttribute("isSelled",isSelled);//是否已卖出
            if (isSelled){
                BuyerGoods bg = userService.getBuyGoodsByGid(id);
                model.addAttribute("buyPrice",bg.getBuyPrice());
            }
            return "sellerShow";
        }else
            return "error";

    }

    @RequestMapping("/seller/edit")
    public String edit(@RequestParam("id") int id, Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            Goods sellerItem = userService.getItemById(id);
            model.addAttribute("sellerItem", sellerItem);
            model.addAttribute("flag", "success");
        } catch (java.lang.Exception e) {
            model.addAttribute("flag", "error");
        }
        return "edit";
    }

    @RequestMapping("/seller/editSubmit")
    public String editSubmit(Goods goods, Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            Date ss = new Date();
            SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format0.format(ss.getTime());
            goods.setTime(time);
            int gid = goods.getGid();
            String image = userService.getImageByGid(gid);
            userService.updateGoods(goods);
            String realPath = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/");
            image = realPath + System.getProperty("file.separator") + image;
            try {
                if (image != null) {
                    File f = new File(image);
                    f.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            int id = goods.getGid();
            model.addAttribute("id", id);
            model.addAttribute("flag", "success");
        } catch (java.lang.Exception e) {
            model.addAttribute("flag", "error");
        }
        return "editSubmit";
    }

    @RequestMapping("/seller/delete")
    public String deleteItem(Model model, @RequestParam("id") int id, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            String image = userService.getImageByGid(id);
            userService.deleteItemById(id);
            model.addAttribute("flag", "success");
            String realPath = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/");
            image = realPath + System.getProperty("file.separator") + image;
            if (image != null) {
                File f = new File(image);
                f.delete();
            }
        } catch (java.lang.Exception e) {
            model.addAttribute("flag", "error");
        }
        return "deleteSubmit";
    }

    //将cookie中的购物车信息搞出来
    @RequestMapping("/cart")
    public String cart(Model model,HttpServletRequest request, HttpServletResponse response) {
        String name = "products";
        Cookie[] cookies = request.getCookies();
        for (Cookie c:cookies){
            if (c.getName().equals(name)){
                System.out.println(c.getValue());
            }
        }
        return "cart";
    }

    @RequestMapping("/buy")
    @ResponseBody
    public Map buy(@RequestBody List<CartItem> data, HttpServletRequest request, HttpServletResponse response) {
        Map responseText = new HashMap();
        try {
            for (int i = 0; i < data.size(); i++) {
                BuyerGoods buyerGoods = new BuyerGoods();
                buyerGoods.setGid(Integer.valueOf(data.get(i).getGid()));
                buyerGoods.setNum(Integer.valueOf(data.get(i).getNumber()));
                //依据数据库中的价格售卖
                Goods goods = userService.getItemById(Integer.parseInt(data.get(i).getGid()));
                buyerGoods.setBuyPrice(goods.getSellPrice());
                Date date3 = new Date();
                SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = format2.format(date3.getTime());
                buyerGoods.setTime(time);
                userService.insertBuyerGoods(buyerGoods);
            }
            responseText.put("code", 200);
        } catch (Exception e) {
            responseText.put("message", "数据格式错误");
        }
        return responseText;
    }

    //购物车读取,从数据库刷新购物车内商品信息。因为商品会被修改。
    @RequestMapping("/cart2")
    @ResponseBody
    public Map cart2(@RequestBody List<CartItem> cartItems, HttpServletRequest request, HttpServletResponse response) {
        Map responseText = new HashMap();
        List<CartItem> ctList = new ArrayList<>();
        for (CartItem d : cartItems){
            Goods goods = userService.getItemById(Integer.parseInt(d.getGid()));
            if (goods == null){
                continue;
            }
            CartItem ct = new CartItem();
            ct.setBuyPrice(String.valueOf(goods.getSellPrice()));
            ct.setGid(String.valueOf(goods.getGid()));
            ct.setImage(goods.getImage());
            ct.setNumber(d.getNumber());
            ct.setTitle(goods.getTitle());
            ctList.add(ct);
        }
        responseText.put("code", 200);
        responseText.put("cart", ctList);
        return responseText;
    }

    //财务
    @RequestMapping("/account")
    public String account(Model model, HttpServletRequest request) {
        int uid = hostHolder.getUser().getId();
        List<BuyerGoods> buyerGoods = userService.getBuyGoodsBy(uid);
        List<Goods> result = new ArrayList<>();
        float total = 0,price = 0;
        for (BuyerGoods bg:buyerGoods){
            Goods goods = userService.getItemById(bg.getGid());
            int num = bg.getNum();
            goods.setBuyCount(num);
            price = bg.getBuyPrice();
            total += num * price;
            goods.setBuyPrice(price);
            goods.setBuyTime(bg.getTime());
            goods.setSell(true);
            goods.setBuy(true);
            result.add(goods);
        }
        model.addAttribute("result", result);
        model.addAttribute("total", total);
        return "account";
    }

    @RequestMapping("/notBuy")
    String indexNotBuy(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("userName", "");
        List<Goods> list = new ArrayList<>();
        model.addAttribute("showType", "not");//也可以放在前端设置这个标志
        list = userService.getNotBuyGoods();//由于只有一个买家，所以不需要传入用户
        model.addAttribute("list", list);
        return "index2";
    }

    @RequestMapping("/error")
    String error(HttpServletRequest request, HttpServletResponse response) {
        return "error";
    }
}
