package com.smart.sample.test;

import com.smart.framework.annotation.Order;
import com.smart.framework.base.BaseTest;
import com.smart.framework.bean.Pager;
import com.smart.framework.helper.BeanHelper;
import com.smart.sample.bean.ProductBean;
import com.smart.sample.service.ProductService;
import com.smart.sample.service.impl.ProductServiceImpl;
import java.util.HashMap;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProductServiceTest extends BaseTest {

    private ProductService productService = BeanHelper.getBean(ProductServiceImpl.class);

    @BeforeClass
    @AfterClass
    public static void init() {
        initSQL("sql/product.sql");
    }

    @Test
    @Order(1)
    public void getProductBeanPagerTest() {
        int pageNumber = 1;
        int pageSize = 10;
        String name = "";

        Pager<ProductBean> productBeanPager = productService.getProductBeanPager(pageNumber, pageSize, name);
        Assert.assertNotNull(productBeanPager);
        Assert.assertEquals(productBeanPager.getRecordList().size(), 10);
        Assert.assertEquals(productBeanPager.getTotalRecord(), 12);
    }

    @Test
    @Order(2)
    public void getProductBeanTest() {
        long productId = 1;

        ProductBean productBean = productService.getProductBean(productId);
        Assert.assertNotNull(productBean);
        Assert.assertNotNull(productBean.getProduct());
        Assert.assertNotNull(productBean.getProductType());
    }

    @Test
    @Order(3)
    public void createProductTest() {
        Map<String, Object> productFieldMap = new HashMap<String, Object>();
        productFieldMap.put("productTypeId", 1);
        productFieldMap.put("name", "1");
        productFieldMap.put("code", "1");
        productFieldMap.put("price", 1);
        productFieldMap.put("description", "1");

        boolean result = productService.createProduct(productFieldMap);
        Assert.assertTrue(result);
    }

    @Test
    @Order(4)
    public void updateProductTest() {
        long productId = 1;
        Map<String, Object> productFieldMap = new HashMap<String, Object>();
        productFieldMap.put("name", "1");
        productFieldMap.put("code", "1");

        boolean result = productService.updateProduct(productId, productFieldMap);
        Assert.assertTrue(result);
    }

    @Test
    @Order(5)
    public void deleteProductTest() {
        long productId = 1;

        boolean result = productService.deleteProduct(productId);
        Assert.assertTrue(result);
    }
}
