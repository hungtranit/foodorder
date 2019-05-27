$(document).ready(function () {
    var btnSubmit = document.getElementById('btn-post-product');

    // xac nhan phone
    // $("#post-phone").keyup(function () {
    //     var phone = $("#phone").val();
    //     var regexPattern = /([0-9]{10})/;
    //     if (!regexPattern.test(phone) || phone.length > 11) {
    //         $('#msg-phone').text("* Số điện thoại không hợp lệ");
    //         phone.focus;
    //         btnSubmit.disabled = true;
    //     } else {
    //         $('#msg-phone').text("");
    //         btnSubmit.disabled = false;
    //     }
    // });

    $('#post-product').submit(function (e) {
        e.preventDefault();
        postProduct();
    });

    function postProduct() {
        var product = {}
        product["typeproduct"] = $('#type-product').val(),
            product["productname"] = $('#product-name').val(),
            product["addressproduct"] = $('#address-post').val(),
            product["phone"] = $('#phone').val(),
            product["price"] = $('#price').val(),
            product["decriptionproduct"] = $('#description-product').val(),
            product["img"] = $('#img').val();
        $.ajax({
            type: "POST",
            url: "/post-product",
            contentType: "application/json",
            data: JSON.stringify(product),
            dataType: 'json',
            success: function (result) {
                console.log("result :" + result);
                window.location.href = "/" + result;
                $('#post-success').text('Đăng kí sản phẩm thành công!!!');
                $("#type-product").val("");
                $("#product-name").val("");
                $("#address").val("");
                $("#phone").val("");
                $("#price").val("");
                $("#description-product").val("");
                $("#img").val("");
            },
            error: function (e) {
                console.log("this error: " + e);
                window.location.href = "/login";
                $('#post-success').text('Đăng kí sản phẩm thành công!!!');
                $("#type-product").val("");
                $("#product-name").val("");
                $("#address").val("");
                $("#phone").val("");
                $("#price").val("");
                $("#description-product").val("");
                $("#img").val("");
            }
        });
    };
});