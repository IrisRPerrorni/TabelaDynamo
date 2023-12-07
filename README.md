# Estudando DynamoDb

DynamoDb é um banco de dados NoSQL (não relacional) , que permite o gerenciamento de uma enorme quantidade de dados com alta velocidade e baixa latência.
o DynamoDB é um banco de chave/valor, e essa chave pode ser composta. A chave de partição (partition key, em inglês) faz parte da nossa chave primária que identificará um item.
Precisamos ter sempre uma identificação para o item. Assim como no banco de dados relacional, temos uma chave primária no DynamoDB, ela pode ser somente de partição, ou a junção de uma chave de partição com uma chave de classificação (sort key). Esta escolha é opcional.


O codigo desse repositório é somente uma aplicação basica , sem tratamentos de erros e validações para entender como funciona a tabela DynamoDb em código.  
