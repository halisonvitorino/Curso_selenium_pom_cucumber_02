# language: pt
Funcionalidade: Logar na loja virtual

  Esquema do Cenario: Acesso o site com sucesso
    Dado o usuario na pagina de login da loja
    Quando insere seu usuario <usuario>
    Quando insere sua senha <senha>
    E aciona o botao de login
    Entao o usuario ve o texto "Experience the difference" na pagina

    Exemplos:
      | usuario           | senha    |
      | "halisonvitorino" | "123456" |
