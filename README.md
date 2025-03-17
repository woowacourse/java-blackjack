# java-blackjack

블랙잭 미션 저장소

---

# 구현할 기능 목록

- [X] 플레이어들의 이름을 입력받는다.
- [X] 모든 플레이어들에게 배팅 금액을 각각 입력받는다.
- [X] 딜러와 플레이어들에게 카드를 2장씩 나눠준다.
- [X] 딜러가 받은 첫 번째 카드를 공개한다.
- [X] 플레이어들이 받은 2장의 카드를 모두 공개한다.
- 2장의 카드 점수의 합이
    - [ ] 21이면: blackjack
    - [X] 21보다 크면: bust
    - [X] 21보다 작으면: 카드 1장을 더 받을 수 있다.
        - [X] 카드의 합이 21 미만인 플레이어들에게 카드 추가 여부를 입력받는다.
            - [X] y가 입력되면(hit): 플레이어에게 카드를 1장 추가한다.
            - [X] n이 입력되면(stand): 카드를 주지 않고 물어보기를 끝낸다.
        - [X] 플레이어가 가진 모든 카드를 공개한다.
        - [X] 플레이어의 카드 점수가 21을 넘지 않으면 카드 추가 여부를 묻는 과정을 반복한다.
- [X] 딜러의 카드 추가 여부를 출력한다.
- [X] 딜러와 플레이어의 모든 카드를 공개하고 점수를 출력한다.
- [ ] 플레이어들의 최종 승패에 따라 배팅 금액을 배분한다.
    - blackjack: +(배팅 금액 * 1.5)
        - 처음 받은 2장의 카드 점수의 합이 21인 경우
    - 승: +(배팅 금액)
        - 딜러가 bust인 경우 그 시점까지 남아있던 플레이어들
        - 플레이어, 딜러 모두 bust가 아니고 플레이어의 점수가 높을 경우
    - 패: -(배팅 금액)
        - 플레이어가 bust인 경우
        - 플레이어, 딜러 모두 bust가 아니고 플레이어의 점수가 낮을 경우
    - 무: 0
        - 딜러와 플레이어가 모두 blackjack인 경우
        - 플레이어, 딜러 모두 bust가 아니고 점수가 동일할 경우
- [ ] 딜러와 플레이어들의 최종 수익을 출력한다.
    - [ ] 딜러의 수익: -(플레이어들의 수익의 총합)

# 실행 결과 예시

```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

pobi의 배팅 금액은?
10000

jason의 배팅 금액은?
20000

딜러와 pobi, jason에게 2장을 나누었습니다.
딜러: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
pobi카드: 2하트, 8스페이드, A클로버
jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

딜러는 16이하라 한장의 카드를 더 받았습니다.

딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17

## 최종 수익
딜러: 10000
pobi: 10000 
jason: -20000
```

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
