# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)


블랙잭 게임을 변형한 프로그램을 구현한다. 
블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

# 블랙잭(Blackjack)
- [x] 딜러, 참가자, 덱을 소유한다. 
- [x] 게임을 세팅한다. 
  - 참가자에게 카드를 2장씩 지급한다.
- [x] 게임을 진행한다.
  - 참가자 별로 턴을 진행한다.
- [x] 게임을 종료하고 결과를 계산한다.

# 블랙잭 규칙(BlackjackRule)
- [x] 딜러, 참가자의 카드를 바탕으로 게임 결과를 생성한다.
- [x] 블랙잭(21)인 플레이어는 승리한다. 단, 딜러와 참가자 모두 블랙잭이면 딜러가 승리한다.
- [x] 21이 넘은 플레이어는 패배한다. 단, 딜러와 참가자 모두 버스트가 되면 딜러가 승리한다. 
- [x] 둘 모두 블랙잭,버스트가 아닐 경우 지닌 카드의 숫자를 계산하여 승패를 결정한다.

# 블랙잭 게임 결과(BlackjackResult)
- [x] 모든 플레이어의 결과를 소유한다.

# 카드(Card)
- [x] 끗수와 문양을 소유한다.

# 끗수(Denomination)
- [x] Ace의 경우, 더했을 때 21 미만이면 수면 11로, 아니면 1로 계산한다.
- [x] 카드의 숫자 계산은 카드 숫자를 기본으로 하며 King, Queen, Jack은 각각 10으로 계산한다.

# 문양(Symbol)
- [x] 문양은 하트,스페이드,클로버,다이아몬드로 4종류이다.

# 딜러(Dealer)
- [x] 딜러는 플레이어이다.
- [x] 딜러는 카드를 소유한다. 
- [x] 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받는다. 단, 17점 이상이면 추가로 받을 수 없다.

# 덱(Deck)
- [x] 랜덤한 카드를 뽑는다.
- [x] 카드를 모두 소진할 경우 뽑을 수 없다.

# 플레이어(Player)
- [x] 플레이어는 카드를 소유한다.
- [x] 자신이 갖는 카드의 합계를 계산한다.
- [x] 플레이어는 항상 유리한 선택을 하기 위해 Ace의 경우, 더했을 때 21 미만이면 수면 11로, 아니면 1로 계산한다.
- [x] 플레이어는는 21이 넘으면 버스트 상태가 된다.

# 참가자(Participant)
- [x] 참가자는 플레이어이다.

# 입력
- [x] 이름을 입력받는다.
- [x] 카드를 더 입력 받을지 입력받는다.

# 출력
- [x] 나눈 카드를 출력한다.
- [x] 나누어진 카드와 게임 결과를 출력한다.
- [x] 게임의 승패를 출력한다.



TODO:

카드 캐싱 적용하기
게임 계산 결과 로직 개선
덱 중복 로직 개선

사용자 이름은 5글자 이하여야한다



The Pack
The standard 52-card pack is used, but in most casinos several decks of cards are shuffled together. 
The six-cards game (312 cards) is the most popular. 
In addition, the dealer uses a blank plastic card, which is never dealt, 
but is placed toward the bottom of the pack to indicate when it will be time for the cards to be reshuffled. When four or more decks are used, they are dealt from a shoe (a box that allows the dealer to remove cards one at a time, face down, without actually holding one or more packs).

