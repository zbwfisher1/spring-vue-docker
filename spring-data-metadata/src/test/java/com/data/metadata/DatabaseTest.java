package com.data.metadata;




import com.data.metadata.database.Dialect;
import com.data.metadata.database.IntrospectedColumn;
import com.data.metadata.database.IntrospectedTable;
import com.data.metadata.database.SimpleDataSource;
import com.data.metadata.utils.DBMetadataUtils;

import java.sql.SQLException;
import java.util.List;


public class DatabaseTest {




    public static void main(String[] args) {
        SimpleDataSource dataSource = new SimpleDataSource(
                Dialect.MYSQL,
                "jdbc:mysql://localhost:3306/test",
                "root",
                "admin"
        );
        DBMetadataUtils dbMetadataUtils = null;
        try {
            dbMetadataUtils = new DBMetadataUtils(dataSource);

            List<IntrospectedTable> list = dbMetadataUtils.introspectTables(dbMetadataUtils.getDefaultConfig());

            for (IntrospectedTable table : list) {
                System.out.println(table.getName() + ":");
                for (IntrospectedColumn column : table.getAllColumns()) {
                    System.out.println(column.getName() + " - " +
                            column.getJdbcTypeName() + " - " +
                            column.getJavaProperty() + " - " +
                            column.getJavaProperty() + " - " +
                            column.getFullyQualifiedJavaType().getFullyQualifiedName() + " - " +
                            column.getRemarks());


//                    System.out.println(column.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
