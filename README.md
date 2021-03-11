# 연료 주입

## 기능 요구사항
1. 사전 세팅
    - 소나타(10KM/L) 2대, 아반떼(15KM/L) 1대, K5(13KM/L) 2대
    - 연료는 초기에 0L 이다.(주입되어 있는 연료량 없음)
2. 이동 거리를 입력 받는다
3. 각 차량별로 필요한 연료량을 나타낸다

## Car가 공통적으로 가져야 하는 기능
- 연비 (각 차량이 생성자에서 셋팅)
- 이동할 거리 (생성자의 인자로 받음)
- 필요한 연료를 계산하는 메소드 (추상메소드는 아님)

## 프로그래밍 요구사항
- 상속과 추상 메소드 활용
- if/else 사용하지 않음

# 블랙잭

## 사전 세팅
- [x] 카드는 총 52 = (A, 2~10, K, Q, J) x 카드종류(스페이드, 하트, 클로버, 다이아몬드)
- [x] 카드 뭉치를 무작위로 셔플
- [x] 카드는 중복되지 않으며 한번씩만 지급된다.

## 기능 요구사항
- [x] 게임 시작시 모든 플레이어와 딜러에게 카드를 2장씩 지급한다.
- [x] 게임 규칙
    - 각 참가자의 카드 합계를 계산한다.
        - [x] Ace는 1 또는 11로 계산할 수 있다.
        - [x] King, Queen, Jack은 각각 10으로 계산한다.
    - 각 플레이어는 딜러와 승부한다.
        - 이기는 경우
            - [x] 카드 합계가 21을 넘지 않으면서 상대보다 합계가 높은 경우 승리한다.
        - 지는 경우
            - [x] 카드 합계가 21을 초과하는 경우
        - 무승부인 경우
            - [x] 카드 합계가 21을 넘지 않으면서 같은 경우
            - [x] 카드 합계가 동시에 21을 초과하는 경우
    - 베팅 수익
        - [x] 플레이어가 이긴 경우 딜러는 베팅금액의 2배를 지급해야 한다.
        - [x] 딜러가 이긴 경우 플레이어의 베팅금액을 딜러가 가진다.
        - [x] 무승부인 경우 플레이어는 베팅금액을 돌려 받는다.
- [x] 딜러
    - [x] 딜러는 처음에 지급 받은 카드 2장의 합이 16이하면 추가로 한장의 카드를 받아야한다.
    - [x] 17점 이상이면 카드를 추가로 받을 수 없다.
- [x] 플레이어
    - [x] 플레이어는 21을 초과하지 않는 경우 카드를 추가로 계속 뽑을 수 있다.
- [x] 결과에 따라 플레이어와 딜러의 지급하거나 지급 받을 금액을 계산한다.     
- [x] 게임 후 금액에서 베팅 금액을 차감하여 수익을 구한다.
- [x] 참가자의 상태
    - [x] bust: 카드 합계가 21 초과인 경우
    - [x] blackjack: 2장의 카트 합계가 21인 경우
    - [x] hit: bust와 blackjack을 제외한 상태
## 입출력
- [x] 쉼표로 구분된 플레이어 이름을 입력 받는다.
  - [x] 예외 상황
    - [x] 이름은 알파벳으로 이루어지지 않은 경우
- [x] 각 플레이어에게 베팅 금액을 묻는다.    
- [x] 각각의 플레이어와 딜러들이 지급 받은 카드를 나열한다.
- [x] 플레이어에게 카드를 추가로 받을지 여부를 물어본다.
- [x] 여부에 따라 카드를 현황을 보여준다.
- [x] 플레이어와 딜러가 각각 지급 받은 카드의 합계를 출력한다.
- [x] 최종 수익 결과를 보여준다.


# To do
- Participant의 카드 합산 로직 Hand로 이동
