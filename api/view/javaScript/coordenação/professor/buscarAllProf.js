const formulario = document.querySelector("form");

function downloadProfessores() {
    fetch("http://localhost:8080/professor/all", {
        headers: {
            'Accept': 'application/json'
        },
        method: "GET"
    })
    .then(response => response.json())
    .then(data => {
        // Converter os dados para CSV
        const csvContent = convertToCSV(data);

        // Criar um link para download
        const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
        const url = URL.createObjectURL(blob);
        const link = document.createElement("a");
        link.setAttribute("href", url);
        link.setAttribute("download", "professores.csv");
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        
        alert('Dados dos professores baixados com sucesso!');
    })
    .catch(error => {
        alert('Erro ao baixar dados dos professores.');
        console.error('Error:', error);
    });
}

function convertToCSV(data) {
    const headers = Object.keys(data[0]).join(",");
    const rows = data.map(obj => Object.values(obj).join(",")).join("\n");
    return `${headers}\n${rows}`;
}

formulario.addEventListener('submit', function (event){
    event.preventDefault();

    downloadProfessores();
});