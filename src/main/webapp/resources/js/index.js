function submitForm() {
    $.ajax({
        type: "POST",
        url: "/add",
        data: $("#createRecordForm").serialize(),
        success: function () {
            if (formValidation()) {
                validateEqualNames();
                $("#createRecordForm").each(function () {
                    this.reset();
                });
            }
        },
        error: function (e) {
            toastr.error("Error: " + e);
        }
    });
    return false;
}

function formValidation() {
    if ($('#createRecordForm').validationEngine('validate')) {
        return true;
    }
}

function validateEqualNames() {
    $.ajax({
        type: "GET",
        url: "/validate",
        success: function (boolFlag) {
            console.log("boolFlag is " + boolFlag);
            if (boolFlag === false) {
                toastr.error("A person with this name already exists in the database!");
            } else {
                toastr.success("The record has been added successfully.");
                ajaxCallTable();
            }
        }
    });
}

function ajaxCallTable() {
    $.ajax({
        type: "GET",
        url: "/showData?" + $.param({searchValue: $("#searchValue").val()}),
        success: function (str) {
            makeTheTable(JSON.stringify(str));
        },
        error: function (e) {
            toastr.error("Error: " + e);
        }
    });
    return false;
}

function makeTheTable(incomingJson) {
    var str;
    var obj = $.parseJSON(incomingJson);
    if (obj.length != 0) {
        str = ("<table class=\"table table-condensed\" id=\"tablePhones\">" +
            "<thead><tr><td>NAME</td><td>PHONE</td></tr></thead><tbody>");
        console.log("obj.length = " + obj.length);
        for (var i = 0; i < obj.length; i++) {
            str += ("<tr>");
            str += ("<td>" + obj[i].name + "</td>");
            str += ("<td>" + obj[i].telephone + "</td>");
            str += ("<td><button id=\"removeButton\" type=\"button\" class=\"btn btn-link\" onclick=\"deleteRecord(" + obj[i].id + ")\">Remove</button></td>");
            str += ("</tr>");
        }
        str += "</tbody></table>";
    } else {
        str = "<p>The database contains no records.</p>";
    }
    $("#tablePhoneDiv").html(str);
}

function deleteRecord(incomingID) {
    console.log(incomingID);
    $.ajax({
        type: "GET",
        url: "/remove?id=" + incomingID,
        success: function () {
            toastr.warning("Record was successfully deleted!");
            ajaxCallTable();
        },
        error: function (e) {
            console.log(e);
            toastr.error("Error: " + e);
        }
    });
    return false;
}

$(document).ready(ajaxCallTable());