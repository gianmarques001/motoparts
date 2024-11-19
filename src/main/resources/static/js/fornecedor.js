var id = null;

// document.getElementById('cadastroForm').addEventListener('submit', async function enviarDadosFornecedor(evt) {
//     evt.preventDefault();
//     const form = document.getElementById('cadastroForm');
//
//     let dados;
//
//     // admin e cliente
//     if (form.tipoUser.value === 0 || form.tipoUser.value === 1) {
//         dados = {
//             email: form.login.value,
//             nome: form.nome.value,
//             senha: form.senha.value,
//             telefone: form.telefone.value,
//             endereco: form.endereco.value,
//             numero: form.numero.value,
//             cep: form.cep.value,
//             identificador: form.cpfCnpj.value,
//             tipo: form.tipoUser.value,
//         }
//     } else {
//
//         // fornecedor
//         dados = {
//             email: form.login.value,
//             nome: form.nome.value,
//             senha: form.senha.value,
//             telefone: form.telefone.value,
//             endereco: form.endereco.value,
//             numero: form.numero.value,
//             cep: form.cep.value,
//             identificador: form.cpfCnpj.value,
//             tipo: form.tipoPessoa.value,
//            // acesso: form.tipoUser.value,
//
//         };
//     }
//     console.log(JSON.stringify(dados));
//
//     const httpResp = await fetch('/api/motoparts/fornecedor', {
//         method: 'post',
//         headers: {
//             'content-type': 'application/json'
//         },
//         body: JSON.stringify(dados)
//     });
//     if (!httpResp.ok) {
//         if (httpResp.status !== 400) {
//             alert('Erro no envio dos dados - ' + httpResp.status);
//             return;
//         }
//         const erros = await httpResp.json();
//         for (const erro of erros.errors) {
//             const mensagem = erro.defaultMessage;
//             const field = erro.field;
//             console.log(mensagem)
//         }
//     }
//     alert('Sucesso');
//
//     document.getElementById('cadastroForm').reset();
//
// });


document.getElementById('cadastroForm').addEventListener('submit', function enviarDados(evt) {
    evt.preventDefault();
    if (id === null) {
        salvarDadosUsuario();
    } else {
        editarDadosUsuario();
    }
});


async function salvarDadosUsuario() {
    const form = document.getElementById('cadastroForm');

    let dados;

    // admin = 0
    // funcionario = 1
    // fornecedor = 2

    dados = {
        email: form.login.value,
        nome: form.nome.value,
        senha: form.senha.value,
        telefone: form.telefone.value,
        endereco: form.endereco.value,
        numero: form.numero.value,
        cep: form.cep.value,
        identificador: form.cpfCnpj.value,
        tipo: form.tipoUser.value,


    }
    console.log("DADOS " + JSON.stringify(dados))

    if (dados.tipo == 0 || dados.tipo == 1) {
        console.log("Dentro do if")
        const httpResp = await fetch('/api/motoparts/cliente', {
            method: 'post',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(dados)
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
        alert('Sucesso ao salvar cliente');
    } else {
        console.log("Dentro do if fornecedor")
        const httpResp = await fetch('/api/motoparts/fornecedor', {
            method: 'post',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(dados)
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
        alert('Sucesso ao salvar fornecedor');
    }


    //document.getElementById('cadastroForm').reset();

}

async function editarDadosUsuario() {
    console.log("Editar" + id)
    const form = document.getElementById('cadastroForm');

    let tipo = form.tipoUser.value;

    let dados = {
        email: form.login.value,
        senha: form.senha.value,
        nome: form.nome.value,
        telefone: form.telefone.value,
        endereco: form.endereco.value,
        numero: form.numero.value,
        cep: form.cep.value,
        identificador: form.cpfCnpj.value,
        tipo: form.tipoUser.value,
    }


    if (tipo == 0 || tipo == 1) {
        const httpResp = await fetch(`/api/motoparts/cliente/${id}`, {
            method: 'PUT',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(dados)
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

        alert('Sucesso ao editar cliente ');
        document.getElementById('cadastroForm').reset();


    } else {
        const httpResp = await fetch(`/api/motoparts/fornecedor/${id}`, {
            method: 'PUT',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(dados)
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
        alert('Sucesso ao editar fornecedor');
        document.getElementById('cadastroForm').reset();

    }


}


async function carregarUsuarios() {


    const resposta = await fetch('/api/motoparts/usuario');
    console.log(resposta);

    if (!resposta.ok) {
        throw new Error('Erro ao carregar os dados da API.');
        return;
    }
    const dados = await resposta.json();

    console.log(dados);

    const tabelaCorpo = document.getElementById('estoqueUsuario');

    tabelaCorpo.innerHTML = '';

    if (dados && dados.length > 0) {
        // Iterar sobre cada item e criar a linha da tabela
        dados.forEach(item => {
            // Criar uma nova linha (<tr>)
            const linha = document.createElement('tr');


            // admin = 0
            // funcionario = 1
            // fornecedor = 2
            let tipo = "";
            if (item.tipo == 0) {
                tipo = "Admin";
            } else if (item.tipo == 1)
                tipo = "Funcionario";
            else {
                tipo = "Fornecedor";
            }

            const colunas = [
                item.id,
                tipo,
                item.nome,
                item.identificador,
                item.email,
                item.endereco,
                item.numero,
                item.cep,
                item.telefone,

            ];

            // Para cada coluna, criar uma célula e adicionar à linha
            colunas.forEach(dado => {
                const celula = document.createElement('td');
                celula.textContent = dado;
                linha.appendChild(celula);
            });

            //Célula para o ícone de editar (ex: lápis)
            const editarCelula = document.createElement('td');
            editarCelula.innerHTML = '<a href="#" style="color: #343a40"> <i class="fas fa-pencil-alt"></i></a>'; // Ícone de lápis
            editarCelula.addEventListener('click', () => editarItem(item.id)); // Adicionar ação de editar
            linha.appendChild(editarCelula);


            const visualizarCelula = document.createElement('td');
            visualizarCelula.innerHTML = '<a href="#" style="color: #343a40"> <i class="fas fa-id-card"></i></a>'; // Ícone de id
            visualizarCelula.addEventListener('click', () => visualizarItem(item.id)); // Ação para visualizar
            linha.appendChild(visualizarCelula);


            const excluirCelula = document.createElement('td');
            excluirCelula.innerHTML = '<a href="#" style="color: #343a40"><i class="fas fa-trash-alt"></i></a>'; // Ícone de lixeira
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


async function editarItem(idFornecedor) {


    //Pegar informações do fornecedor
    const resposta = await fetch(`/api/motoparts/fornecedor/${idFornecedor}`);
    const dadosFornecedor = await resposta.json();

    // Define o id do Fornecedor para variável global
    id = idFornecedor;
    preencherModalComDados(dadosFornecedor);

    // Abre o modal
    var modal = new bootstrap.Modal(document.getElementById('modalCadastro'));
    modal.show();
}


async function excluirItem(id) {

    const httpResp = await fetch(`/api/motoparts/fornecedor/${id}`, {
        method: 'delete',
        headers: {
            'content-type': 'application/json'
        },
    });

    if (httpResp.ok) {
        alert("Fornecedor excluído")
        window.location.reload();
        return
    }
}


// Funções Auxiliares

function preencherModalComDados(dados) {

    //Campos que não irão aparecer
    document.getElementById('tipoUser').value = dados.tipo;
    document.getElementById('tipoPessoa').readOnly = true;
    document.getElementById('tipoUser').readOnly = true
    document.getElementById('tipoPessoa').readOnly = true;


    document.getElementById('senha').value = dados.senha || '';
    document.getElementById('endereco').value = dados.endereco || '';
    document.getElementById('numero').value = dados.numero || '';
    document.getElementById('cep').value = dados.cep || '';
    document.getElementById('telefone').value = dados.telefone || '';


    // Campos bloqueados para edição
    document.getElementById('nome').value = dados.nome || ''; // bloqueado
    document.getElementById('cpfCnpj').value = dados.identificador || ''; // bloqueado
    document.getElementById('login').value = dados.email || '';  // bloqueado


    document.getElementById('nome').readOnly = true;
    document.getElementById('cpfCnpj').readOnly = true;
    document.getElementById('login').readOnly = true;


}

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

// Função para adicionar usuário ao array e exibir na tabela
// document.getElementById('cadastroForm').addEventListener('submit', function (event) {
//     event.preventDefault();
//     let isValid = true;
//
//     // Limpar mensagens de erro
//     $('.error-message').text('');
//
//     // Verificar campos obrigatórios
//     $('#cadastroForm [required]').each(function () {
//         if (!this.value.trim()) {
//             isValid = false;
//             const errorId = this.id + 'Error';
//             $('#' + errorId).text('Este campo é obrigatório.');
//         }
//     });
//
//     // Validar CPF/CNPJ de acordo com o Tipo de Usuário
//     const tipoUser = document.getElementById('tipoUser').value;
//     const cpfCnpj = document.getElementById('cpfCnpj').value.trim();
//
//     if (tipoUser === '0' || tipoUser === '1') { // Admin ou Funcionário
//         if (!cpfCnpj) {
//             isValid = false;
//             $('#cpfCnpjError').text('CPF é obrigatório.');
//         } else if (!validateCPF(cpfCnpj)) { // Validar CPF
//             isValid = false;
//             $('#cpfCnpjError').text('CPF inválido.');
//         }
//     } else if (tipoUser === '2') { // Fornecedor
//         if (!cpfCnpj) {
//             isValid = false;
//             $('#cpfCnpjError').text('CNPJ é obrigatório.');
//         } else if (!validateCNPJ(cpfCnpj)) { // Validar CNPJ
//             isValid = false;
//             $('#cpfCnpjError').text('CNPJ inválido.');
//         }
//     }
// });

// Função para manipular os campos Tipo de Usuário e CPF/CNPJ
function handleTipoUserChange() {
    const tipoUser = document.getElementById('tipoUser').value;
    const tipoPessoa = document.getElementById('tipoPessoa');
    const cpfCnpj = document.getElementById('cpfCnpj');
    const telefone = document.getElementById('telefone');
    const cep = document.getElementById('cep');

    if (tipoUser === "2") {
        tipoPessoa.disabled = false;
        cpfCnpj.placeholder = "Digite o CPF ou CNPJ";
        cpfCnpj.value = "";
        cpfCnpj.removeAttribute("maxlength");
        cpfCnpj.oninput = formatarCPF;
    } else {
        tipoPessoa.disabled = true;
        tipoPessoa.selectedIndex = 0;
        cpfCnpj.value = "";
        cpfCnpj.placeholder = "Digite o CPF";
        cpfCnpj.setAttribute("maxlength", "14");
        cpfCnpj.oninput = formatarCPF;
        cpfCnpj.required = true;
    }

    // Formatar CEP e telefone apenas se o tipo for fornecedor (tipoUser = 2)
    if (tipoUser === "2") {
        telefone.oninput = formatarTelefone;
        cep.oninput = formatarCEP;
    } else {
        telefone.oninput = formatarTelefone;
        cep.oninput = formatarCEP;
    }
}

function handleTipoPessoaChange() {
    const tipoPessoa = document.getElementById('tipoPessoa').value;
    const cpfCnpj = document.getElementById('cpfCnpj');
    cpfCnpj.value = "";

    if (tipoPessoa === "1") {
        cpfCnpj.placeholder = "Digite o CPF";
        cpfCnpj.setAttribute("maxlength", "14");
        cpfCnpj.oninput = formatarCPF;
    } else if (tipoPessoa === "2") {
        cpfCnpj.placeholder = "Digite o CNPJ";
        cpfCnpj.setAttribute("maxlength", "18");
        cpfCnpj.oninput = formatarCNPJ;
    }
}

// Função para validar CPF
function validateCPF(cpf) {
    cpf = cpf.replace(/[^\d]+/g, ''); // Remover qualquer caractere não numérico

    if (cpf.length !== 11) {
        return false;
    }

    // Lógica de validação do CPF (fórmula padrão)
    let sum = 0;
    let remainder;

    for (let i = 1; i <= 9; i++) {
        sum += parseInt(cpf.substring(i - 1, i)) * (11 - i);
    }

    remainder = sum % 11;
    if (remainder === 10 || remainder === 11) {
        remainder = 0;
    }

    if (remainder !== parseInt(cpf.substring(9, 10))) {
        return false;
    }

    sum = 0;
    for (let i = 1; i <= 10; i++) {
        sum += parseInt(cpf.substring(i - 1, i)) * (12 - i);
    }

    remainder = sum % 11;
    if (remainder === 10 || remainder === 11) {
        remainder = 0;
    }

    if (remainder !== parseInt(cpf.substring(10, 11))) {
        return false;
    }

    return true;
}

// Função para validar CNPJ
function validateCNPJ(cnpj) {
    cnpj = cnpj.replace(/[^\d]+/g, '');

    if (cnpj.length !== 14) {
        return false;
    }

    let sum = 0;
    let remainder;

    for (let i = 1; i <= 12; i++) {
        sum += parseInt(cnpj.charAt(i - 1)) * (13 - i);
    }

    remainder = sum % 11;
    if (remainder === 0 || remainder === 1) {
        if (cnpj.charAt(12) !== '0') {
            return false;
        }
    } else if (cnpj.charAt(12) !== (11 - remainder).toString()) {
        return false;
    }

    sum = 0;
    for (let i = 1; i <= 13; i++) {
        sum += parseInt(cnpj.charAt(i - 1)) * (14 - i);
    }

    remainder = sum % 11;
    if (remainder === 0 || remainder === 1) {
        if (cnpj.charAt(13) !== '0') {
            return false;
        }
    } else if (cnpj.charAt(13) !== (11 - remainder).toString()) {
        return false;
    }

    return true;
}

// Função para formatar CPF
function formatarCPF(event) {
    let input = event.target;
    let value = input.value.replace(/\D/g, ''); // Remove tudo que não for número

    if (value.length <= 11) {
        value = value.replace(/(\d{3})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
    }

    input.value = value;
}

// Função para formatar CNPJ
function formatarCNPJ(event) {
    let input = event.target;
    let value = input.value.replace(/\D/g, ''); // Remove tudo que não for número

    if (value.length <= 14) {
        value = value.replace(/(\d{2})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d)/, '$1/$2');
        value = value.replace(/(\d{4})(\d{1,2})$/, '$1-$2');
    }

    input.value = value;
}

// Função para formatar CEP
function formatarCEP(event) {
    let input = event.target;
    let value = input.value.replace(/\D/g, ''); // Remove tudo que não for número

    if (value.length <= 8) {
        value = value.replace(/(\d{5})(\d)/, '$1-$2');
    }

    input.value = value;
}

// Função para formatar Telefone
function formatarTelefone(event) {
    let input = event.target;
    let value = input.value.replace(/\D/g, ''); // Remove tudo que não for número

    if (value.length <= 11) {
        value = value.replace(/^(\d{2})(\d)/, '($1) $2');
        value = value.replace(/(\d{5})(\d)/, '$1-$2');
    }

    input.value = value;
}

// Chama a função de manipulação ao mudar o Tipo de Usuário
document.getElementById('tipoUser').addEventListener('change', handleTipoUserChange);
document.getElementById('tipoPessoa').addEventListener('change', handleTipoPessoaChange);

// Inicializar a página com a função chamada
window.onload = function () {
    carregarUsuarios();
    handleTipoUserChange();
    document.getElementById('cadastroForm').reset();
};
