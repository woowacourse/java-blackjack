# java-blackjack

블랙잭 미션 저장소

# 프로젝트 다이어그램

![프로젝트 다이어그램](./diagram.png)

---

# 구현할 기능 목록

## 🗂️ Domain

### Rank
- [x] 2부터 10, 잭, 퀸, 킹, 에이스

### Suit
- [x] 스페이드, 하트, 다이아몬드, 클로버

### Card
- [x] `Rank`와 `Suit` 조합 1개 저장
- [x] `Card` 점수 계산

### Cards
- [x] `Card` 묶음 저장
- [x] `Card` 추가
- [x] `Card` 묶음의 점수 총합 계산
  - [X]  `ACE` 카드는 1과 11 중 유리한 점수로 적용
- [x] 다른 `Cards`와 비교한 `GameStatus` 계산
- [x] `Card` 묶음 섞기
- [x] 카드 1장 뽑기

### CardDeck
- [X] 트럼프 카드 1세트에 해당하는 `Cards` 저장
- [x] `Cards` 섞기
- [x] 카드 1장 뽑기

### Participant
- [x] 참여자의 이름 저장
- [x] 참여자가 보유한 `Cards` 저장
- [x] `Card` 추가 가능 여부 판단
- [x] 참여자가 보유한 `Cards`에 `Card` 추가
- [x] 다른 `Participant`와 비교한 `GameStatus` 계산

### Dealer
- [x] 카드 추가 여부 판단
  - [x] 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드 추가

### Player
- [x] 카드 추가 여부 판단
  - [x] 받은 카드가 21을 넘지 않은 경우 계속 카드 추가 가능

### Game
- [x] `CardDeck` 저장
- [x] 전체 `Participant` 저장

- [x] 참여자 등록
  - [x] 플레이어 이름 유효성 검증
- [x] 참여자에게 최초로 카드 2장 배부
- [x] 참여자에게 추가로 카드 1장 배부
- [x] 참여자의 게임 최종 승패 결과 계산

### GameStatus
- [x] 게임 최종 승패(승, 무, 패)

### GameResult
- [x] 게임 최종 승패(승, 무, 패)와 횟수 저장

### ParticipantCardsDto
- [x] 참여자의 이름, 참여자가 보유한 `Cards`, `Cards`의 총 합

### GameResultDto
- [x] 참여자의 이름, 게임 최종 승패(승, 무, 패)와 횟수

---

## 👀 View

### InputView
- [x] 게임 참여자들의 이름 입력
- [x] 플레이어가 한 장의 카드를 더 받는 여부 입력
  - [x] `y`가 입력되면 `true`, `n`가 입력되면 `false` 반환

### OutputView
- [X] 오류 메시지 출력
- [x] 최초 카드 배부 후 전체 참여자의 보유 카드 출력
- [X] 카드를 추가로 받은 참여자의 보유 카드 출력
- [x] 딜러의 카드 추가 여부 출력
- [x] 최종 승패 출력
  - [x] 전체 참여자의 보유 카드 및 결과 출력

---

## 🕹️ Controller

### GameController
- [X] 참여자 등록 요청
  - [X] 딜러 등록 요청
  - [X] 플레이어 등록 요청

- [X] 참여자들에게 최초로 카드 배부 요청
- [x] 참여자들에게 카드 추가 배부 요청
  - [x] 참여자들에게 카드 추가 가능 여부 확인 요청

---

## ⚠️Exception

### ExceptionHandler
- [X] 잘못된 값 입력시 예외 메시지 출력 및 재입력

### ErrorException
- [x] 잘못된 입력에 대한 예외 메시지를 오류 메시지로 생성
