# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

---

```mermaid
classDiagram
  
  Dealer
  Player --> Players
  Card --> DrawnCards
  DrawnCards --> Player
  DrawnCards --> Dealer
  Name --> Status
  Status --> Player
  Status --> Dealer
  Account --> Status
  Type --> Card
  Value --> Card
  Card --> CardDeck
  
  CardDeck --> BlackJackController
  Players --> BlackJackController
  Dealer --> BlackJackController
  
```

## 오리 X 제이의 블랙잭 규칙

- 점수가 21점에 가까운 사람이 승리한다.
- 점수가 같으면 딜러가 우승하는 것으로 간주한다.
- 카드 종류는 `스페이드, 다이아몬드, 하트, 클로버` 가 있고, `2~10의 숫자와 J(10), Q(10), K(10), A(1 또는 11)`이다
    - 즉 한 종류의 카드에 `13장`씩 있고, `총 52장`의 카드가 있다.

- 딜러를 포함한 모든 플레이어의 카드는 공개하지만, `딜러의 첫 번째 카드는 비공개`한다.
- 한 명이 카드 뽑기를 마치고나서 다음 플레이어가 카드를 뽑는다.
    - 단, 카드를 뽑을 때 21이 넘어가면 패배 처리가 되고, 다음 플레이어로 넘어간다.
    - 단, 딜러와 플레이어 모두 21이 넘어간다면 `21`에 가장 가까운 수를 뽑은 참가자가 이긴다.
    - 딜러는 카드의 총합이 16이하라면 `반드시` 카드를 한장 더 뽑아야한다.

---

## 요구 사항

- 게임에 참여할 사람의 이름을 입력 받는다.
    - `콤마(,)`를 통해서 이름을 구분한다.

- 딜러와 각 참여자들에게 두 장씩 카드를 분배한다.
    - 딜러와 참여자들이 받은 카드는 공개된다.
    - 딜러의 두 번째 카드는 공개되지 않는다.

- 플레이어의 카드 선택 (loop)
```text
  - 각 플레이어들은 추가적으로 카드를 더 뽑을지 선택한다.
    - 추가로 카드를 뽑지 않으면 다음 플레이어로 넘어간다.
    - 뽑는다면 추가된 카드를 포함하여 공개한다.
    - 총합이 21이 초과하면, 게임 패배 메시지를 출력한다.
```

- 딜러의 카드 선택 (loop)
```text
  - 딜러의 카드 총합이 16을 초과할 때까지, 딜러는 카드 뽑기를 반복한다.
```

- 딜러를 포함한 모든 플레이어들의 카드 목록과 스코어를 출력한다.

- 딜러를 포함한 모든 플레이어들의 스코어와 상금 혹은 잃은 금액을 출력한다.
