$(document).ready(function () {
    $("#post-comment-form").submit(function (event) {
        event.preventDefault();
        comment();
    });
});

function comment() {

    var comment = {}
    comment["content"] = $("#message-box").val();

    $.ajax(this.href, {
        type: "POST",
        contentType: "application/json",
        dataType: 'json',
        url: "/add-comment",
        cache: false,
        data: JSON.stringify(comment),
        success: function (response) {
            var json = "<div class=\"comments-details\"><div class=\"comments-list-img\">" +
                "<img src=\"../home/img/blog/b02.jpg\" alt=\"post-author\"></div>" +
                "<div class=\"comments-content-wrap\"><span>" +
                "<b><a href=\"#\">" + response.userCMT + "</a></b >" +
                "<span class =\"post-time\">" + response.timecmt + "</span>" +
                "<button class=\"add_field_button\"> Reply </button>" +
                "</span><p>" + response.content + "</p></div></div>";
            $("#comments").fadeOut(800, function () {
                $("#comments").html(json).fadeIn().delay(2000);
            });
            $("#message-box").val("");
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    })

}