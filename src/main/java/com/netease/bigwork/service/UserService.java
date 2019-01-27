package com.netease.bigwork.service;

import com.netease.bigwork.pojo.BuyerGoods;
import com.netease.bigwork.pojo.Goods;
import com.netease.bigwork.pojo.User;

import java.util.List;


public interface UserService {
    public boolean check(User user);//检查用户密码
    public List<Goods> getAllGoods();//获得首页所有商品
    public int updateGoods(Goods goods);//编辑商品
    public List<Goods> getAllById(int userId);//
    public Goods getItemById(int id);//根据商品id得商品
    public int deleteItemById(int id);
    public int insertGoods(Goods good);
    public List<Goods> getBuyGoods();
    public List<Goods> getNotBuyGoods();
    public int insertBuyerGoods(BuyerGoods buyerGoods);
    public int updateBuyAndSell(int gid,boolean buy, boolean sell);
    public int getIdByName(String name);
    public List<BuyerGoods> getBuyGoodsBy(int uid);
    public int getIdByTitle(String title);
    public String getImageByGid(int gid);
    public int updateSellCountByGid(int gid,int sellCount);

    BuyerGoods getBuyGoodsByGid(int id);
}
