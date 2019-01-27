package com.netease.bigwork.mapper;

import com.netease.bigwork.pojo.BuyerGoods;
import com.netease.bigwork.pojo.Goods;
import com.netease.bigwork.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {
    public User getUserByName(String username);


    public List<Goods> getAllById(int userId);
    public List<Goods> getAllGoods();
    public List<Goods> getNotBuyGoods();


    public int getIdByName(String name);
    public Goods getItemById(int gid);
    public int updateGoods(Goods goods);
    public int deleteItemById(int id);
    public int insertGoods(Goods good);
    public int getIdByTitle(String title);
    public String getImageByGid(int gid);



    /**********买家***********/
    public int updateBuyerItem(@Param("gid")int id,@Param("buyCount")int number);
    public int insertBuyGoods(BuyerGoods buyergoods);
    public List<BuyerGoods> getBuyGoodsBy(int uid);

    public int updateBuyAndSell(@Param("gid")int gid,@Param("buy") boolean buy,@Param("sell")boolean sell);
    public int updateSellCountByGid(@Param("gid")int gid,@Param("sellCount")int sellCount);
    public int getSellCountByGid(int gid);

    BuyerGoods getBuyGoodsByGid(int gid);
}
