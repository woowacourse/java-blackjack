# java-blackjack

블랙잭 미션 저장소

## 리팩터링 목록
- [x] Game::getResult 메서드 너무 복잡하고 길어
  - [x] index -> name 이용하도록 변경 (isWon, dealAnotherCard)

- [x] Application 메서드 분리
- [x] Game도 인스턴스 변수 개수 3개다
  - dealer를 users 안에 넣자.

- [x] Player::calculateScore 메서드 분리
- [x] Game::dealCards indent가 2 임.
- [x] Player 인스턴스 변수 개수 3개다.
- [x] 카드 글자 리팩터링 Deck::buildCards()
- [x] Players 일급 컬렉션 사용

- [x] 매직 넘버 상수화
- [x] Hand(Cards) 구현
  - [X] Hand Test
- [x] 제약조건
  - [x] 인원수 제한 (1이상 10 이하의 인원)
  - [x] 입력값 검증(빈 문자열, 공백)
- [ ] Status를 이용한다. 
- [x] Score 원시 값 포장
  - [x] Score Test
- [x] denomination 가 점수를 가지게 한다.
- [x] dealer와 player의 중복 기능은 추상 클래스로 분리하고, 상속하게 한다.
- [ ] 메서드 위치 정렬


## 기능 구현 목록

### 카드

### 덱
- [x] 카드는 덱에서 랜덤으로 뽑는다
  - [x] 52개의 카드를 만들고, 그 중 하나씩 전달 -> 현실과 비슷함
  - [x] 카드가 모두 소진되면 IllegalStateException을 던진다. 

### 플레이어
- [x] 이름을 가진다
- [x] 플레이어는 카드를 받을 수 있다.
- [x] hit이 가능한지 확인할 수 있다.
- [x] 점수를 계산한다
  - [x] king, queen, jack 은 10으로 계산한다.
  - [x] ace 는 1이나 11로 계산한다.
  - [x] A는 무조건 플레이어에게 유리하게 계산한다
- [x] 점수가 21을 초과하는지 확인한다.

### 딜러
- 딜러는 플레이어의 역할도 가진다.
- [x] 점수가 16 초과하는지 알 수 있다.
- [x] 딜러는 16 이하이면 카드를 추가로 뽑는다.
  - 딜러가 카드를 16이하여도 1번까지만 뽑는다.
  - 16 초과가 될떄까지 뽑는다.
  
### 게임
- [x] 플레이어가 21 미만이라면, 카드를 한 장 준다.
  - [x] 21 이상인 플레이어는 카드를 주지 않는다.
- [x] 딜러에게도 카드를 한 장 주도록 할 수 있다.
- [x] 카드를 2장씩 분배한다.
- [x] 승패를 정한다.
  - [x] 딜러와 플레이어중에 21에 가까운 사람이 이긴다.
  - [x] 점수가 동일하거나 모두 `BUSTED`면 무승부로 한다.
- [x] 딜러의 모든 승패를 정한다.

## 입력
- [x] 게임에 참여할 사람의 이름을 입력 받는다.
- [x] 플레이어에게 카드를 한 장 더 받을 지 물어본다.

## 출력
- [x] 카드를 몇장씩 나눴는지 출력한다.
- [x] 어떤 카드를 받았는지 출력한다.
- [x] 딜러가 추가로 받은 카드를 출력한다.
- [x] 딜러의 점수가 17 이상일 때 안내문을 출력한다.
- [x] 전체 참여자의 카드와 점수를 출력한다.
- [x] 전체 참여자의 승패를 출력한다.
- [x] 딜러의 모든 승패를 출력한다

 
## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)
