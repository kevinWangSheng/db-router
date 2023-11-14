package com.kevin.db.router;

/**
 * @description: 数据源基础配置
 * @author: kevin
 * @date: 2021/9/22
 */
public class DBRouterBase {

    private String tbIdx;

    public String getTbIdx() {
        return DBContextHolder.getTBKey();
    }

}
