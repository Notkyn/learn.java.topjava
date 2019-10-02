let formFilter = $('#filter');

$(function () {
    makeEditable({
            ajaxUrl: "ajax/profile/meals/",
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "dateTime"
                    },
                    {
                        "data": "description"
                    },
                    {
                        "data": "calories"
                    },
                    {
                        "defaultContent": "Edit",
                        "orderable": false
                    },
                    {
                        "defaultContent": "Delete",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "desc"
                    ]
                ]
            })
        }
    );
});

function updateFilteredTable(){
    $.ajax({
        type: "GET",
        url: context.ajaxUrl + 'filter',
        data: formFilter.serialize()
    }).done(function (data) {
        context.datatableApi.clear().rows.add(data).draw();
    });
}

function clearFilter(){
    formFilter.find(":input").val("");
    updateTable();
}

// Data Picker Initialization
$('#datetimepicker').datetimepicker();

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
        format:'Y-m-d H:i'
    }
);