## 블랙잭 요구사항
- 유저
  - [ ] 유저는 이름을 가질 수 있다.
  - [ ] [ERROR] 이름이 null이 들어오는 경우 예외가 발생한다.
  - [ ] [ERROR] 이름이 공백이 들어오는 경우 예외가 발생한다.
  - [ ] 유저는 배팅금액을 가질 수 있다.
  - [ ] [ERROR] 배팅금액이 0이하가 들어오는 경우 예외가 발생한다.

- 카드
  - 카드 패턴
    - [ ] 카드 패턴은 총 4개이다. (스페이드, 클로버, 다이아, 하트)
  - 카드 숫자
    - [ ] 카드 숫자는 총 13개이다.
    - [ ] A는 상황에 따라 11로 계산될 수 있다.

- 카드 덱
  - [ ] 카드 덱은 총 52개로 구성되어있다.
  - [ ] [ERROR] 카드 덱이 52개가 들어오지 않는 경우 예외가 발생한다.
  - [ ] 카드를 한장 반환할 수 있다.
  - [ ] [ERROR] 카드가 없는데 카드를 반환하려고하는 경우 예외가 발생한다.

- 게임
  - [ ] 유저는 처음 2장의 카드를 나눠받는다
  - [ ] 유저는 hit으로 카드를 받을 수 있다.
  - [ ] 유저는 카드 받기를 중단할 수 있다.
  - [ ] [ERROR] 유저가 이미 종료된 상태인데 hit을 하려는 경우 예외가 발생한다.
  - [ ] 카드를 받았는데 Bust가 된다면 (21초과) 유저의 상태는 Bust로 변경된다.

- 게임 결과
  - 진행 중
    - [ ] hit을 할 수 있다
    - [ ] stay하여 종료할 수 있다.
    - [ ] 종료 여부는 false이다.
  - 완료
    - [ ] [ERROR] hit을 하는 경우 예외가 발생한다
    - [ ] [ERROR] stay를 하는 경우 예외가 발생한다.
    - [ ] 종료 여부는 true이다.
    - 다른 카드의 결과와 확인하여 이익률을 반환할 수 있다.
      - 현재 Blackjack
        - 상대도 Blackjack인 경우 0을 반환한다.
        - 나머지의 경우 1.5를 반환한다.
      - 현재 Bust
        - -1을 반환한다.
      - 현재 Stand
        - 상대가 Blackjack인 경우 -1을 반환한다.
        - 상대보다 21에 가까울 경우 1을 반환한다.
        - 상대와 수가 같은 경우 0을 반환한다.
        - 상대보다 21에 멀 경우 -1을 반환한다.
