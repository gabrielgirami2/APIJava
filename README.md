# APIJava

# NextGen

### Integrantes:
- Felipe Cardoso Torlai - RM: 550263
- Felipe Santos Pinheiro - RM: 550244
- Gabriel Girami de Souza - RM: 98017
- Gustavo Vinhola dos Santos - RM: 98826
- Jean Carlos Oliveira Silva - RM: 550430


## Pré-requisitos

Antes de iniciar o deploy, certifique-se de que você possui:

- Uma **conta no Azure**.
- **Azure CLI** instalada. Para instalar, siga as [instruções oficiais](https://learn.microsoft.com/en-us/cli/azure/install-azure-cli).
- **Git** instalado e configurado (opcional).
- O código da sua **aplicação web** pronto para deploy.

## Passos para o Deploy

### 1. Login no Azure

No terminal, faça o login na sua conta do Azure:

\`\`\`bash
az login
\`\`\`

Isso abrirá uma nova janela de navegador para você realizar o login.

### 2. Crie um Grupo de Recursos

Um grupo de recursos é necessário para organizar seus serviços no Azure.

\`\`\`bash
az group create --name NomeDoGrupo --location eastus
\`\`\`

### 3. Crie o App Service Plan

O App Service Plan define a região, capacidade e custo do seu serviço de aplicação web.

\`\`\`bash
az appservice plan create --name NomeDoPlano --resource-group NomeDoGrupo --sku B1
\`\`\`

O plano de serviço \`B1\` é uma opção básica e econômica.

### 4. Crie o Web App

Agora, crie o Web App dentro do plano de serviço que você acabou de configurar:

\`\`\`bash
az webapp create --resource-group NomeDoGrupo --plan NomeDoPlano --name NomeDaAplicacao --runtime "PYTHON|3.9"
\`\`\`

Substitua o runtime conforme necessário (por exemplo, \`NODE|16-lts\`, \`DOTNET|6.0\`, etc.).

### 5. Configure o Deploy com Git

Se você planeja usar Git para o deploy, configure o Web App para aceitar deploys Git:

\`\`\`bash
az webapp deployment source config-local-git --name NomeDaAplicacao --resource-group NomeDoGrupo
\`\`\`

Este comando retorna a URL do repositório Git onde você pode fazer push do código.

### 6. Realize o Deploy

Se o deploy for via Git, faça o commit e o push do seu código para o repositório fornecido:

\`\`\`bash
git remote add azure <URL do repositório>
git push azure main
\`\`\`

### 7. Acesse o Web App

Após o deploy, você pode acessar a aplicação no navegador usando a seguinte URL:

\`\`\`
https://NomeDaAplicacao.azurewebsites.net
\`\`\`

### 8. Monitoramento e Logs

Para visualizar logs de execução da aplicação:

\`\`\`bash
az webapp log tail --name NomeDaAplicacao --resource-group NomeDoGrupo
\`\`\`

## Etapas Finais

Agora seu Web App está no ar! Monitore seu desempenho no portal do Azure e faça ajustes conforme necessário.

### Links Úteis

- [Documentação do Azure Web App](https://learn.microsoft.com/en-us/azure/app-service/)
- [CLI do Azure](https://learn.microsoft.com/en-us/cli/azure/)
"""
