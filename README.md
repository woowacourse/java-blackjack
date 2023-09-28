# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 구현할 기능 목록
### Base
- [x] 게임을 진행할 때 필요한 기본 규칙을 `BASE.md`에 작성한다.
### View
- [ ] 게임에 참여할 사람들의 이름을 입력받는다.
- [ ] 한 장의 카드를 더 받는지 물어본다.
  - [ ] 입력값이 y, n 이외의 값이라면 다시 물어본다.
  - [ ] 카드 점수 합계가 21을 초과할 경우 물어보지 않는다.
- [ ] 각 플레이어의 이름 및 카드 현황을 출력한다.
  - [ ] 마지막에는 각 플레이어의 이름 및 카드 현황과 더불어 결과 점수도 출력한다.
- [ ] 딜러의 점수 합계가 16점 이하라면 추가 카드 지급문을 출력한다.
  - [ ] 딜러의 점수 합계가 17점 이상이라면 지급하지 않음을 출력한다.
- [ ] 딜러와 플레이어 간 상대적인 승패 결과를 출력한다.
### GameController
- [ ] 컨트롤러 생성 시 `Deck`을 주입받는다.
- [ ] 딜러와 플레이어들에게 처음 카드 두 장을 지급한다.
  - [ ] 지급받은 후 각 지급 현황을 알려준다.
- [ ] 카드를 더 받을 수 있는 플레이어가 없을 때 까지 카드 지급을 실행한다.
- [ ] 최종 지급 현황 및 점수를 알려준다.
- [ ] 최종 승패를 알려준다.
### Name
- [ ] 입력값을 쉼표(,)를 기준으로 분리하여 `Name` 리스트를 만든다.
- [x] 단일 입력값을 토대로 `Name` 객체를 만들 수도 있다.
- [ ] 만약 같은 이름이 있다면 (이미 저장된 이름이 다시 들어온다면) 무시한다.
- [ ] 가지고 있는 실제 값인 `name`을 알려줄 수 있다.
### Deck
- [ ] 게임 시작 전, `BASE.md`에 작성한 규칙을 토대로 기본 카드 덱을 만든다.
- [ ] 카드 덱은 `Cards` 리스트를 가진다.
- [ ] 카드 덱에서 랜덤하게 카드를 한 장 지급한다.
### Card
- [ ] 모든 카드는 타입 (ex: 하트, 스페이드)과 점수를 가진다.
  - [ ] 에이스 (Ace) 카드는 점수를 11로 저장한다.
  - [ ] 에이스 (Ace) 카드는 실제 지급 시 점수가 1로 변경될 수도 있다.
- [ ] 플레이어의 카드 목록은 일급 컬렉션 `Cards`를 활용해 관리한다.
  - [ ] 플레이어들의 카드 점수를 합산한다.
  - [ ] 플레이어들의 각 카드 목록의 이름을 알려줄 수 있다.
### Player
- [ ] 게임 시작 전, 딜러 플레이어를 만들어둔다.
- [ ] 각 플레이어들은 생성 시 빈 `Cards` 리스트를 가진다.
- [ ] 딜러 이외의 플레이어는 직접 입력된 이름을 가진다.
- [ ] 플레이어들은 자신의 이름을 알려줄 수 있다.
- [ ] 플레이어들은 자신의 카드 점수 현황을 알려줄 수 있다.
