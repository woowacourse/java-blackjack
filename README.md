## 기능목록
- [x] 사람 이름 입력받기
  - [x] 쉼표 기준 분류
  - [x] 최소 2명, 최대 8명
  - [x] 공백 제외
  - [x] 중복 제외
- [x] 블랙잭 게임 진행
  - [x] 처음에 플레이어는 2장씩 카드를 지급받는다.
  - [x] 딜러는 2중 1장만 참여자는 2장 전체 공개한다.
  - [x] 카드 1장 드로우
  - [x] 카드 중복 없이
  - [x] 카드 추가 여부 확인
    - [x] 딜러가 16이하일 시 카드 배부
    - [x] 딜러가 17이상이면 종료
  - [x] 21 초과 시 패배
- [x] 카드 점수 계산
  - [x] ace는 1 또는 11 중 하나로 결정
  - [x] 각 사용자 별로 점수를 계산한다.
- [x] 승패 결정
- [x] 버스트면 자동으로 아웃

## 2차 기능 목록
- [ ] 배팅 금액을 입력받는다.
  - [ ] 정수인지 확인
  - [ ] 1이상인지 확인
  - [ ] Integer범위 이내의 숫자인지 확인
- [ ] 결과에 따라 최종 수익을 계산한다.
  - [ ] 승리 했을 시 : 배팅 금액 * 1.0
  - [ ] 패배 했을 시 : 배팅 금액 * -1
  - [ ] 블랙잭 일 시 : 배팅 금액 * 1.5
  - [ ] 딜러의 수익은 참가자들의 총 수익에 -1을 곱한 값 이다.
