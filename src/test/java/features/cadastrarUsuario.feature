# language: pt
Funcionalidade: Cadastro de usuário

  Contexto:
    #Dado que a API do Serverest está disponível

  Esquema do Cenário: Cadastro de novo usuário
    Dado que eu tenho os dados de um novo usuário "<tipoUsuario>" válido
    Quando eu envio uma requisição para cadastrar o usuário
    Então o status da resposta deve ser 201
    E devo receber no response os dados de acordo com o schema "cadastroUsuarioSucessoSchema"
    E a mensagem retornada deve ser "Cadastro realizado com sucesso"

    Exemplos:
      | tipoUsuario |
      | admin       |
      | comum       |

  Cenário: Cadastro de usuário com campos vazios
    Dado que eu tenho um usuário sem os campos preenchidos
    Quando eu envio uma requisição para cadastrar o usuário
    Então o status da resposta deve ser 400



