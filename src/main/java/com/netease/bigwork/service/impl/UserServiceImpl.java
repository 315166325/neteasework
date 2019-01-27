package com.netease.bigwork.service.impl;

import com.netease.bigwork.Model.HostHolder;
import com.netease.bigwork.mapper.UserMapper;
import com.netease.bigwork.pojo.BuyerGoods;
import com.netease.bigwork.pojo.Goods;
import com.netease.bigwork.pojo.User;
import com.netease.bigwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;
    @Autowired
    HostHolder hostHolder;

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public boolean check(User user) {
        if(user != null){
            User user1 = userMapper.getUserByName(user.getUsername());
            if(user1 == null){
                return false;
            }else{
                if(user.getUsername().equals(user1.getUsername())&&user.getPassword().equals(user1.getPassword())){
                    return true;
                }else{
                    return false;
                }
            }
        }else{
            return false;
        }

    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public Goods getItemById(int id) {
        return userMapper.getItemById(id);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public List<Goods> getAllGoods() {
       return userMapper.getAllGoods();
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int updateGoods(Goods goods) {
        return userMapper.updateGoods(goods);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public List<Goods> getAllById(int userId) {
        return userMapper.getAllById(userId);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int deleteItemById(int id) {
        userMapper.deleteItemById(id);
        return 0;
    }

    @Override
    public int insertGoods(Goods good) {
        return userMapper.insertGoods(good);
    }

    @Override
    public List<Goods> getBuyGoods() {
        return null;
    }

//    @Transactional(propagation= Propagation.REQUIRED)
//    @Override
//    public List<Goods> getBuyGoods() {
//        return userMapper.getBuyGoods();
//    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public List<Goods> getNotBuyGoods() {
        return userMapper.getNotBuyGoods();
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int insertBuyerGoods(BuyerGoods buyerGoods) {
        int uid = hostHolder.getUser().getId();
        userMapper.insertBuyGoods(buyerGoods);//插入账单的表（财务的表）
        int gid = buyerGoods.getGid();
        userMapper.updateBuyAndSell(gid,true,true);
        int count = userMapper.getSellCountByGid(gid);//已卖出的数目
        return  userMapper.updateSellCountByGid(gid,buyerGoods.getNum()+count);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int updateBuyAndSell(int gid,boolean buy, boolean sell) {
        return userMapper.updateBuyAndSell(gid,buy,sell);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int getIdByName(String name) {
        return userMapper.getIdByName(name);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public List<BuyerGoods> getBuyGoodsBy(int uid) {
        return userMapper.getBuyGoodsBy(uid);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int getIdByTitle(String title) {
        return userMapper.getIdByTitle(title);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public String getImageByGid(int gid) {
        return userMapper.getImageByGid(gid);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int updateSellCountByGid(int gid, int sellCount) {
        int count = userMapper.getSellCountByGid(gid);
        return  userMapper.updateSellCountByGid(gid,sellCount+count);
    }

    @Override
    public BuyerGoods getBuyGoodsByGid(int gid) {
        return userMapper.getBuyGoodsByGid(gid);
    }
}
