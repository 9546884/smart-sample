$(function() {
    $('#edit').click(function() {
        location.href = 'edit.html';
    });

    $('#cancel').click(function() {
        location.href = 'main.html';
    });

    var productId = window.sessionStorage.getItem('product.id');
    $.ajax({
        type: 'get',
        url: '/product/' + productId,
        success: function(result) {
            if (result.success) {
                var productBean = result.data;
                $('#product_type_id').val(productBean.productType.productTypeName);
                $('#product_name').val(productBean.product.productName);
                $('#product_code').val(productBean.product.productCode);
                $('#price').val(productBean.product.price);
                $('#description').val(productBean.product.description);
            } else {
                switch (result.error) {
                    case 10:
                        alert('The parameter is error!');
                        break;
                    case 20:
                        alert('The data is error!');
                        break;
                }
            }
        }
    });
});