package com.smart.sample.service.impl;

import com.smart.framework.DataSet;
import com.smart.framework.annotation.Bean;
import com.smart.framework.annotation.Transaction;
import com.smart.framework.base.BaseService;
import com.smart.framework.bean.Pager;
import com.smart.framework.util.MapUtil;
import com.smart.framework.util.StringUtil;
import com.smart.sample.bean.ProductBean;
import com.smart.sample.entity.Product;
import com.smart.sample.entity.ProductType;
import com.smart.sample.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Bean
public class ProductServiceImpl extends BaseService implements ProductService {

    @Override
    public List<Product> getProductList() {
        return DataSet.selectList(Product.class, "", "id asc");
    }

    @Override
    public Product getProduct(long productId) {
        return DataSet.select(Product.class, "id = ?", productId);
    }

    @Override
    @Transaction
    public boolean createProduct(Map<String, Object> productFieldMap) {
        return DataSet.insert(Product.class, productFieldMap);
    }

    @Override
    @Transaction
    public boolean updateProduct(long productId, Map<String, Object> productFieldMap) {
        return DataSet.update(Product.class, productFieldMap, "id = ?", productId);
    }

    @Override
    @Transaction
    public boolean deleteProduct(long productId) {
        return DataSet.delete(Product.class, "id = ?", productId);
    }

    @Override
    public Pager<ProductBean> searchProductPager(int pageNumber, int pageSize, Map<String, String> queryMap) {
        String where = "";
        if (MapUtil.isNotEmpty(queryMap)) {
            String productName = queryMap.get("productName");
            if (StringUtil.isNotEmpty(productName)) {
                where += "product_name like '%" + productName + "%'";
            }
        }

        String sort = "id asc";

        int totalRecord = DataSet.selectCount(Product.class, where);
        List<Product> productList = DataSet.selectListForPager(pageNumber, pageSize, Product.class, where, sort);

        List<ProductBean> productBeanList = new ArrayList<ProductBean>();
        Map<Long, ProductType> productTypeMap = DataSet.selectMap(ProductType.class, "");
        for (Product product : productList) {
            ProductType productType = productTypeMap.get(product.getProductTypeId());
            if (productType != null) {
                productBeanList.add(new ProductBean(product, productType));
            }
        }

        return new Pager<ProductBean>(pageNumber, pageSize, totalRecord, productBeanList);
    }
}
