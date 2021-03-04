# java-blackjack

블랙잭 게임 미션 저장소

## 기능 요구사항

### Domain(TDD)

- [x] Card
    - [x] 미리 분배할 카드들을 캐싱 한다.
        - [x] 카드들을 섞는다.
    - [x] Suit 와 Number 상태값을 가진다.

- [x] Suit, Face
    - [x] enum을 사용하여 카드에서 사용하는 정보를 관리한다.

- [x] Cards (유저가 가지고 있는 카드 묶음)
    - [x] 현재 스코어를 계산한다.
    - [x] 21점이 넘으면, Bust 임을 확인한다.

- [x] Player
    - [x] Challenger
        - [x] Cards 상태값을 가진다.
        - [x] Name, isBust 상태값을 가진다.
        - [x] 초기 카드를 받는다. (2장)
        - [x] 카드를 추가로 받는다.
        - [x] Bust 여부 계산한다.
        - [x] 딜러와의 스코어를 계산하여 승자 판별한다.
    - [x] Dealer
        - [x] Cards 상태값을 가진다.
        - [x] 초기 카드를 받는다. (2장)
        - [x] 카드를 추가로 받는다.
        - [x] Player와의 승자 판별한다.
        - [x] Bust 여부 계산한다.


- [x] Challengers (게임에 참여한 플레이어 그룹)

- [x] Name

### Controller, Service, View

- [ ] InputView
    - [ ] 인풋에 관련된 입,출력을 관리한다.
    - [ ] 인풋을 split해서 문자열 리스트로 반환한다.
    - [ ] 게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리) pobi,jason
    - [ ] pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n) -> 검증도 InputView에서 담당한다. y pobi카드: 2하트, 8스페이드, A클로버  
      pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
      n jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
      n jason카드: 7클로버, K스페이드
      (jason카드: 7클로버, K스페이드 이부분 출력 로직? Controller는 도메인 정보를 모르게, Service에서 도메인과 통신하게 설계하고 싶은데, View에서 출력을 할때 흐름이 어떻게?)

- [ ] OutputView
    - [ ] 결과 관련된 출력을 담당한다.
    - [ ] 딜러와 pobi, jason에게 2장의 나누었습니다. 딜러: 3다이아몬드 pobi카드: 2하트, 8스페이드 jason카드: 7클로버, K스페이드
    - [ ] 딜러는 16이하라 한장의 카드를 더 받았습니다. 딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20 pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21 jason카드:
      7클로버, K스페이드 - 결과: 17 최종 승패 딜러: 1승 1패 pobi: 승 jason: 패

- [ ] BlackJackController
- [ ] BlackJackService
    - [ ] 

  