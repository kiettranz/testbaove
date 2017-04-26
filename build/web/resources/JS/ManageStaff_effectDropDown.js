$(document).ready(function () {
    $('#formAddNew').hide();
    $('#btnAddNew').on('click', function (e) {
        var $this = $('#ImageBtnAdd');
        console.log("dropdown toggled!");
        if ($this.hasClass("glyphicon glyphicon-plus-sign")) {
            $this.removeClass('glyphicon-plus-sign').addClass('glyphicon-minus-sign');
            $('#formAddNew').slideDown();
        } else {
            $this.removeClass('glyphicon-minus-sign').addClass('glyphicon-plus-sign');
            $('#formAddNew').slideUp();
        }
    });
});
