# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)


## 기능 구현 목록

### BlackjackGame
- 블랙잭 게임 규칙에 따라 게임을 진행
- [ ] Deck에서 카드를 받아 User에게 전달한다.
- [ ] 사용자의 의사를 확인하여 카드를 추가로 발급한다.

### AbstractUser
- 게임에 참여하는 유저가 상속받는 클래스
- [ ] 덱의 점수를 계산한다.
- [ ] 카드를 더 받을 수 있는 상황인지 확인한다.
- [ ] CardHand에 Card를 저장한다.
- [ ] CardHand의 점수가 블랙잭인지 확인한다.

### Dealer
- 플레이어와 비교하여 승패를 결정하는 유저
- [ ] CardHand의 점수가 16이하인지 확인한다. (Override)

### Player
- 게임에 참여하는 유저
- [ ] CardHand의 점수가 21이하인지 확인한다. (Override)

### Name
- 유저의 이름 정보를 가지고 있는 객체
- [x] 유효한 이름인지 검증한다.
- [x] 이름 값을 반환한다.

### Deck
- 상징 별, 숫자 별 Card 52장을 생성해서 가지고 있는 객체
- [ ] 상징, 숫자 별 카드를 생성한다.
- [ ] 랜덤한 1개의 카드를 반환한다.
  - [ ] 한 번 반환한 카드는 다시 반환하지 않는다.

### Card
- 카드 한장
- [x] 숫자를 반환한다.
- [x] 카드 정보에 대한 텍스트를 반환한다.

### Symbol (enum)
- Card가 가지고 있는 상징
- [x] 카드 상징에 대한 정보를 갖고 있는 상수 집합을 구현한다.
- [x] 상징의 이름을 반환한다.

### CardNumber (enum)
- Card가 가지고 있는 숫자
- [x] 카드가 가질 수 있는 숫자 정보를 갖고 있는 상수 집합을 구현한다.
  - [x] Ace는 1의 숫자를 가지고 있는다.
  - [x] Jack, Queen, King 10으로 계산한다.

### CardHand
- 유저가 가지고 있는 카드 뭉치
- [ ] 점수를 반환한다.
  - [ ] Ace는 점수의 유불리에 따라 1 또는 11로 계산한다.

### Result (enum)
- 게임 결과 정보
- [ ] 게임 결과에 대한 정보를 갖고 있는 상수 집합을 구현한다.

### ResultRepository
- Player의 게임 결과(Result)를 매칭한 정보를 가지고 있는 객체.
- [ ] Player의 게임 결과를 저장한다.
- [ ] Player의 게임 결과를 반환한다.
