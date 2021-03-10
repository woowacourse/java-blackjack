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

- [x] InputView
    - [x] 인풋에 관련된 입,출력을 관리한다.
    - [x] 인풋을 split해서 문자열 리스트로 반환한다.
    - [x] 챌린저로부터, 카드를 더 받을지 여부를 확인한다.

- [x] OutputView
    - [x] 딜러와 pobi, jason에게 2장의 나누었습니다. 딜러: 3다이아몬드 pobi카드: 2하트, 8스페이드 jason카드: 7클로버, K스페이드
    - [x] 카드를 추가로 뽑은 결과를 출력한다.
    - [x] 받은 카드의 정보를 출력한다.
    - [x] 최종 결과를 출력한다.

  