package com.netease.bigwork.service;

import com.netease.bigwork.pojo.BuyerGoods;
import com.netease.bigwork.pojo.Goods;
import com.netease.bigwork.pojo.User;

import java.util.List;


public interface UserService {
    public boolean check(User user);//����û�����
    public List<Goods> getAllGoods();//�����ҳ������Ʒ
    public int updateGoods(Goods goods);//�༭��Ʒ
    public List<Goods> getAllById(int userId);//
    public Goods getItemById(int id);//������Ʒid����Ʒ
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
