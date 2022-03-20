# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
- 게임을 완료한 후 각 플레이어별로 승패를 출력한다.

## 기능 요구 사항

블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다. 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 단,
카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다. 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다. 딜러와 플레이어가 모두 동시에 블랙잭인 경우
플레이어는 베팅한 금액을 돌려받는다. 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다. 딜러가 21을 초과하면 그 시점까지 남아 있던
플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.

## UI

- [ ] 참여자 이름을 입력 받는다.
    - [ ] 중복된 이름을 입력 받으면 다시 입력 받는다.
    - [ ] 딜러라는 이름을 입력 받으면 다시 입력 받는다.

- [ ] 배팅 금액을 입력 받는다.
    - [ ] 배팅 금액이 1000 단위가 아니라면 다시 입력 받는다.

- [ ] 카드를 두 장씩 받는다.
    - [ ] 보유 카드를 출력한다.
        - [ ] 딜러는 한 장만 오픈한다.

- [ ] player 들의 hit 여부를 입력 받는다.
    - [ ] Y: 카드를 한 장 더 받는다.
    - [ ] N: 카드를 받지 않는다.
    - [ ] 보유 카드를 출력한다.

- [ ] dealer 가 hit 한다.

- [ ] 모든 참가자들의 카드와 총점을 출력한다.

- [ ] 최종 수익을 출력한다.

## 객체

- [ ] BlackjackApplication 은 Participant dealer 와 List<Participant> players 를 가지고 있다.

- [ ] Participant 는 State 를 가지고 있다.
    - [ ] abstract hit (State) void
    - [ ] stay (State) void
    - [ ] getCards (State) return List<Card>
    - [ ] initCards 카드를 두 장 받는다. (State) void
    - [ ] getTotalScore (State) return int
    - [ ] getProfit (Participant) return int
    - [ ] getName (State) return String

- [ ] State 는 privateArea, Chip 을 가지고 있다.
    - [ ] abstract draw(Card) return State
    - [ ] abstract compare(State) return State
    - [ ] abstract stay() return State
    - [ ] getCards() return List<Card>
    - [ ] getName() return String
    - [ ] getTotalScore() return int

- [ ] privateArea 는 List<Card> 와 이름(String)을 가지고 있다.
    - [ ] addCard return void
    - [ ] getTotalScore return int
    - [ ] getCards() return List<Card>
    - [ ] getName() return String
    - [ ] isBust
    - [ ] isBlackjack
    - [ ] isDealer

- [ ] PlayerName 으로 이름 생성
    - [ ] 이름이 딜러일 경우 예외 발생

- [ ] Chip 로 배팅금액 생성
    - [ ] 배팅 금액이 1000 단위가 아니라면 예외 발생

- [ ] Player PlayerName 과 Chip 으로 생성된다.

- [ ] Dealer 는 생성 시 파라미터가 필요없다.
    - [ ] hit(overriding, 카드 합이 17이상이면 State.stay() 하고 throw Exception)

- [ ] Player 는 PlayerName, Chip 으로 생성 된다.
    - [ ] hit(overriding, State.draw())

State state = new Init();

    state.compare(other);

    State compare(State other) {
        if (cards.isDealer) {
            return compareWithPlayer(other.cards, other.chip);
        }
        return compareWithDealer(other.cards);
    }

    private State compareWithDealer(Cards dealerCards) {
        if (dealerCards.sum() == cards.sum()) {
            return new draw();
        }
        if (dealerCards.sum() > cards.sum()) {
            return new Lose(chip);
        }
        return new Win(chip);
    }

    private State compareWithPlayer(Cards playerCards, int playerChip) {
        if (playerCards.sum() == cards.sum()) {
            return new draw();
        }
        if (playerCards.sum() > cards.sum()) {
            return new Lose(playerChip);
        }
        return new Win(playerChip);
    }