# ♠️ java-blackjack ♠️

- 게임을 완료한 후 각 플레이어별로 승패를 출력한다.

# 🛠️ 기능 구현 목록

- [x] 입력
    - [x] 게임에 참여할 사람의 이름을 입력 받을 수 있다.
    - [x] 한 장 더 받을지 여부를 입력 받을 수 있다.
    - [ ] 플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다.
- [x] 입력 검증
    - [x] 카드 추가 여부를 올바른 형태 (y/n)으로 입력했는지 검증할 수 있다.
- [x] 도메인
    - CardRank
        - [x] 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
          - Ace를 11로 계산해서 점수가 21을 초과하지 않으면 Ace를 11로 계산한다.
    - CardSuit
    - Card
    - Hand
        - [x] 카드를 지급 받는다.
        - [x] 두 장의 카드를 지급 받는다.
        - [x] 지정한 개수만큼 지급받은 카드를 공개한다.
        - [x] 핸드의 합을 계산한다.
            - 핸드에 Ace가 있을 때 점수를 조정한다.
        - [x] 처음 두 장의 카드 합이 21일 경우 블랙잭이다.
    - CardDeck
        - [x] 모든 카드 랭크와 모양의 조합으로 이루어진 카드를 랜덤하게 섞어 카드 덱을 생성한다.
        - [x] 카드 덱에서 카드를 한 장 빼내어 반환한다.
        - [x] 카드 덱이 비었는데 카드를 빼내서 반환하려고 하면 예외가 발생한다.
    - PlayerName
        - [x] 이름은 빈 문자열일 수 없다.
    - Players
        - [x] 이름이 중복되는 플레이어는 존재할 수 없다.
        - [x] 플레이어가 없는 경우는 게임을 시작할 수 없다.
        - [x] 모든 플레이어의 승패를 결정한다.
    - Player
        - [x] 두 장의 지급받은 카드를 공개한다.
        - [x] 플레이어는 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
    - Dealer
        - [x] 한 장의 지급받은 카드를 공개한다.
        - [x] 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
    - DrawDecision
    - Score
        - [x] 점수는 음수일 수 없다.
        - [x] 21이면 블랙잭 점수다.
        - [x] 21을 초과하면 버스트 점수다.
        - [x] 21을 넘지 않을 경우 플레이어 히트 가능 점수다.
        - [x] 16을 넘지 않을 경우 딜러 히트 가능 점수다.
    - BetAmount
      - [x] 10 단위 양의 정수이다. 
    - Profit
      - [ ] 플레이어가 버스트면 플레이어는 배팅 금액을 모두 잃게 된다.
      - [ ] 플레이어가 버스트가 아니고 딜러가 버스트면 플레이어는 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.
      - [ ] 플레이어가 블랙잭이고 딜러는 블랙잭이 아니면 플레이어는 베팅 금액의 1.5배를 딜러에게 받는다.
      - [ ] 플레이어와 딜러가 모두 블랙잭이면 플레이어는 베팅한 금액을 돌려받는다.
      - [ ] 플레이어와 딜러가 모두 버스트가 아니고 플레이어 핸드가 크면 플레이어는 배팅 금액을 얻게 된다.
      - [ ] 플레이어와 딜러가 모두 버스트가 아니고 플레이어 핸드가 작으면 플레이어는 배팅 금액을 잃게 된다.
      - [ ] 플레이어와 딜러가 모두 버스트가 아니고 둘의 핸드가 같으면 플레이어는 배팅 금액을 돌려받게 된다.
    - PlayersProfit
      - [ ] 딜러의 수익은 플레이어들이 잃은 금액에서 얻은 금액을 제한 금액이다.
- [x] 출력
    - [x] 참여자의 deal 이후 핸드를 출력할 수 있다.
    - [x] 참여자의 draw 이후 핸드를 출력할 수 있다.
    - [x] 참여자의 핸드와 점수를 출력할 수 있다.
    - [x] 참여자의 승패 결과를 출력할 수 있다.
