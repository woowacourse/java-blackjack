

#### domain

- [x] 게임을 위한 카드 덱 생성
- [x] 카드 덱 셔플
- [x] 카드 덱에서 카드 한장 뽑기
- [x] 카드마다 정해진 문양과, 숫자를 가진다
  - [x] Ace는 1또는 11로 가진다
  - [x] King, Queen, Jack은 10을 숫자로 가진다
  - [x] 같은 종류의 카드는 중복일 수 없다
- [x] 플레이어 등록
  - [x] validation: 공백 이름이 입력되었는가
- [x] 게임을 시작하면 플레이와 딜러에게 2장씩의 카드를 지급한다
- [x] 플레이어와 딜러가 가진 패의 숫자합을 계산한다
- [x] 플레이어 게임 진행
  - [x] 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다
- [x] 딜러 게임 진행
  - [x] 딜러의 숫자 합이 16이하이면 반드시 1장의 카드를 추가로 받고, 17이상이면 추가로 받지 않는다
- [x] 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다.


#### view

- [x] 플레이어 이름 입력 안내 메시지 출력
- [x] 플레이어 이름 입력
  - [x] delimiter: `,`
- [x] 카드 분배 안내메시지 출력
- [x] 진행 카드의 목록을 출력한다
- [x] 카드를 더 받을지 안내 메시지 출력
- [x] 카드를 더 받을지 말지 입력 (y, n)
- [x] 딜러 카드 추가 안내 메시지 출력
- [x] 게임 결과 출력
- [x] 최종 승패 출력
