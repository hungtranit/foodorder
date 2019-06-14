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

    // $('#form-forgot-password').submit(function (event) {
    //     event.preventDefault();
    //     createPassword();
    // });
    //
    // function createPassword() {
    //     var infoUser = {}
    //     infoUser["username"] = $('#forgot-username').val(),
    //         infoUser["email"] = $('#forgot-email').val();
    //     $.ajax({
    //         type: "POST",
    //         url: "/forgot-password",
    //         contentType: "application/json",
    //         data: JSON.stringify(infoUser),
    //         dataType: 'json',
    //         success: function (result) {
    //             console.log("result: " + result);
    //             if (result == true) {
    //                 $('#register-success').text('Kiểm tra mail để xem mật khẩu mới!!!');
    //                 window.location.href = "/login";
    //             } else {
    //                 $('#create-new-password-fail').text('Tài khoản hoặc email không đúng!!!');
    //                 window.location.href = "/forgot-password";
    //             }
    //         },
    //         error: function (e) {
    //             console.log("this error: " + e);
    //             // window.location.href = "/login";
    //         }
    //     });
    // };

});