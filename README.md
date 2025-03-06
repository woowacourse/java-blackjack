# 블랙잭

## 게임에 참여할 사람 이름을 입력받는다.

- [ ] 공백이 입력되었을 경우, 예외를 발생시킨다.
- [x] 쉼표 기준으로 분리 후 참여자를 생성한다.
- [ ] 참여자의 최대 인원수는 9명으로 제한한다.
    - [ ] 만약 9명을 초과할 경우, 예외를 발생시킨다.

## 덱 생성

- [x] 카드 뭉치로부터 덱을 생성한다
    - [x] 덱이 생성될 때 카드들이 섞여야 한다
- [x] 덱에 들어가는 카드는 하트, 스페이스, 다이아, 클로버다.
- [x] 각 카드 별 A, 1, 2, 3, ... 10, Q, K, J 을 한 장씩 가지고 있어야 한다.

## 카드 분배

- [x] 참여자 모두에게 2장의 카드를 나눠준다
    - [x] 딜러에게 2장의 카드를 나눠준다
    - [x] 참여자 모두에게 2장의 카드를 나눠준다
- [x] 나눠준 카드들을 덱에서 제거한다.

## 추가 카드 요청

- [x] 모든 참여자에게 카드를 받을지 선택 여부를 입력받는다.
    - [x] 참가자가 가진 카드의 합을 계산하는 기능
        - [x] A의 값은 11로 계산하되 21을 넘는 경우 1로 계산된다
    - [x] 가진 카드의 합이 특정 값을 초과하는지 판별할 수 있어야 한다.
    - [x] 참가자의 현재 카드의 합이 21을 초과할 경우, 입력받지 않는다.

## 딜러 카드 뽑기

- [x] 딜러가 카드를 뽑을 수 있는지 판별한다
- [x] 딜러의 현재 카드 상태가 16 이하라면, 카드를 한 장 더 뽑는다.
- [x] 딜러의 현재 카드 상태가 17 이상이라면, 추가 카드를 뽑지 않는다.

## 블랙잭 결과 정산

- [x] 참여자의 최종 카드 정보를 출력한다.
- [x] 최종 승패를 출력한다.
- [x] 딜러와 플레이어간의 승패를 계산하는 기능

최종 확인 사항

- [x] 21이 넘지 않는다면, 계속해서 카드를 뽑아야 한다.