# ♠️ java-blackjack

블랙잭 미션 저장소

---

## 📌 프로젝트 개요

콘솔 기반으로 즐길 수 있는 블랙잭 게임입니다.

## 💡 주요 용어

- **Blackjack**: 처음 받은 카드 2장의 합이 21인 상태. (2장을 초과해 21을 만든 경우는 포함하지 않음)
- **Bust**: 카드의 점수 합이 21을 초과한 상태. (즉시 패배 처리)
- **Hit / Stand**: 카드를 추가로 1장 더 받을지(Hit), 여기서 턴을 종료할지(Stand) 결정하는 행위.

## 🚀 기능 요구 사항

### 1. 게임 준비 및 카드 분배

- [x] 참여할 플레이어들의 이름을 쉼표(,) 기준으로 입력받는다.
    - [x] 사용자 입력값 유효성 검사
        - [x] 이름 목록이 공백인 경우 예외 처리
        - [x] 이름이 공백인 경우 예외 처리
    - [x] 도메인 규칙 검증
        - [x] 최대 참여 인원(5명)을 초과하는 경우 예외 처리
        - [x] 이름에 한글 또는 영문을 제외한 문자가 포함되는 경우 예외 처리
- [x] 각 플레이어에게 배팅 금액을 입력받는다.
    - [x] 사용자 입력값 유효성 검사
        - [x] 배팅 금액이 공백인 경우 예외 처리
        - [x] 숫자가 아닌 문자를 입력한 경우 예외 처리
        - [x] 배팅 금액이 정수 범위를 초과하는 경우 예외 처리
    - [x] 도메인 규칙 검증
        - [x] 배팅 금액이 0 이하인 경우 예외 처리
- [x] 딜러와 플레이어들에게 무작위로 섞인 카드를 2장씩 나누어 준다.
- [x] 분배된 초기 카드를 출력한다.
    - 딜러는 1장만 공개

### 2. 카드 추가 분배 (Hit / Stand)

- [x] 카드 추가 분배는 다음의 공통 제약 조건 내에서만 가능하다.
    - [x] 도메인 규칙 검증
        - [x] 이미 카드 합계가 21 이상이라 더 이상 받을 수 없는 경우 예외 처리
        - [x] 이미 보유하고 있는 카드와 동일한 카드를 중복해서 추가하려는 경우 예외 처리
- [x] 플레이어는 카드 합계가 21 미만이라면 계속해서 카드를 추가로 받을 수 있다.
    - [x] 사용자 입력값 유효성 검사
        - [x] 대답이 공백인 경우 예외 처리
        - [x] `y` 또는 `n` 이외의 값을 입력한 경우 예외 처리
    - [x] 입력값에 따라 Hit / Stand 의사 결정
        - `y` 입력 시: Hit
        - `n` 입력 시: Stand
- [x] 딜러는 자신의 카드 합계가 16 이하이면 16을 초과할 때까지 무조건 1장을 더 받는다.
    - [x] 도메인 규칙 검증
        - [x] 이미 딜러의 카드 합계가 16을 초과해 더 이상 받을 수 없는 경우 예외 처리
        - [x] 딜러의 카드 합계가 16 이하인 상태로 게임이 종료되는 경우 예외 처리
- [x] 딜러가 카드를 추가로 받았는지 여부를 출력한다.

### 3. 점수 계산 및 승패/수익 판정

- [x] J, Q, K는 10으로 계산한다.
- [x] Ace(A)는 1 또는 11 중 카드 합산 범위(21)를 초과하지 않는 유리한 방향으로 계산한다.
    - [x] Ace가 1장 포함된 경우
        - [x] Ace를 11점으로 계산해도 Bust가 나지 않는다면, Ace를 11점으로 계산
        - [x] Ace를 11점으로 계산 시 Bust가 난다면, Ace를 1점으로 계산
    - [x] Ace가 2장 이상 포함된 경우
        - 2장 이상을 11로 계산하면 무조건 22점 이상으로 Bust가 나므로, 11로 계산할 수 있는 Ace는 최대 1장
        - [x] 1장의 Ace를 11로 계산했을 때 Bust가 난다면, 모든 Ace를 1점으로 계산
- [x] 딜러와 플레이어의 최종 승패 및 조건에 따른 플레이어의 최종 수익을 계산한다.
    - [x] 플레이어가 승리한 경우
        - [x] 플레이어만 Blackjack인 경우: 배팅 금액의 1.5배 수익
        - [x] 딜러만 Bust인 경우: 배팅 금액의 1배 수익
        - [x] 플레이어와 딜러 모두 Bust가 아니고, 플레이어의 점수가 딜러의 점수보다 높은 경우: 배팅 금액의 1배 수익
    - [x] 플레이어가 패배한 경우: 배팅 금액만큼 손실 (-1배)
        - [x] 플레이어가 Bust인 경우
        - [x] 딜러만 Blackjack인 경우
        - [x] 플레이어와 딜러 모두 Bust가 아니고, 플레이어의 점수가 딜러의 점수보다 낮은 경우
    - [x] 무승부인 경우: 수익 0
        - [x] 플레이어와 딜러 모두 Blackjack인 경우
        - [x] 플레이어와 딜러 모두 Bust가 아니고, 플레이어와 딜러의 점수가 동일한 경우
- [x] 딜러의 수익은 플레이어들의 최종 수익 합의 반대로 계산한다.

### 4. 게임 결과 출력

- [x] 모든 참가자의 최종 카드 목록과 점수를 출력한다.
- [x] 딜러와 플레이어들의 최종 수익을 출력한다.

## 🗂️ 클래스 다이어그램

```mermaid
classDiagram
%% 1. 도메인 클래스 선언
    class Game {
        -Deck totalDeck
        -Dealer dealer
        -Players players
        +readyParticipantDecks()
        +drawCardUnderCondition(Participant)
        +generateGameResult() GameResult
    }
    class GameResult {
        -Map~Player, Result~ playerResults
        -Map~Result, Integer~ dealerResult
        +calculate(Dealer, Players)$ GameResult
    }
    class Participant {
        <<abstract>>
        -Name name
        -Cards cards
        +getInitialVisibleCards()*
        +isDrawable()*
    }
    class Name {
        <<record>>
        -String name
    }
    class Player {
    }
    class Dealer {
    }
    class Players {
        -List~Player~ players
    }
    class Cards {
        -List~Card~ cards
        +calculateCardScoreSum() int
    }
    class Deck {
        -List~Card~ totalDeck
    }
    class Card {
        -CardShape cardShape
        -CardContents cardContents
    }
    class CardShape {
        <<enumeration>>
    }
    class CardContents {
        <<enumeration>>
        -String number
        -int score
    }
    class Result {
        <<enumeration>>
        WIN, TIE, LOSS
        +determinePlayerResult(...)$ Result
        +reverse() Result
    }
    class CardShuffleStrategy {
        <<interface>>
        +shuffle(List~Card~)
    }
    class RandomCardShuffleStrategy {
    }

%% 2. 컨트롤러 및 뷰 클래스 선언
    class Main {
    }
    class BlackJackController {
        -InputView inputView
        -OutputView outputView
        -CardShuffleStrategy strategy
        +doGame()
    }
    class InputView {
        -Scanner scanner
    }
    class OutputView {
    }

%% 3. DTO 클래스 선언
    class GameResultDto {
        <<record>>
        -Map~String, Integer~ dealerWinTieLossResult
        -Map~String, String~ playerWinTieLossResults
    }
    class ParticipantDto {
        <<record>>
        -String name
        -List~CardDto~ cards
        -int score
    }
    class CardDto {
        <<record>>
        -String cardShape
        -String cardContentNumber
    }

%% Relationships
%% 도메인 내부 관계
    Game --> Deck
    Game --> Dealer
    Game --> Players
    Game ..> GameResult : generates
    Participant <|-- Player
    Participant <|-- Dealer
    Participant --> Name
    Participant --> Cards
    Players --> Player
    Cards --> Card
    Deck --> Card
    Card --> CardShape
    Card --> CardContents
    GameResult --> Result
    CardShuffleStrategy <|.. RandomCardShuffleStrategy

%% 컨트롤러 및 뷰 관계
    Main --> BlackJackController
    BlackJackController --> InputView
    BlackJackController --> OutputView
    BlackJackController --> CardShuffleStrategy

%% DTO 관계
    GameResultDto --> ParticipantDto
    ParticipantDto --> CardDto
