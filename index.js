/* Alert box
if (document.referrer=="http://localhost/SoftEng2/logIn.html") {
    alert("Welcome to La BiblioTecha");
} */ // Unused due to modal #Welcome

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

	
//PDF Reader
const zoombutton = document.getElementById('zoom');
const input = document.getElementById('inputFile');
const openFile = document.getElementById('openPDF');
const currentPage = document.getElementById('current_page');
const viewer = document.querySelector('.pdf-viewer')
let currentPDF = {};

function resetCurrentPDF(){
    currentPDF = {
        file: null,
        countofpages: 0,
        currentPage: 1,
        zoom: 1.5
    }
}

openPDF.addEventListener('click', event => {
    input.click();
});

input.addEventListener('change', event => {
	const inputFile = event.target.files[0];
	if (inputFile.type == 'application/pdf') {
		const reader = new FileReader();
		reader.readAsDataURL(inputFile);
		reader.onload = () => {
			loadPDF(reader.result);
			zoomButton.disabled = false;
		}
	}
	else {
		alert("The file you are trying to open is not a pdf file!")
	}
});

zoomButton.addEventListener('input', () => {
	if (currentPDF.file) {
		document.getElementById('zoomValue').innerHTML = zoomButton.value + "%";
		currentPDF.zoom = parseInt(zoomButton.value) / 100;
		renderCurrentPage();
	}
});

document.getElementById('next').addEventListener('click', () => {
	const isValidPage = currentPDF.currentPage < currentPDF.countOfPages;
	if (isValidPage) {
		currentPDF.currentPage += 1;
		renderCurrentPage();
	}
});

document.getElementById('previous').addEventListener('click', () => {
	const isValidPage = currentPDF.currentPage - 1 > 0;
	if (isValidPage) {
		currentPDF.currentPage -= 1;
		renderCurrentPage();
	}
});

function loadPDF(data) {
	const pdfFile = pdfjsLib.getDocument(data);
	resetCurrentPDF();
	pdfFile.promise.then((doc) => {
		currentPDF.file = doc;
		currentPDF.countOfPages = doc.numPages;
		viewer.classList.remove('hidden');
		document.querySelector('main h3').classList.add("hidden");
		renderCurrentPage();
	});

}

function renderCurrentPage() {
	currentPDF.file.getPage(currentPDF.currentPage).then((page) => {
		var context = viewer.getContext('2d');
		var viewport = page.getViewport({ scale: currentPDF.zoom, });
		viewer.height = viewport.height;
		viewer.width = viewport.width;

		var renderContext = {
			canvasContext: context,
			viewport: viewport
		};
		page.render(renderContext);
	});
	currentPage.innerHTML = currentPDF.currentPage + ' of ' + currentPDF.countOfPages;
}



