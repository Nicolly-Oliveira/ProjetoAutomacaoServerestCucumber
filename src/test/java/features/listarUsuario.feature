# language: pt
Funcionalidade: Listar usuários

  Contexto:
    #Dado que a API do Serverest está disponível
    #E que eu tenha ao menos um usuario cadastrado

  Cenário: Listar usuário com sucesso sem parametros
    Quando eu envio uma requisição para listar os usuários
    Então o status da resposta deve ser 200
    E devo receber no response os dados de acordo com o schema "listarUsuarioSucessoSchema"


