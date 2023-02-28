# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 미션 명세

### 도메인 구성 요소
- 카드 덱
  - 카드
    - 심볼
    - 숫자 (2~10, Ace, King, Queen, Jack)
- 사람
  - 딜러
  - 참여자
    - 이름
    - 가지고 있는 카드
- 최종 승패

### 기능 목록
#### 도메인 기능
- [x] 카드를 생성한다.
- [x] 카드는 심볼(스페이드, 하트, 클로버, 다이아몬드)을 가진다.
- [x] 카드는 숫자(2~10, Ace, King, Queen, Jack)을 가진다.
  - [x] 각 숫자는 값을 가진다. 

- [x] 카드 덱은 52장의 카드를 가진다.
- [x] 카드 덱은 중복을 허용하지 않는다.
- [x] 카드 덱에서 주어진 장수만큼 카드를 뽑는다.
- [ ] 모든 참여자에게 카드를 2장씩 나눠준다.

- [ ] 카드들을 만든다.
- [ ] 카드들을 섞는다.

- [ ] 사람이 가지고 있는 카드의 합을 계산한다.
  - 카드의 숫자가 A이면 1 또는 11 중 계산한다.
  - King, Queen, Jack은 10으로 계산한다.
- [ ] 사람은 카드를 한장 더 받을 수 있다.

- [ ] 딜러의 카드 합이 16 이하인지 판단한다.

- [ ] 합을 비교해 최종 승패를 결정한다.
  - 딜러가 특정 참여자의 승패를 알려준다.
  - 딜러가 자신의 승패 결과를 합산한다.

#### UI 기능
- [ ] 참여자의 이름을 입력받는다.
- [ ] 이름을 쉼표 기준으로 분리한다.
- [ ] 카드를 더 받을지 여부를 정해진 키워드로 입력받는다.
- [ ] 카드를 나눠준 결과를 출력한다.
- [ ] 참여자가 가진 카드들을 출력한다.
- [ ] 딜러가 카드를 받은 여부를 출력한다.
- [ ] 최종 카드 합산 결과를 출력한다.
- [ ] 최종 승패를 출력한다.
