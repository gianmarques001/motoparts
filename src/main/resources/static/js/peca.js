document.getElementById('addItemEstoqueLojaForm').addEventListener('submit', async function enviarDadosPeca(evt) {
    evt.preventDefault();
    const form = document.getElementById('addItemEstoqueLojaForm');

    const dadosPeca = {
        codPeca: form.txtCodItemLoja.value,
        nome: form.txtNomeLoja.value,
        marca: form.txtMarcaLoja.value,
        descricao: form.txtDescricaoLoja.value,
        quantidade: form.txtQtdeLoja.value,
        preco: form.txtPrecoLoja.value

    };
    console.log(JSON.stringify(dadosPeca));

    const httpResp = await fetch('/api/motoparts/peca', {
        method: 'post',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(dadosPeca)
    });
    if (!httpResp.ok) {
        if (httpResp.status !== 400) {
            alert('Erro no envio dos dados - ' + httpResp.status);
            return;
        }
        const erros = await httpResp.json();
        for (const erro of erros.errors) {
            const mensagem = erro.defaultMessage;
            const field = erro.field;
            console.log(mensagem)
        }
    }
    alert('Sucesso');
});


// Função para carregar os dados da API e inserir na tabela
async function carregarEstoque() {

    const resposta = await fetch('/api/motoparts/peca');

    if (!resposta.ok) {
        throw new Error('Erro ao carregar os dados da API.');
        return;
    }
    const dados = await resposta.json();

    console.log(dados);

    const tabelaCorpo = document.getElementById('estoqueLoja');


    tabelaCorpo.innerHTML = '';

    if (dados && dados.length > 0) {
        // Iterar sobre cada item e criar a linha da tabela
        dados.forEach(item => {
            // Criar uma nova linha (<tr>)
            const linha = document.createElement('tr');

            const colunas = [
                item.id,
                item.codPeca,
                item.nome,
                item.descricao,
                item.marca,
                item.quantidade,
                item.preco
            ];

            // Para cada coluna, criar uma célula e adicionar à linha
            colunas.forEach(dado => {
                const celula = document.createElement('td');
                celula.textContent = dado;
                linha.appendChild(celula);
            });

            // Célula para o ícone de editar (ex: lápis)
            const editarCelula = document.createElement('td');
            editarCelula.innerHTML = '<i class="fas fa-pencil-alt"></i>'; // Ícone de lápis
            editarCelula.addEventListener('click', () => editarItem(item.id)); // Adicionar ação de editar
            linha.appendChild(editarCelula);


            const visualizarCelula = document.createElement('td');
            visualizarCelula.innerHTML = '<i class="fas fa-id-card"></i>'; // Ícone de id
            visualizarCelula.addEventListener('click', () => visualizarItem(item.id)); // Ação para visualizar
            linha.appendChild(visualizarCelula);


            const excluirCelula = document.createElement('td');

            excluirCelula.innerHTML = '<a href="#" style="color: #343a40" "><i class="fas fa-trash-alt"></i></a>';
            excluirCelula.addEventListener('click', () => excluirItem(item.id)); // Ação para excluir
            linha.appendChild(excluirCelula);

            // Adicionar a linha à tabela
            tabelaCorpo.appendChild(linha);
        });
    } else {
        // Se não houver dados,
        tabelaCorpo.innerHTML = '<tr><td colspan="10">Nenhum item encontrado.</td></tr>';
    }
}


async function excluirItem(id) {

    const httpResp = await fetch(`/api/motoparts/peca/${id}`, {
        method: 'delete',
        headers: {
            'content-type': 'application/json'
        },
    });

    if (httpResp.ok) {
        alert("Peça excluída")
        window.location.reload();
        return
    }
}


// Funções Auxiliares

function toggleSidebar() {
    const sidebar = document.getElementById('sidebar');
    sidebar.classList.toggle('collapsed');
}

function toggleSubMenu(subMenuId) {
    const allSubMenus = document.querySelectorAll('.sub-menu');
    allSubMenus.forEach(subMenu => {
        subMenu.classList.remove('active');
    });
    const currentSubMenu = document.getElementById(subMenuId);
    currentSubMenu.classList.toggle('active');
}

// document.getElementById('addItemEstoqueLojaForm').addEventListener('submit', function(event) {
//     event.preventDefault();
//     let isValid = true;
//
//     const fields = ['txtCodItemLoja', 'txtMarcaLoja', 'txtNomeLoja', 'txtDescricaoLoja', 'txtQtdeLoja', 'txtPrecoLoja'];
//     fields.forEach(function(field) {
//         const input = document.getElementById(field);
//         const error = document.getElementById('error' + field.charAt(3).toUpperCase() + field.slice(4));
//         if (!input.value) {
//             error.style.display = 'block';
//             isValid = false;
//         } else {
//             error.style.display = 'none';
//         }
//     });
//
//     if (isValid) {
//         alert('Peça incluída com sucesso!');
//         $('#addItemEstoqueLoja').modal('hide');
//     }
// });

$('#addItemEstoqueLoja').on('hidden.bs.modal', function () {
    $('.resettable').val('');
    $('.error-message').hide();
});

function blockLetters(input) {
    input.value = input.value.replace(/[^0-9,]/g, '');
}

function formatCurrency(input) {
    let value = input.value.replace(/[^\d,]/g, '');

    if (value.includes(',')) {
        value = value.replace(',', '.');
    }

    if (value) {
        let numericValue = parseFloat(value);
        input.value = numericValue.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2});
    } else {
        input.value = '';
    }
}


window.onload = carregarEstoque;