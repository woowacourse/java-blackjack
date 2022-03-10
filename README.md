# java-blackjack

### 요구 기능 정리

- [x] 플레이어 이름 입력 받기
    - [x] 쉼표로 분리
- [ ] 딜러 및 플레이어 생성하기
    - [x] 딜러 및 플레이어들에게 카드를 각각 두 장씩 주기
    - [ ] 딜러의 카드는 첫 장만 공개
    - [ ] 플레이어의 카드는 2장 모두 공개
- [ ] 플레이어 별로 게임 진행
    - [ ] 카드를 더 받을지 선택
        - [ ] 플레이어의 카드 숫자 합이 21을 넘으면 선택할 수 없음
        - [ ] y / n 으로 입력받아서 결정
    - [ ] 플레이어가 가진 카드를 출력
- [x] 딜러의 게임 진행
    - [x] 숫자의 합이 16 이하인 경우 카드를 한 장 더 받기
    - [x] 17 이상이면 멈추기
- [ ] 승패 계산
    - [x] A는 1 또는 11이 될 수 있다.
    - [x] K, Q, J는 10으로 계산한다.
    - [ ] 딜러와 플레이어가 가진 카드와 합계를 출력
    - [x] 플레이어의 숫자가 21을 넘기면 무조건 플레이어의 패배
    - [x] 플레이어의 숫자가 딜러보다 21에 가까우면 플레이어의 승리
    - [x] 플레이어의 숫자가 21을 넘기지 않고 딜러의 숫자가 21을 넘기면 플레이어의 승리

---

### 객체 별 역할 정리

- Dealer
    - 게임의 딜러
    - 카드를 소유하고 있다.
    - 카드 덱을 받아서 보유 카드 숫자가 17이 넘을 때 까지 카드를 뽑는다.
- Player
    - 게임의 플레이어
    - 카드를 소유하고 있다.
    - 카드 덱을 받아서 카드를 뽑는다.
- Name
    - 딜러 또는 플레이어의 이름
    - null이거나 빈 문자열일 수 없다.
- Card
    - Pattern
        - 카드의 문양이 저장된 enum
    - Denomination
        - 카드의 끗수가 저장된 enum
    - 카드의 문양과 끗수를 저장하고 있다.
- CardDeck
    - 게임에서 사용하는 카드들을 관리하고 있다.
    - CardsGenerator
        - 카드를 생성하는 전략을 제공하는 인터페이스
        - BlackJackCardsGenerator
            - CardsGenerator의 구현체로 서로 다른 52장의 카드를 가진 덱을 생성한다.
- Rule
    - 게임의 규칙을 담고 있다.
    - 카드의 총합을 계산한다.
    - 버스트(총합이 21을 넘는 경우) 여부를 판단한다.
- Result
    - 플레이어와 딜러의 카드를 받는다.
    - 승패를 결정한다.
    - Judgement
        - 승, 무, 패 값을 가진 enum