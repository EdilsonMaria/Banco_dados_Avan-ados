const formulario = document.querySelector("form");

const Iname = document.querySelector(".name");
const Iemail = document.querySelector(".email");

function cadastroProf() {
    fetch("http://localhost:8080/professor", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
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
            alert('Professor cadastrado com sucesso!');
            console.log('Success:', data);
        })
        .catch(error => {
            alert('Erro ao cadastrar professor.');
            console.error('Error:', error);
        });
};

function limpar() {
    Iname.value = "";
    Iemail.value = "";
}

formulario.addEventListener('submit', function(event) {
    event.preventDefault();
    cadastroProf();
    limpar();
});

