package com.learn.plugindemo.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author callor
 * @version 1.0
 * @date 2018/6/20
 */
@Intercepts({@Signature(type = StatementHandler.class // 确定要拦截的对象
        ,method = "prepare" // 确定要拦截的方法
        ,args = {Connection.class})}) // 确定要拦截的参数
public class QueryLimitPlugin implements Interceptor {
    private int limit;
    private String dbType;
    private static final String LMT_TABLE_NAME = "tt";
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 取出要拦截的对象
        StatementHandler statementHandler =(StatementHandler)invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        // 分离代理对象，从而形成多次代理，通过2次循环最原始的被代理类
        while (metaObject.hasGetter("h")){
            Object object =metaObject.getValue("h");
            metaObject = SystemMetaObject.forObject(object);
        }
        // 分离最后一个代理对象的目标
        while (metaObject.hasGetter("target")){
            Object object =metaObject.getValue("target");
            metaObject = SystemMetaObject.forObject(object);
        }
        // 取出即将要执行的sql
        //String sql=metaObject.getGetterNames()
        return null;
    }

    @Override
    public Object plugin(Object o) {

        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
