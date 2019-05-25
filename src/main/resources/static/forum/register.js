$(document).ready(function () {
    var btnSubmit = document.getElementById('btn');
    var x_timer;
    // kiem tra dinh dang email
    $('#email').keyup(function () {
        // var formatEmail = $('#email').val();
        // var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        // if (!filter.test(formatEmail)) {
        //     $('#msg-email').text("Email không hợp lệ. Thử lại");
        //     formatEmail.focus;
        //     btnSubmit.disabled = true;
        // } else {
        clearTimeout(x_timer);
        var email = $('#email').val();
        x_timer = setTimeout(function () {
            check_email_ajax(email);
            email.focus;
        }, 2000);
        // }
    });

    function check_email_ajax(param) {
        var email = param;
        $.ajax({
            type: "POST",
            contentType: "application/json",
            dataType: 'json',
            url: "/check-email",
            cache: false,
            data: JSON.stringify(email),
            success: function (response) {
                if (response == 1) {
                    $('#msg-email').text("Email đã tồn tại");
                    btnSubmit.disabled = true;
                } else {
                    $('#msg-email').text("Bạn có thể sử dụng email này");
                    btnSubmit.disabled = false;
                }
            }
        });
    }

    // xac nhan mat khau
    $("#re-password").keyup(function () {
        var p = $("#password").val();
        if ($(this).val() != p) {
            $('#msg-re-password').text("Mật khẩu không tương ứng. Thử lại");
            btnSubmit.disabled = true;
        } else {
            $("#msg-re-password").text("");
            btnSubmit.disabled = false;
        }
    });
    // xac nhan phone
    $("#phone").keyup(function () {
        var phone = $("#phone").val();
        var regexPattern = /([0-9]{10})|(\([0-9]{11})/;
        if (!regexPattern.test(phone)) {
            $('#msg-phone').text("Số điện thoại không hợp lệ");
            phone.focus;
            btnSubmit.disabled = true;
        } else {
            $('#msg-phone').text("");
            btnSubmit.disabled = false;
        }
    });

    $("#username").keyup(function () {
        var username = $("#username").val();
        if (username.text().length < 6 || username.text().length < 24) {
            $('#msg-username').text("Tài khoản phải có hơn 5 và bé hơn 25 kí tự");
            username.focus;
            btnSubmit.disabled = true;
        } else {
            $('#msg-username').text("");
            btnSubmit.disabled = false;
        }

    });

    $("#password").keyup(function () {
        var password = $("#password").val();
        if (password.text().length < 6 || password.text().length < 24) {
            $('#msg-password').text("Mật khẩu phải có hơn 5 và bé hơn 25 kí tự");
            password.focus;
            btnSubmit.disabled = true;
        } else {
            $('#msg-password').text("");
            btnSubmit.disabled = false;
        }
    });

    $("#re-password").keyup(function () {
        var password = $("#password").val();
        if (password.text().length < 6 || password.text().length < 24) {
            $('#msg-re-password').text("Mật khẩu phải có hơn 5 và bé hơn 25 kí tự");
            password.focus;
            btnSubmit.disabled = true;
        } else {
            $('#msg-re-password').text("");
            btnSubmit.disabled = false;
        }
    });

});