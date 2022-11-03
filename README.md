# cstv-app-challenge

O desafio compreenderá a construção de um App que mostra uma lista de partidas de CS:GO 🔫  em um determinado período de tempo utilizando a API do PandaScore.

## Você vai encontrar nesse projeto:
* [master](https://github.com/VitorMazzola/cstv-app-challenge/tree/master) | Default branch |
* [develop](https://github.com/VitorMazzola/cstv-app-challenge/tree/develop) | Development branch  |
* Single-activity architecture, usando **[Navigation & UI](https://developer.android.com/guide/navigation)**.
* A camada de apresentação possui **ViewModel** por tela (ou feature).
* Reactive UIs utilizando **[Flow](https://developer.android.com/kotlin/flow)** e **[coroutines](https://kotlinlang.org/docs/coroutines-overview.html)** para operações assíncronas.
* A **camada de dados** com repositories e data sources (Remote) **[Retrofit](https://square.github.io/retrofit/)**.
* A **Camada de domínio** utiliza UseCases para regra de negócio.
* Usa **StateFlow** e **SharedFlow** como soluções de fluxo de dados **[StateFlow e SharedFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow?hl=pt-br)**
* Usa **[Glide](https://github.com/bumptech/glide)** para download de imagens.
* Injeção de depêndencia(DI) [Hilt](https://developer.android.com/training/dependency-injection/hilt-android).


## Screenshots

<img src="screenshots/screenshots.jpeg" alt="Screenshot">


## Abrir projeto no Android Studio

Clonar o repositório:

```
git clone https://github.com/VitorMazzola/cstv-app-challenge.git
```
