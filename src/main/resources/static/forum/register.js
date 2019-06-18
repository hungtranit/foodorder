$(document).ready(function () {
    var btnSubmit = document.getElementById('btn');

    // kiem tra dinh dang email
    $('#email').keyup(function () {
        var formatEmail = $('#email').val();
        var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        if (filter.test(formatEmail)) {
            $('#email').on('change', function () {
                $.ajax({
                    url: "/check-email",
                    data: {
                        'email': $('#email').val()
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data == 1) {
                            $('#msg-email').text("Email đã tồn tại");
                            btnSubmit.disabled = true;
                        } else {
                            $('#msg-email').text("Bạn có thể sử dụng email này");
                            btnSubmit.disabled = false;
                        }
                    },
                    error: function (e) {
                        console.log("check mail fail: " + e);
                    }
                });
            });
        } else {
            $('#msg-email').text("* Email không hợp lệ. Thử lại");
            formatEmail.focus;
            btnSubmit.disabled = true;
        }
    });

    var minLength = 6;
    var maxLength = 24;
    $('#password').on('keydown keyup change', function () {
        var char = $(this).val();
        var charLength = $(this).val().length;
        if (charLength < minLength || charLength > maxLength) {
            $('#msg-password').text('* Mật khẩu phải có hơn 6 và ít hơn 25 kí tự.');
            char.focus;
            btnSubmit.disabled = true;
        } else {
            $('#msg-password').text('');
            btnSubmit.disabled = false;
        }
    });

    // xac nhan mat khau
    $("#re-password").keyup(function () {
        var p = $("#password").val();
        if ($(this).val() != p) {
            $('#msg-re-password').text("* Mật khẩu không tương ứng. Thử lại");
            btnSubmit.disabled = true;
        } else {
            $("#msg-re-password").text("");
            btnSubmit.disabled = false;
        }
    });

    // xac nhan phone
    $("#phone").keyup(function () {
        var phone = $("#phone").val();
        var regexPattern = /([0-9]{10})/;
        if (!regexPattern.test(phone) || phone.length > 11) {
            $('#msg-phone').text("* Số điện thoại không hợp lệ");
            phone.focus;
            btnSubmit.disabled = true;
        } else {
            $('#msg-phone').text("");
            btnSubmit.disabled = false;
        }
    });

    $("#username").keyup(function () {
        var username = $("#username").val();
        if (username.length < 6 || username.length > 24) {
            $('#msg-username').text("* Tài khoản phải hơn 5 và ít hơn 25 kí tự");
            username.focus;
            btnSubmit.disabled = true;
        } else {
            $('#msg-username').text("");
            btnSubmit.disabled = false;
        }
    });

    // kiem tra username
    $('#username').keyup(function () {
        var username = $("#username").val();
        if (username.length < 6 || username.length > 24) {
            $('#msg-username').text("* Tài khoản phải hơn 5 và ít hơn 25 kí tự");
            username.focus;
            btnSubmit.disabled = true;
        } else {
            $('#username').on('change', function () {
                $.ajax({
                    url: "/check-user",
                    data: {
                        'username': $('#username').val()
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data == 1) {
                            $('#msg-username').text("Tài khoản đã tồn tại");
                            btnSubmit.disabled = true;
                        } else {
                            $('#msg-username').text("Bạn có thể sử dụng tài khoản này này");
                            btnSubmit.disabled = false;
                        }
                    },
                    error: function (e) {
                        console.log("check user fail: " + e);
                    }
                });
            });
        }
    });

})
;