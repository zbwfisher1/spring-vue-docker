package com.zbwfisher.datasource.example.dao.impl;




import com.zbwfisher.datasource.common.sqlTools.SqlUtil;
import com.zbwfisher.datasource.example.dao.ITestDAO;
import com.zbwfisher.datasource.druid.DynamicDataSource.TargetDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhong on 2016/9/5.
 */
@Repository("testDAO")
public class TestDAOImpl implements ITestDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SqlUtil sqlUtil;

    @Override
    public void testMaster() {
        List<Map<String,Object>> list = new ArrayList<>();
        Connection con1 = DataSourceUtils.getConnection(dataSource);
        Statement stmt = null;
        try {
            ResultSet rs = null;
            stmt = con1.createStatement();
            rs = stmt.executeQuery("select * from test");

            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (rs.next()) {
                Map map = new HashMap();
                for (int i = 1; i <= columnCount; i++) {
                    String columnTypeName = resultSetMetaData.getColumnTypeName(i).toLowerCase();
                    String columnName = resultSetMetaData.getColumnName(i).toLowerCase();
                    Object obj = null;
                    if (columnTypeName.equals("clob")) {
                        Clob cb = rs.getClob(columnName);
                        Reader r = cb.getCharacterStream();
                        char[] c = new char[(int) cb.length()];
                        r.read(c);
                        obj = new String(c);
                    } else if (columnTypeName.equals("date")) {
                        obj = rs.getTimestamp(columnName);
                    } else {
                        obj = rs.getObject(columnName);
                    }
                    map.put(columnName, obj);
                }
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        System.out.println(list);
    }
    @TargetDataSource(name="slave1")
    @Override
    public void testSlave1() {
        List<Map> list  = null;
        try {
            list = sqlUtil.queryBySql("select * from test");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list);
    }



    @TargetDataSource(name="slave2")
    @Override
    public void testSlave2() {
        List<Map<String,Object>> list = this.jdbcTemplate.queryForList("select * from test");
        System.out.println(list);
    }

    @Override
    public void testQuery() {
        try {
            Thread.sleep(5000);

            List<Map<String,Object>> list = this.jdbcTemplate.queryForList("select * from test");
            System.out.println(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void testQuery2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> list = this.jdbcTemplate.queryForList("select * from test");
        System.out.println(list);
    }
}
