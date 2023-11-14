package com.kevin.db.router.dynamic;


import com.kevin.db.router.DBContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @description: 动态数据源获取，每当切换数据源，都要从这个里面进行获取
 * @author: kevin
 * @date: 2023/9/22
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return "db" + DBContextHolder.getDBKey();
    }

}
