# ♠️ java-blackjack ♠️

- 게임을 완료한 후 각 플레이어별로 승패를 출력한다.

# 🛠️ 기능 구현 목록

- [x] 입력
    - [x] 게임에 참여할 사람의 이름을 입력 받을 수 있다.
    - [x] 한 장 더 받을지 여부를 입력 받을 수 있다.
- [x] 입력 검증
    - [x] 카드 추가 여부를 올바른 형태 (y/n)으로 입력했는지 검증할 수 있다.
- [x] 도메인
    - [x] 카드합 비교를 통해서 플레이어의 승패를 결정할 수 있다.
    - [x] 카드합 비교를 통해서 딜러의 승패를 계산할 수 있다.
    - CardSuit
    - CardRank
        - [x] 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
    - Card
    - Hand
        - [x] 핸드에 카드를 추가한다.
        - [x] 핸드의 합을 계산한다.
            - 만약 합이 21을 초과하지 않으면 Ace는 11로 계산한다.
    - CardDeck
        - [x] 모든 카드 랭크와 모양의 조합으로 이루어진 52장의 카드를 랜덤하게 섞어 카드 덱을 생성한다.
        - [x] 카드 덱에서 카드를 한 장 빼내어 반환한다.
        - [x] 카드 덱에서 카드를 지정한 개수만큼 빼내어 반환한다.
        - [x] 카드 덱이 비었는데 카드를 빼내서 반환하려고 하면 예외가 발생한다.
    - Participant
        - [x] 참여자의 히트 조건에 따라 핸드에 카드를 추가한다.
        - [x] 21을 초과하면 버스트다.
    - Player
        - [x] 플레이어는 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
    - Dealer
        - [x] 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
    - Participants
        - [x] 게임을 시작하면 두 장의 카드를 지급 받는다.
    - PlayerName
        - [x] 이름은 빈 문자열일 수 없다.
    - Players
        - [x] 이름이 중복되는 플레이어는 존재할 수 없다.
        - [x] 플레이어가 없는 경우는 게임을 시작할 수 없다.
    - Result
        - [x] 플레이어 결과와 반대로 딜러의 결과를 업데이트한다.
- [x] 출력
    - [x] 각 참여자의 카드 정보를 출력할 수 있다.
    - [x] 각 참여자의 카드 합을 출력할 수 있다.
    - [x] 최종 승패를 출력할 수 있다.
