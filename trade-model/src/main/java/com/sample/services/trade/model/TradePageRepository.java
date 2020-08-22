package com.sample.services.trade.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TradePageRepository extends PagingAndSortingRepository<TradeEntity, Key> {
    List<Long> findKeyTradeIdByMaturityDtLessThan(Date date);
}
