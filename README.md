## STEP2 추가 기능 요구 사항
- [ ] 각 플레이어 배팅 금액 입력 받기
  - [ ] 배팅 금액 숫자 검증
  - [ ] 배팅 금액 음수 예외
- [x] 플레이어 배팅 결과 계산
- [ ] 플레이어 수익 계산하기
  - [ ] 처음 2장이 블랙잭이면 1.5배 수익
- [ ] 딜러 수익 계산하기
- [ ] 수익 출력하기

## STEP1 기능 요구 사항
- [x] 참여자 이름을 입력받는다.
  - [x] 구분자로 끝나지 않는지 검증
  - [x] 이름이 중복이지 않는지 검증
  - [x] 공백이 아닌지 검증
- [x] 덱을 생성한다.
  - [x] 중복되지 않는 4개모양, 14개의 숫자의 카드들
- [x] 덱에서 카드를 뽑는다.
- [x] 딜러의 공개된 카드를 출력한다.
- [x] 참여자의 모든 카드를 출력한다.
- [x] 카드의 합을 계산한다.
- [x] 카드가 에이스인지 확인한다.
- [x] 참여자의 상태를 확인한다.
  - [x] 만약 블랙잭이 있으면 바로 결과 출력으로 간다.
  - [x] 만약 21 초과이면 바로 패배한다.
- [x] 딜러의 상태를 확인한다.
  - [x] 만약 블랙잭이면 바로 결과 출력으로 간다. 
- [x] 각 참여자에게 카드를 더 받을지 물어본다.
  - [x] 카드 더 받는지 여부 입력 검증
- [x] 참여자의 카드를 추가한다.
- [x] 딜러가 카드를 더 받아야 하는 상황인지 확인한다.
- [x] 딜러와 참여자들의 카드 현황과 숫자 합을 출력한다.
- [x] 참여자의 승패를 계산한다.
- [x] 딜러의 승패를 통계낸다.
- [x] 최종 승패를 출력한다.
