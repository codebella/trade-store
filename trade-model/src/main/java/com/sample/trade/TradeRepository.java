package com.sample.trade;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TradeRepository extends CrudRepository<TradeEntity, Key> {

    TradeEntity findFirstByKeyTradeIdOrderByKeyVersionDesc(String tradeId);

    List<TradeEntity> findByKeyTradeId(String tradeId);

    List<TradeEntity> findByKeyTradeIdAndKeyVersionGreaterThan(String tradeId, Long version);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Trade t SET t.expired = 'Y' WHERE t.maturityDt < :businessDt")
    Integer mature(@Param("businessDt") Date businessDt);
}
