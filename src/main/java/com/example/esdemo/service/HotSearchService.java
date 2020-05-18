package com.example.esdemo.service;

import com.example.esdemo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
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
    private final int passDays = 3;

    private final String dateFormat="yyyyMMdd";

    public Set<String> getHotSearchFilmTitle(long start, long end) {
        List<String> dates = getPassDaysString();

        //使用一个随机的UUID做为redis生成的临时ZSET并集名称，防止并发下数据冲突
        String destKey = UUID.randomUUID().toString();
        redisTemplate.opsForZSet().unionAndStore(hotSearchZSET+getCurrentDayString(), dates, destKey);
        Set<String> set = redisTemplate.opsForZSet().reverseRange(destKey, start, end);
//        System.out.println(destKey);
        redisTemplate.delete(destKey);
        return set;
    }

    public void searchAdd2HostSearchFilmTitle(String key) {
        redisTemplate.opsForZSet().incrementScore(hotSearchZSET+getCurrentDayString(), key, 1);

        //经过passDays天后键过期
        redisTemplate.expire(hotSearchZSET+getCurrentDayString(), passDays, TimeUnit.DAYS);
    }

    private String getCurrentDayString(){
        Date date = new Date();
        return DateUtil.dateToString(date, dateFormat);
    }

    //返回当前时间倒退passDays天内每天的时间格式化字符串，不包括当前天
    private List<String> getPassDaysString(){
        ArrayList<String> dates = new ArrayList<>();
        Date date = new Date();
        for( int i = 1; i < passDays; i ++){
            Date temp = DateUtil.dateIncreaseByDay(date, -i);
            dates.add(hotSearchZSET + DateUtil.dateToString(temp, dateFormat));
        }
        return dates;
    }
}
