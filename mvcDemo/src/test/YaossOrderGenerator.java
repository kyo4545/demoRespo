package com.huntkey.rx.sceo.erp.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.List;

public class YaossOrderGenerator {
    public static final String PROJECT_RESOURCES_ROOT_DIR = "D:\\project\\sceo-erp\\biz-erp\\src\\main\\resources";
    public static final String PROJECT_SOURCES_ROOT_DIR = "D:\\project\\sceo-erp\\biz-erp\\src\\main\\java";

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        Configuration config = new Configuration();
        Context context = new Context(ModelType.CONDITIONAL);
        context.setId("erpGenerator");
        //是否将数据库的表和列的备注生成到实体中
        CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
        commentGeneratorConfiguration.addProperty("addRemarkComments", "true");
        context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);
        //配置数据库连接
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setDriverClass("com.mysql.jdbc.Driver");
        jdbcConnectionConfiguration.setConnectionURL("jdbc:mysql://10.3.98.163:3306/sceo?useSSL=true");
        jdbcConnectionConfiguration.setUserId("root");
        jdbcConnectionConfiguration.setPassword("root123");
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
        //配置实体类生成策略
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.addProperty("rootClass", "com.huntkey.rx.sceo.core.repository.entity.OrderEntity");
        //指定实体类所属的包名
        javaModelGeneratorConfiguration.setTargetPackage("com.huntkey.rx.sceo.erp.local.order.sale.repository.entity");
        //指定工程的src目录
        javaModelGeneratorConfiguration.setTargetProject(PROJECT_SOURCES_ROOT_DIR);
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
        //配置Sql文件的生成策略
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetPackage("mapper.order");
        sqlMapGeneratorConfiguration.setTargetProject(PROJECT_RESOURCES_ROOT_DIR);
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
        //配置DAO接口的生成策略
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        //接口实现方式:XMLMAPPER、MIXEDMAPPER、ANNOTATEDMAPPER
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        javaClientGeneratorConfiguration.addProperty("rootInterface","com.huntkey.rx.sceo.core.repository.dao.Mapper");
        javaClientGeneratorConfiguration.setTargetPackage("com.huntkey.rx.sceo.erp.local.order.sale.repository.dao");
        javaClientGeneratorConfiguration.setTargetProject(PROJECT_SOURCES_ROOT_DIR);
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
        //生成实体的其他规则配置
        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setDomainObjectName("SaRebateOrder");
        //是否直接使用数据库列名作为属性名，false时会转驼峰
        tableConfiguration.setTableName("sa_rebate_order");
        context.addTableConfiguration(tableConfiguration);
        config.addContext(context);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        System.out.println("success");
    }
}
