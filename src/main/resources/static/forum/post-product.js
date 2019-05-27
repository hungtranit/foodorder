$('#post-product').submit(function (e) {
    e.preventDefault();
    postProduct();
});

function postProduct() {
    var product = {}
    product["typeproduct"] = $('#type-product').val(),
        product["productname"] = $('#product-name').val(),
        product["address"] = $('#address').val(),
        product["phone"] = $('#phone').val(),
        product["price"] = $('#price').val(),
        product["decription"] = $('#decription').val(),
        product["img"] = $('#img').val();
    $.ajax({
        type: "POST",
        url: "/register-user",
        contentType: "application/json",
        data: JSON.stringify(infoUser),
        dataType: 'json',
        success: function (result) {
            console.log("result :" + result);
            window.location.href = "/login";
        },
        error: function (e) {
            console.log(e);
            window.location.href = "/login";
        }
    });
};

