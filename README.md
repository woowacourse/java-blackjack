# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)


## 기능 구현 목록

### InputView
- [x] 플레이어 이름과 베팅금액을 입력한다.
- [x] 카드 재발급 여부를 입력받는다.

### OutputView
- [x] 딜러와 플레이어 초기 카드 발급 결과를 출력한다.

### CardPrintMapper
- [x] Card에게 정보를 받아 CardNumber와 Symbol에 대한 정보를 출력용으로 변환한다.

### MatchResultMapper
- [x] Result를 출력용으로 변환한다.

### InputPlayerDTO
- [x] 플레이어의 이름과 베팅 금액을 입력받아 dto형태로 컨트롤러에게 전달한다.

### Participants
- 딜러와 플레이어간의 상호작용을 관리
- [x] 모든 Plyaer와 Dealer에게 카드를 2장씩 전달한다.
- [x] 사용자의 의사를 확인하여 카드를 추가로 발급한다.
- [x] 딜러가 카드를 더 받을 수 있으면 추가로 발급한다.
- [x] 모든 유저의 카드 현황을 반환한다.
- [x] 딜러와 각 플레이어의 게임 결과를 계산한다.
- [x] 딜러와 각 플레이어의 게임 결과를 반환한다.

### AbstractUser
- 게임에 참여하는 유저가 상속받는 클래스
- [x] CardHand의 점수를 계산한다.
- [x] 카드를 더 받을 수 있는 상황인지 확인한다. (Abstract)
- [x] CardHand에 Card를 저장한다.
- [x] CardHand의 점수가 블랙잭인지 확인한다.
- [x] bust인지 확인한다.
- [x] blackjack인지 확인한다.

### Dealer extends AbstractUser
- 플레이어와 비교하여 승패를 결정하는 유저
- [x] 카드를 더 받을 수 있는 상태인지 확인한다. (Override)
  - [x] 점수가 16점 이상이면 카드를 더 받을 수 없다.
- [x] Deck을 소유해 카드 발급 관련 기능을 수행한다.
- [x] 전체 베팅결과를 한번에 계산한다.

### Player extends AbstractUser
- 게임에 참여하는 유저
- [x] 카드를 더 받을 수 있는 상태인지 확인한다. (Override)
  - [x] 점수가 21점 이상이면 카드를 더 받을 수 없다.
- [x] 이름을 반환한다.

### Players
- Player를 감싸는 일급 컬렉션
- [x] Player들을 반환한다.

### Name
- 유저의 이름 정보를 가지고 있는 객체
- [x] 유효한 이름인지 검증한다.
- [x] 이름 값을 반환한다.

### Deck
- 상징 별, 숫자 별 domain.Card 52장을 생성해서 가지고 있는 객체
- [x] 상징, 숫자 별 카드를 생성한다.
  - [x] 순서를 랜덤하게 섞는다.
- [x] 1개의 카드를 반환한다.
  - [x] 한 번 반환한 카드는 다시 반환하지 않는다.
  - [x] 단, 카드를 52장 전부 사용한 경우 재발급해서 반환한다.

### Card
- 카드 한장
- [x] 숫자를 반환한다.
- [x] Symbol과 CardNumber를 각각 반환한다.

### Symbol (enum)
- Card가 가지고 있는 상징
- [x] 카드 상징에 대한 정보를 갖고 있는 상수 집합을 구현한다.

### CardNumber (enum)
- Card가 가지고 있는 숫자
- [x] 카드가 가질 수 있는 숫자 정보를 갖고 있는 상수 집합을 구현한다.
  - [x] Ace는 우선 1의 숫자를 가지고 있는다.
  - [x] Jack, Queen, King은 10으로 계산한다.

### CardHand
- 유저가 가지고 있는 카드 뭉치
- [x] 카드 뭉치에 카드를 하나씩 추가한다.
- [x] 점수를 반환한다.
  - [x] Ace는 점수의 유불리에 따라 1 또는 11로 계산한다.

### Result (enum)
- 게임 결과 정보
- [x] 게임 결과에 대한 정보를 갖고 있는 상수 집합을 구현한다.

### PlayerRevenues
- Player의 게임 결과를 매칭한 정보를 가지고 있는 객체
- [x] Player의 게임 결과를 저장한다.
- [x] Player의 게임 결과를 Map형태로 반환한다.

### BettingAmount
- 베팅 금액을 갖는 객체
- [x] 음수인 경우 예외를 던진다.
