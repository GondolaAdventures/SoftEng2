if (document.referrer=="http://localhost/SoftEng2/logIn.html") {
    alert("Welcome to LaBiblioTECA");
}

$(document).ready(function () { //leftover when @media still existed
                $("#sidebar").mCustomScrollbar({
                    theme: "minimal"
                });

                $('#sidebarCollapse').on('click', function () {
                    $('#sidebar, #content').toggleClass('active');
                    $('.collapse.in').toggleClass('in');
                    $('a[aria-expanded=true]').attr('aria-expanded', 'false');
                });
            });

// clears the file field when called
function deleteFile() {
    document.getElementById('formFileMultiple').value = "";
    }