const formulario = document.querySelector("#updateForm");
const Iname = document.querySelector(".name");
const Iemail = document.querySelector(".email");
const Iid = document.getElementById('professorId');

function atualizarProf() {
    const professorId = Iid.value;

    fetch(`http://localhost:8080/professor/${professorId}`, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "PUT",
        body: JSON.stringify({
            name: Iname.value,
            email: Iemail.value
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro na resposta da rede');
        }
        return response.json().catch(() => { return {}; });
    })
    .then(data => {
        alert('Professor atualizado com sucesso!');
        console.log('Success:', data);
    })
    .catch(error => {
        alert('Erro ao atualizar professor.');
        console.error('Error:', error);
    });
}

function limpar() {
    Iid.value = "";
    Iname.value = "";
    Iemail.value = "";
}

formulario.addEventListener('submit', function (event){
    event.preventDefault();
    atualizarProf();
    limpar();
});