## Requisitos
- [JDK 1.8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html "download")
- [Eclipse (2018-12 ou posterior)](https://www.eclipse.org/downloads/ "download") ou outra IDE de sua preferência com suporte a Maven

## Cenário
Este projeto contém o back-end de parte de um sistema legado para e-commerce. Trata-se de uma API RESTful implementada utilizando Spring com arquitetura dividida em três camadas:
- Controller
- Service
- Repository

O projeto está configurado para utilizar uma base de dados [H2](http://www.h2database.com/html/main.html) embutida cuja persistência se dá em arquivo (caminho do projeto/database). Entretanto, para testes integrados, cria-se uma nova base de dados apenas em memória, isto é, não transferida para o disco. 

O sistema possui as entidades:
* `br.com.neolog.interview.product.Product`: produto da loja. Possui descrição, nome resumido, pertence a uma categoria e, finalmente informações de preço, peso e volume.
Como este sistema não está restrito a apenas uma região geográfica, as unidades de preço, peso e volume são arbitrárias, tal que sejam representáveis em número inteiro. Por exemplo: no Brasil o preço seria expresso em centavos, peso em gramas e volume em centímetros cúbicos. Não é esperado que o sistema seja utilizado para quantidades muito altas e, ao mesmo tempo, muito pequenas; 
* `br.com.neolog.interview.category.ProductCategory`: Categoria de produto para facilitar buscas;
* `br.com.neolog.interview.stockitem.StockItem`: Item de estoque, armazena a quantidade disponível para venda de cada produto;

Além de fornecer os fluxos de cadastro destas 3 entidades, o sistema possui a funcionalidade de sugerir itens do estoque baseado em um preço limite máximo. Este serviço é disponibilizado através do _entry-point_: `br.com.neolog.interview.suggestion.SuggestionController`. Com este serviço, o usuário recebe uma lista de itens e quantidades que poderia adquirir dado um limite de orçamento.

## Enunciado
Surge a necessidade de negócio de disponibilizar _também_ serviços de recomendação a partir dos critérios:
* Peso máximo
* Volume máximo

Cada critério é analisado isoladamente, isto é, não será possível _ainda_ sugerir itens considerando múltiplos critérios simultaneamente na mesma _request_. Pode-se admitir que as unidades de medida utilizadas no serviço são as mesmas utilizadas para cadastrar os produtos na base de dados.

O sistema deve manter a _API_ atual para o serviço de recomendação por preço máximo.

É desejável que os novos fluxos estejam cobertos com testes integrados.

## Tudo funciona?
De:[william.jose@totvs.com.br] (mailto:william.jose@totvs.com.br)
Envie seu PR para [rafael.goes@totvs.com.br](mailto:rafael.goes@totvs.com.br).


