const mealAjaxUrl = "ajax/profile/meals/";

function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: "ajax/profile/meals/filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function formatDate(date){
    return addZero(date.getDate()) + '-' + addZero(date.getMonth() + 1)
        + '-' + date.getFullYear() + ' ' + addZero(date.getHours())
        + ':' + addZero(date.getMinutes()) + ':' + addZero(date.getSeconds());
}

function addZero(num){
    if(num < 10){
        return '0' + num;
    } else {
        return num;
    }
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get("ajax/profile/meals/", updateTableByData);
}

$(function () {
    makeEditable({
        ajaxUrl: mealAjaxUrl,
        datatableApi: $("#datatable").DataTable({
            "ajax": {
                "url": mealAjaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime",
                    "render": function(data, type, row){
                        if(type === "display"){
                            return formatDate(new Date(data));
                        }
                        return data;
                    }
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                if(data.excess){
                    $(row).attr("data-mealExcess", true);
                } else {
                    $(row).attr("data-mealExcess", false);
                }
            }
        }),
        updateTable: updateFilteredTable
    });
});

// Data Picker Initialization
$('.datePicker').datetimepicker(
    {
        timepicker: false,
        format: 'Y-m-d'
    }
);

$('.timePicker').datetimepicker(
    {
        datepicker:false,
        format:'H:i'
    }
);

$('.dateTimePicker').datetimepicker(
    {
        format:'Y-m-d H:i',
    }
);