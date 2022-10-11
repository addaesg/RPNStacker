## [Task 03 - RPNStacker Automatic/Regex Scanning]
### 1. Requisitos da Atividade

> Evoluir o projeto da Task 02 para implementar uma feature de scanning [com Java Regex]:
> 1. No geral, nosso Programa le um arquivo com a expressao em RPN e devolve a expressao avalliada.
> 2. A feature de scanning deve continuar a retornar uma lista de tokens [como ja realizada na Task 01], porém, agora, utilizando regex [regular expressions com Java].
> 3. A partir dessa lista de tokens a interpretacao das expressoes eh realizada com uma pilha.
> 4. A feature de scanning deve continuar retornar um erro caso nao reconheca um "num" [numero] ou "op" [operator].
> 5. Implementar as features de regex em uma classe chamada Regex.

### 2. Instruções:
> Para Rodar o programa:
> 1. Na pasta `src` da task2
> 2. Rode `javac Main.java`
> 3. Rode `java Main`

> Ao rodar Você poderá escolher entre rodar o arquivo `teste.stk` ou algum outro arquivo.
> * Digite `1` para evaliar a expressão que está no arquivo `teste.stk`.
> * Digite `2` para especificar o ***path absoluto*** do arquivo `.stk` que você quer evaliar.


### 3. Info sobre o programa
> * Pode escolher entre usar o arquivo _teste.stk_ ou dar o **path absoluto** de algum outro arquivo
> * Operadores Válidos = (+, -, *, /, ^)
> * A lista de tokens gerados é impressa antes do resultado.
> * Uma mensagem de erro é impressa quando é inserido um caractere inválido ou uma expressão inválida/incompleta. 

