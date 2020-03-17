# java-blackjack
블랙잭 게임 미션 저장소

## 우아한테크코스 코드리뷰
* [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 기능목록

- 게임에 참여할 사람(플레이어)의 이름을 입력받기
- 플레이어들 각각의 배팅금액 입력받기
- 카드 섞기
- 모든 게임 참여자에게 카드 두 장을 나누어주기
- 딜러의 첫 카드 두 장 중 한 장 공개하기
- 모든 유저의 카드 출력하기
- 각각의 플레이어에 대해서 카드 나누어주기를 끝내기
  - 21점 이하인 경우 카드를 더 받을지 입력받기
  - 카드 한 장을 나누어주기
- 딜러의 경우 합이 16 이하이면 카드를 받기
- 딜러와 모든 게임 참여자의 카드와 점수 공개
- 최종 승패 출력

## 카드 계산

- 2 ~ 10 : 카드 숫자와 점수가 같다.
- J,Q,K : 10점
- Ace : 1 혹은 11
   - Ace 를 제외한 카드의 합이 10점 이하이면 11점,  
   - 11점 이상이면 1점

## 승패 기준

- 21 이하이면서 21에 가까운 사람이 승
- 딜러와 플레이어가 동점일 경우 무승부
- 둘 다 21일 초과이면 무승부

## 수익 계산

플레이어의 배팅 금액을 A 라고 하자.

- 딜러가 이긴 경우
   - 플레이어는 배팅한 금액을 그대로 딜러에게 준다.
   - 플레이어 수익 : -A
   - 딜러 수익 : +A
- 무승부인 경우
   - 플레이어는 배팅한 금액을 그대로 돌려받는다.
   - 플레이어 수익 : 0
   - 딜러 수익 : 0
- 플레이어가 블랙잭으로 이긴 경우
   - 플레이어는 배팅 금액을 돌려받고, 배팅 금액의 1.5 배를 더 받는다.
   - 플레이어 수익 : +(1.5 * A)
   - 딜러 수익 : -(1.5 * A)
- 그 외 플레이어가 이긴 경우
   - 플레이어는 배팅한 금액의 두배를 돌려받는다.
   - 플레이어 수익 : +A
   - 딜러 수익 : -A