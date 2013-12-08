<%@ page pageEncoding="UTF-8" %>
<%@ include file="common/global.jsp" %>

<c:set var="productBeanList" value="${productBeanPager.recordList}"/>

<table id="product_table" class="css-table">
    <thead>
        <tr>
            <td><f:message key="product.picture"/></td>
            <td><f:message key="product.product_type"/></td>
            <td><f:message key="product.name"/></td>
            <td><f:message key="product.code"/></td>
            <td><f:message key="product.price"/></td>
            <td><f:message key="product.description"/></td>
            <td class="css-width-75"><f:message key="common.action"/></td>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="productBean" items="${productBeanList}">
            <c:set var="product" value="${productBean.product}"/>
            <c:set var="productType" value="${productBean.productType}"/>
            <tr data-id="${product.id}" data-name="${product.name}">
                <td>
                    <c:set var="picture" value="www/upload/${product.picture}"/>
                    <img src="${BASE}/${not empty product.picture ? picture : 'www/img/s.gif'}" height="32"/>
                </td>
                <td>${productType.name}</td>
                <td>
                    <a href="${BASE}/product/view/${product.id}">${product.name}</a>
                </td>
                <td>${product.code}</td>
                <td>${product.price}</td>
                <td>${product.description}</td>
                <td>
                    <a href="${BASE}/product/edit/${product.id}"><f:message key="common.edit"/></a>
                    <a href="#" class="ext-product-delete"><f:message key="common.delete"/></a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<tag:pager id="product_pager" pager="${productBeanPager}"/>