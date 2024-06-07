const formulario = document.querySelector("form");
        const resultadosDiv = document.querySelector(".resultados");

        function handleProfessorData() {
            const professorId = document.getElementById('professorId').value;
            const outputOption = document.querySelector('input[name="outputOption"]:checked').value;

            fetch(`http://localhost:8080/professor/${professorId}`, {
                headers: {
                    'Accept': 'application/json'
                },
                method: "GET"
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro na requisição: ${response.statusText}`);
                }
                return response.json();
            })
            .then(data => {
                if (!data || !data.id || !data.name || !data.email) {
                    throw new Error("Os dados recebidos não são válidos.");
                }

                if (outputOption === 'csv') {
                    downloadCSV(data, professorId);
                } else if (outputOption === 'screen') {
                    displayOnScreen(data);
                }
            })
            .catch(error => {
                alert(`Erro ao obter dados do professor: ${error.message}`);
                console.error('Error:', error);
            });
        }

        function downloadCSV(data, professorId) {
            const csvContent = convertToCSV(data);
            const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
            const url = URL.createObjectURL(blob);
            const link = document.createElement("a");
            link.setAttribute("href", url);
            link.setAttribute("download", `professor_${professorId}.csv`);
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
            alert('Dados do professor baixados com sucesso!');
        }

        function displayOnScreen(data) {
            resultadosDiv.innerHTML = `
                <p>ID: ${data.id}</p>
                <p>Nome: ${data.name}</p>
                <p>Email: ${data.email}</p>
            `;
            alert('Dados do professor exibidos com sucesso!');
        }

        function convertToCSV(data) {
            const headers = "ID,Name,Email";
            const row = `${data.id},${data.name},${data.email}`;
            return `${headers}\n${row}`;
        }

        formulario.addEventListener('submit', function (event){
            event.preventDefault();
            handleProfessorData();
        });