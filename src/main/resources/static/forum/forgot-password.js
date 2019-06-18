$(document).ready(function () {
    var btnSubmit = document.getElementById('btn-forgot-password');

    // kiem tra dinh dang email
    $('#forgot-email').keyup(function () {
        var formatEmail = $('#forgot-email').val();
        var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        if (!filter.test(formatEmail)) {
            $('#msg-forgot-email').text('* Email không hợp lệ. Thử lại');
            formatEmail.focus;
            btnSubmit.disabled = true;
        } else {
            $('#msg-forgot-email').text('');
            formatEmail.focus;
            btnSubmit.disabled = false;
        }
    });

    $("#forgot-username").keyup(function () {
        var username = $("#forgot-username").val();
        if (username.length < 6 || username.length > 24) {
            $('#msg-forgot-username').text("* Tài khoản phải hơn 5 và ít hơn 25 kí tự");
            username.focus;
            btnSubmit.disabled = true;
        } else {
            $('#msg-forgot-username').text("");
            username.focus;
            btnSubmit.disabled = false;
        }
    });

});