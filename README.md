# 🧠 Algoritmo KNN em Java

## 📘 Descrição

Projeto da disciplina de *Machine Learning* (Projeto 2A), com o objetivo de compreender e implementar o algoritmo **k-Nearest Neighbors (kNN)** do zero em Java.

O programa:
- Lê uma base de dados.
- Classifica objetos com base nessa base.
- Avalia a acurácia do algoritmo utilizando uma **matriz de confusão**.

---

## 🎯 Objetivo da Atividade

Atividade proposta na aula de **Classificação** com as seguintes instruções:

- Implementar o algoritmo **kNN**.
- Utilizar a base de dados `sintetica.m` com seus respectivos rótulos para treinamento.
- Prever os rótulos de 5 objetos de entrada (armazenados em `objetos.m`) e compará-los com os rótulos reais (`rotulosObjeto.m`).

---

## 💻 Tecnologias Utilizadas

- ✅ **Linguagem:** Java `22.0.1`
- ✅ **Biblioteca gráfica:** JavaFX `21.0.7`

---

## 📁 Estrutura do Projeto


```
📦Knn
├── .gitignore
├── README.md
├── 📁src
│   ├── App.java
│   ├── 📁controller
│   │   └── Controller.java
│   ├── 📁data
│   │   ├── Base\_sintética.txt
│   │   ├── Objetos.txt
│   │   └── 📁rotulos
│   │       ├── Base\_sintetica\_rotulos.txt
│   │       └── Objetos\_rotulos.txt
│   ├── grafico.fxml
│   ├── 📁models
│   │   ├── Knn.java
│   │   └── TabelaMatrizConfusao.java
│   └── 📁util
│       ├── LeitoraDeDados.java
│       └── LeitorDeTxt.java

```

---

## ⚙️ Instalação e Execução

### 1. Pré-requisitos
- [ ] Java `22.0.1` instalado.
- [ ] JavaFX SDK `21.0.7` baixado e extraído em alguma pasta local.

### 2. Clonar o repositório
```bash
git clone https://github.com/DevolperGdSL/Knn.git
```

### 3. Abrir no Visual Studio Code

### 4. Configurar o `launch.json`

Substitua `CAMINHO_PARA_LIB` pelo caminho completo da pasta `lib` do JavaFX:

```json
{
    "type": "java",
    "request": "launch",
    "name": "Launch JavaFX App",
    "mainClass": "App",
    "vmArgs": "--enable-preview --module-path CAMINHO_PARA_LIB --add-modules javafx.controls,javafx.fxml -Djava.library.path=CAMINHO_PARA_LIB -Dprism.order=sw -Dprism.verbose=true"
}
```

### 5. Configurar o `settings.json`

Troque `CAMINHO_PARA_PASTA` pelo caminho da pasta pai do JavaFX:

```json
{
    "java.project.sourcePaths": [
        "src"
    ],
    "java.project.outputPath": "bin",
    "java.project.referencedLibraries": [
        "CAMINHO_PARA_PASTA/javafx-sdk-21.0.7/lib/javafx.base.jar",
        "CAMINHO_PARA_PASTA/javafx-sdk-21.0.7/lib/javafx.controls.jar",
        "CAMINHO_PARA_PASTA/javafx-sdk-21.0.7/lib/javafx.fxml.jar",
        "CAMINHO_PARA_PASTA/javafx-sdk-21.0.7/lib/javafx.graphics.jar",
        "CAMINHO_PARA_PASTA/javafx-sdk-21.0.7/lib/javafx.media.jar",
        "CAMINHO_PARA_PASTA/javafx-sdk-21.0.7/lib/javafx.swing.jar",
        "CAMINHO_PARA_PASTA/javafx-sdk-21.0.7/lib/javafx.web.jar"
    ]
}
```

### 6. Executar o projeto

* Pressione `F5` no VS Code para compilar e executar o projeto.

---

## 🐧 Compatibilidade
⚠️ Este projeto foi desenvolvido e testado em sistemas Linux.
* Os caminhos utilizados nos arquivos launch.json e settings.json estão no formato Unix (`/`).
* Se estiver utilizando Windows, substitua as barras (`/`) por barras invertidas (`\\`) nos caminhos dos arquivos e nos .json.

## 📊 Resultado Esperado

* A aplicação deve ler a base de dados e classificar os objetos.
* Exibe a matriz de confusão comparando os rótulos reais com os previstos.

---

## 📝 Observações

* Os arquivos `.txt` foram adaptados de arquivos `.m` originalmente utilizados no Octave/Matlab.
* Para visualização dos resultados, é utilizada uma interface gráfica em JavaFX.

---

## 📎 Licença

Este projeto é acadêmico e não possui licença de uso. Uso livre para fins educacionais.

---
