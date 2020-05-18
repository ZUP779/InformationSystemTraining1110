package com.example.esdemo.service;

import com.example.esdemo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Author: ZUP779
 * Date:   2020/5/16 20:29
 * Description:
 */
@Service
public class HotSearchService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    private final String hotSearchZSET = "esdemo:hotSearch";

    //热搜统计天数
    private final int passDays = 1;

    private final String dateFormat="yyyyMMdd";

    public Set<String> getHotSearch(long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(hotSearchZSET+getCurrentDayString(), start, end);
    }

    public void searchAdd2HostSearch(String key) {
        redisTemplate.opsForZSet().incrementScore(hotSearchZSET+getCurrentDayString(), key, 1);

        //经过passDays天后键过期
        redisTemplate.expire(hotSearchZSET+getCurrentDayString(), passDays, TimeUnit.DAYS);
    }

    private String getCurrentDayString(){
        Date date = new Date();
        return DateUtil.dateToString(date, dateFormat);
    }

//    private List<String> getPassDaysString(){
//        ArrayList<String> dates = new ArrayList<>();
//        Date date = new Date();
//        for( int i = 0; i < passDays; i ++){
//            Date temp = DateUtil.dateIncreaseByDay(date, -i);
//            dates.add(DateUtil.dateToString(temp, dateFormat));
//        }
//        return dates;
//    }
}
