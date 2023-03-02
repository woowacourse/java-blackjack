# java-blackjack

블랙잭 미션 저장소


## 기능 구현 목록

### 카드
- [x] king, queen, jack 은 10으로 계산한다.(이동예정)

### 덱

- [x] 카드는 덱에서 랜덤으로 뽑는다
  - [ ] 뽑은 카드를 제외하면서 하나씩 전달 -> 성능
  - [x] 52개의 카드를 만들고, 그 중 하나씩 전달 -> 현실과 비슷함
  - [x] 카드가 모두 소진되면 IllegalStateException을 던진다. 


[//]: # (플레이어 - 딜러 공통점 묶자. 상속?조합?)
### 플레이어
- [x] 플레이어는 카드를 받을 수 있다.
- [x] 점수를 계산한다
  - [x] A는 무조건 플레이어에게 유리하게 계산한다
- [x] 점수가 21을 초과하는지 확인한다

### 딜러
- [x] 점수가 16 초과하는지 알 수 있다.
- [x] 딜러는 16 이하이면 카드를 추가로 뽑는다.
  - 딜러가 카드를 16이하여도 1번까지만 뽑는다.
  - 16 초과가 될떄까지 뽑는다.
  
### 기타
- [ ] 카드를 분배한다.
- [ ] 승패를 정한다.
  - [x] king, queen, jack 은 10으로 계산한다.
  - [ ] ace 는 1이나 11로 계산한다.
  - [ ] 21 이상이면 죽는다.
  - [ ] 점수가 21에 가까운 사람이 이긴다.
  - [ ] 점수가 동일하면 무승부로 한다.

## 입력
- [ ] 게임에 참여할 사람의 이름을 입력 받는다.
- [ ] 플레이어에게 카드를 한 장 더 받을 지 물어본다.

## 출력
- [ ] 카드를 몇장씩 나눴는지 출력한다.
- [ ] 어떤 카드를 받았는지 출력한다.
- [ ] 딜러가 추가로 받은 카드를 출력한다.
- [ ] 딜러의 점수가 17 이상일 때 안내문을 출력한다.
- [ ] 전체 참여자의 카드와 점수를 출력한다.
- [ ] 전체 참여자의 승패를 출력한다.

 
## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)
