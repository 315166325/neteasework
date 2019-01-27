package com.netease.work.mapper;

import com.netease.work.pojo.BuyerGoods;
import com.netease.work.pojo.Goods;
import com.netease.work.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {
    User getUserByName(String username);

    List<Goods> getAllGoods();

    List<Goods> getNotBuyGoods();


    int getIdByName(String name);

    Goods getItemById(int gid);

    int updateGoods(Goods goods);

    int deleteItemById(int id);

    int insertGoods(Goods good);

    int getIdByTitle(String title);

    String getImageByGid(int gid);

    int insertBuyGoods(BuyerGoods buyergoods);

    List<BuyerGoods> getBuyGoodsBy(int uid);

    int updateBuyAndSell(@Param("gid") int gid, @Param("buy") boolean buy, @Param("sell") boolean sell);

    int updateSellCountByGid(@Param("gid") int gid, @Param("sellCount") int sellCount);

    int getSellCountByGid(int gid);

    BuyerGoods getBuyGoodsByGid(int gid);
}
