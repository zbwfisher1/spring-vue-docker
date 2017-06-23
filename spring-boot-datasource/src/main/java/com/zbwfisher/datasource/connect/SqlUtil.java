package com.zbwfisher.datasource.connect;

import javax.sql.DataSource;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zbw on 17/4/21.
 * 暂时只有面对hive数据仓库的操作
 */
public class SqlUtil {

    private Connection conn;
    private static DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public SqlUtil() {
    }

    public SqlUtil(Connection conn) {
        this.conn = conn;
    }


    public static Connection getConnection() throws SQLException {
        Connection con = dataSource.getConnection();
        return con;
    }



    public static boolean executeSql(String sql) throws Exception {
        boolean flag = false;
        Connection con = null;
        Statement stmt = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            stmt.execute(sql);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return flag;
    }


    public static List<Map> queryBySql(String sql) throws Exception {
        ResultSet rs = null;
        int totalRecords = 0;
        List list = null;
        Connection con = null;
        Statement stmt = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs != null) {
                list = new ArrayList();
            }
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
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }


    public static Map getMapBySql(String sql) throws Exception {
        List list = queryBySql(sql);
        if ((list != null) && (list.size() > 1)) {
            throw new Exception("数据库查询操作：" + sql + "结果记录不唯一！");
        }
        if ((list != null) && (list.size() == 1)) {
            Map map = (Map) list.get(0);
            return map;
        }
        return null;
    }



    public static  <T> List<T> getListTBySql(String sql, T t)throws Exception {
        List<Map> list = queryBySql(sql);
        List<T> olist = new ArrayList<T>();
        if (list != null) {
            try {
                for(Map item:list){
                    olist.add((T) ToolUtil.mapToBean(item, t.getClass().newInstance(), false, true));
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return olist;
    }


    public static List<Object> getListObjectBySql(String sql, Class c) throws Exception {
        List<Map> list = queryBySql(sql);
        List<Object> olist = new ArrayList<Object>();
        if (list != null) {
            try {
                for(Map item:list){
                    olist.add(ToolUtil.mapToBean(item, c.newInstance(), false, true));
                }

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return olist;
    }


    public static Object getObjectBySql(String sql, Class c) throws Exception {
        List list = queryBySql(sql);
        Object reObj = null;
        if ((list != null) && (list.size() > 1)) {
            throw new Exception("数据库查询操作：" + sql + "结果记录不唯一！");
        }
        if ((list != null) && (list.size() == 1)) {
            Map map = (Map)list.get(0);
            try {
                reObj = ToolUtil.mapToBean(map, c.newInstance(), false, true);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return reObj;
    }


    public static PageModel queryPageBySql(String sql, int pageNo, int pageSize) throws Exception {
        ResultSet rs = null;
        int totalRecords = 0;
        List list = null;
        Connection con = null;
        Statement stmt = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select count(*) from (" + sql + ") as num");
            if (rs.next()) {
                totalRecords = rs.getInt(1);
            }
            System.out.println(totalRecords);
            rs.close();
            if (pageNo < 0)
                pageNo = 1;
            else if (pageNo > totalRecords / pageSize + 1) {
                pageNo = totalRecords / pageSize + 1;
            }
            int startRowNum = (pageNo - 1) * pageSize;
            int endRowNum = pageNo * pageSize;
            //TODO orcl 和 mysql 分别 hive 不用
//            String ssql = "select  * from ( select row_.*, rownum rownum_ from (" + sql + " ) row_ where rownum < = " + endRowNum + " ) where rownum_ > " + startRowNum;
            String ssql = "select  * from (" + sql + " ) as temp " + " limit " + (pageNo - 1) * pageSize + "," + pageSize;

            if (stmt != null) {
                stmt.close();
            }
            stmt = con.createStatement();
            rs = stmt.executeQuery(ssql);
            if (rs != null) {
                list = new ArrayList();
            }
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (rs.next()) {
                Map map = new HashMap();
                for (int i = 1; i <= columnCount; i++) {
                    String columnTypeName = resultSetMetaData.getColumnTypeName(i).toLowerCase();
                    String columnName = resultSetMetaData.getColumnName(i).toLowerCase();
                    Object obj = null;
                    if (columnTypeName.equals("date"))
                        obj = rs.getTimestamp(columnName);
                    else {
                        obj = rs.getObject(columnName);
                    }
                    map.put(columnName, obj);
                }
                list.add(map);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        PageModel pm = new PageModel();
        pm.setList(list);
        pm.setPageNo(pageNo);
        pm.setPageSize(pageSize);
        pm.setTotalRecords(totalRecords);
        return pm;
    }



}
