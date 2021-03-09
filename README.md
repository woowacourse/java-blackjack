# java-blackjack
블랙잭 게임 미션 저장소

# 구현 기능 목록 

* 카드를 뽑는다. (덱)
* 카드를 받는다. (참가자)
    * 딜러는 총점이 16이하면 한장을 더 뽑는다.
    * 참가자의 총점이 21을 초과하면 턴이 종료된다. 
* 카드 총점을 합산한다. (Rule)
    * 에이스는 1 or 11 점이다. 
* 카드를 보여준다 (참가자)
    * 딜러는 1장의 카드만 보여준다.
* 승패를 가른다. (딜러)
    * 참가자는 총점이 21을 초과하면 플레이어가 패배한다. 
    * 플레이어가 딜러보다 점수가 낮으면 플레이어가 패배한다. 
    * 둘다 bust인 경우, 딜러가 승리한다. 
    * bust가 아니고, 점수가 같을 경우 무승부다. 
    * 딜러만 bust면 모든 플레이어가 승리한다. 
* 카드덱을 생성한다.
* 게임시작 시, 두 장의 카드를 지급한다. 
* 참가자들을 Participants 일급 컬렉션으로 포장한다.
* 참가자들 중에 딜러와 플레이어를 뽑아낸다.

# 블랙잭 2단계 배팅 구현 목록

* HIT 상태 (처음 2장 카드 상태에서 카드를 더 뽑는 것)
  * 처음으로 받는 카드 2장이 21이면 -> 블랙잭 상태
  * 카드를 뽑을 수 있다.
  * 카드가 21이 넘어가면 -> 버스트 상태
  * 카드 뽑기를 멈출 수 있다 -> 스테이 상태
  
* BLACK JACK 상태 (처음 2장 카드합이 21인 경우)
  * 카드를 더 뽑을 수 없다.
  * 배팅금액의 1.5배
  
* STAY 상태 (카드를 뽑지 않고 차례를 마치는 것)
  * 카드를 더 이상 뽑을 수 없다.
  * 승리 시 배팅 금액 만큼 받음
  
* BUST 상태 (카드 총합이 21이 넘는 경우)
  * 카드를 더 이상 뽑을 수 없다
  * 배팅 금액 만큼 잃음
