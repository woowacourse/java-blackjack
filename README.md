# 기능 요구 사항
- [x] 참여자 이름을 입력받는다.
  - [x] 구분자로 끝나지 않는지 검증
  - [x] 이름이 중복이지 않는지 검증
  - [x] 공백이 아닌지 검증
- [x] 플레이어에게 게임을 시작할 때 배팅 금액을 입력받는다.
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
  - [x] 만약 21 초과이면 배팅 금액을 모두 잃게 된다.
- [x] 딜러의 상태를 확인한다.
  - [x] 만약 블랙잭이면 바로 결과 출력으로 간다. 
- [x] 각 참여자에게 카드를 더 받을지 물어본다.
  - [x] 카드 더 받는지 여부 입력 검증
- [x] 참여자의 카드를 추가한다.
- [x] 딜러가 카드를 더 받아야 하는 상황인지 확인한다.
- [x] 딜러와 참여자들의 카드 현황과 숫자 합을 출력한다.
- [x] 참여자의 승패를 계산한다.
- [x] 딜러의 승패를 통계낸다.
- [x] 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 받는다.
  - [x] 딜러에게 받는다.
- [x] 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.
- [x] 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.
- [x] 최종 승패를 출력한다.

# 리팩토링 목록
- [x] 딜러, 참여자 중복 로직 제거
- [x] 클래스 - 메서드 사이 공백 고민, 통일
- [x] 딜러, 참여자, 플레이어, 게임, dto 컨벤션 체크
- [x] 테스트 코드 컨벤션 체크
- [x] entry -> 로 한 거 그냥 key, value로 바로 부르게 스트림 부분들 수정
- [x] 카드 formatter도 도메인에 의존하게 변경
- [ ] status 여러개의 객체 역할 정리하기, 통합, 분리
- [ ] status 메서드 10행 맞추기
- [ ] Betting민est 리팩토링
- [ ] 컨벤션 체크, 공백 체크
- [ ] shuffle 안으로 넣었는데 기존 테스트 통과하는지 확인
- [ ] 수익 계산 방식, 책임 고민