const BASE_URL = 'http://localhost:8080/api';
const form = document.getElementById('main-form');
const deleteForm = document.getElementById('delete-form');
const statusMessage = document.getElementById('status-message');
const resultsList = document.getElementById('results-list');

function showStatus(message, isError) {
    statusMessage.textContent = message;
    statusMessage.className = isError ? 'status-message error' : 'status-message success';
    statusMessage.style.display = 'block';
    setTimeout(() => { statusMessage.style.display = 'none'; }, 5000);
}

function updateForm() {
    const entity = document.getElementById('entity-select').value;
    document.getElementById('hospede-fields').style.display = entity === 'hospedes' ? 'block' : 'none';
    document.getElementById('funcionario-fields').style.display = entity === 'funcionarios' ? 'block' : 'none';
}
document.addEventListener('DOMContentLoaded', updateForm);

async function handleResponse(response) {
    const data = await response.json();

    if (response.ok) {
        showStatus(data.message, false);
    } else {
        showStatus(data.message, true);
    }
}


form.addEventListener('submit', async function(event) {
    event.preventDefault();
    const entity = document.getElementById('entity-select').value;
    const url = `${BASE_URL}/${entity}`;

    const data = {
        cpf: document.getElementById('cpf').value,
        nome: document.getElementById('nome').value,
        idade: parseInt(document.getElementById('idade').value),
    };

    if (entity === 'hospedes') {
        data.rg = document.getElementById('rg').value;
        data.fidelidade = document.getElementById('fidelidade').checked;
    } else {
        data.funcao = document.getElementById('funcao').value;
    }

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        await handleResponse(response);
        listar(entity);

    } catch (error) {
        console.error('Erro de Rede:', error);
        showStatus('Erro de conexão com o servidor.', true);
    }
});

async function listar(entity) {
    const url = `${BASE_URL}/${entity}`;
    resultsList.innerHTML = `<li>Carregando ${entity}...</li>`;

    try {
        const response = await fetch(url);

        if (!response.ok) {
            resultsList.innerHTML = `<li>Erro ao carregar dados (Status: ${response.status}).</li>`;
            return;
        }

        const data = await response.json();
        resultsList.innerHTML = '';

        if (data.length === 0) {
            resultsList.innerHTML = `<li>Nenhum ${entity} cadastrado.</li>`;
            return;
        }

        data.forEach(item => {
            const li = document.createElement('li');

            if (entity === 'hospedes') {
                li.innerHTML = `**HÓSPEDE** | CPF: ${item.cpf}, Nome: ${item.nome}, Idade: ${item.idade}, Fidelidade: **${item.fidelidade ? 'Sim' : 'Não'}**`;
            } else {
                li.innerHTML = `**FUNCIONÁRIO** | CPF: ${item.cpf}, Nome: ${item.nome}, Função: **${item.funcao}**`;
            }
            resultsList.appendChild(li);
        });

    } catch (error) {
        console.error('Erro ao listar:', error);
        resultsList.innerHTML = `<li>Erro ao carregar lista. Verifique se o Back-End está ativo.</li>`;
    }
}

deleteForm.addEventListener('submit', async function(event) {
    event.preventDefault();
    const entity = document.getElementById('delete-entity-select').value;
    const cpf = document.getElementById('delete-cpf').value;
    const url = `${BASE_URL}/${entity}/${cpf}`;

    try {
        const response = await fetch(url, {
            method: 'DELETE'
        });

        await handleResponse(response);
        listar(entity);

    } catch (error) {
        console.error('Erro de Rede:', error);
        showStatus('Erro de conexão ao tentar excluir.', true);
    }
});