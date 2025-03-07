# java-blackjack
# 블랙잭 미션

- [x] 카드덱 역할
  - [x] 카드 52장을 컬렉션으로 포함하고 있음
  - [x] 카드를 뽑는 기능이 있음
  - [x] 카드를 넣는 기능이 있음
  - [x] 카드덱을 처음 생성할 때 52장을 생성한다.
  - [x] 카드덱을 초기화 한다. -> 블랙잭 게임에 참가한 참가자들의 개인 카드덱
  - [x] 카드덱을 섞는다.

- [x] 카드 역할
  - [x] 필드에 카드의 숫자를 가지고 있다.
  - [x] 필드에 카드의 심볼을 가지고 있다.
  - [x] 카드가 에이스인지 확인한다.

- [x] 카드 심볼
  - [x] 클로버, 다이아몬드, 하트, 스페이드

- [x] 카드 숫자
  - [x] ACE = 1, 2 = 2 . . . (J, Q, K = 10)

- [x] 참가자 역할
  - [x] participant 인터페이스 추상화
  - [x] DEALER, PLAYER 구현체 생성
  - [x] 카드 뽑기 가능한지 확인
  - [x] 닉네임 반환
  - [x] 딜러인지 확인
  - [x] 전적 확인

- [x] 게임판 역할
  - [x] 필드로 게임카드(52장) 정보
  - [x] 필드로 플레이어가 가지고 있는 카드덱 생성
  - [x] 승,패 유무 판단
  - [x] 점수 계산 판단
  - [x] 카드 두 장 뽑기
  - [x] 카드 한 장 뽑기
