$(document).ready(function () {
    search();
});

function search() {
    var searchText = {}
    searchText["searchText"] = $("#searchText").val();
    $.ajax(this.href, {
        type: "POST",
        contentType: "application/json",
        dataType: 'json',
        url: "/search-product",
        cache: false,
        data: JSON.stringify(comment),
        success: function (response) {
            window.location.href = "/" + response;
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    })
}