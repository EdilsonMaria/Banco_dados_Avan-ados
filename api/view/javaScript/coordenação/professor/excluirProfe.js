const deleteId = document.getElementById('deleteProfessorId');

        function deletarProf() {
            const professorId = deleteId.value;

            fetch(`http://localhost:8080/professor/${professorId}`, {
                headers: {
                    'Accept': 'application/json'
                },
                method: "DELETE"
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na resposta da rede');
                }
                alert('Professor deletado com sucesso!');
                console.log('Deleted:', response);
            })
            .catch(error => {
                alert('Erro ao deletar professor.');
                console.error('Error:', error);
            });
        }

        function limparDelecao() {
            deleteId.value = "";
        }

        deleteForm.addEventListener('submit', function (event){
            event.preventDefault();
            deletarProf();
            limparDelecao();
        });