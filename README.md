# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## Controller
- [ ] 게임에 참여할 플레이어의 이름을 입력받는다.
- [ ] 딜러와 플레이어들에게 카드를 두 장씩 랜덤하게 나눠준다.
- [ ] 딜러와 플레이어들이 받은 카드를 출력한다.
  - [ ] 딜러는 한장의 카드만 공개한다.
- [ ] 플레이어 별로 카드를 더 받을지 물어본다.
  - [ ] 플레이어는 카드를 한 장 뽑고 가진 카드 현황을 출력한다.
  - [ ] 플레이어가 가진 카드 숫자의 총합이 21을 초과하면 그만 받도록 한다.
  - [ ] 플레이어가 가진 카드 숫자의 총합이 21 이하라면 플레이어의 선택에 따라 카드를 계속 받을 수 있다.
- [ ] 딜러는 자신의 숫자 카드 합이 17 이상이 되도록 카드를 더 뽑는다.
- [ ] 딜러와 플레이어의 카드 합 결과를 계산한다.
- [ ] 딜러와 플레이어 최종 카드 현황과 각 합을 출력한다.
- [ ] 게임결과를 출력하고 게임을 종료한다.
  - [ ] 딜러는 총 몇 승 몇 패를 했는지 출력한다.
  - [ ] 플레이어는 각자의 승패 결과를 출력한다.

## Model
- [ ] 블랙잭 전체 카드 생성
  - [ ] 전체 카드 개수는 52장
  - [ ] 문양은 하트, 다이아, 클로버, 스페이드
  - [ ] 각 문양 별 Ace, 2~10, K,Q,J 로 구성
    - [ ] Ace는 1 또는 11로 계산할 수 있다.
    - [ ] K,Q,J는 각각 10으로 계산한다.
  - [ ] 52장 카드는 중복되지 않는다.
- [ ] 블랙잭 카드 드로우
  - [ ] 모든 카드는 랜덤하게 셔플된다.
  - [ ] 드로우는 셔플된 카드에서 순서대로 가져간다.
- [ ] 블랙잭 카드 현황 저장한다.
  - [ ] 전체 카드에서 남은 카드 현황
    - [ ] 드로우된 카드는 제외한다.
  - [ ] 딜러 카드 현황
  - [ ] 플레이어 카드 현황
- [ ] 블랙잭 게임 진행
  - [ ] 게임의 플레이어 수는 딜러 제외 5명으로 제한한다.
  - [ ] 플레이어와 딜러는 게임을 시작하며 카드를 2장씩 드로우한다.
  - [ ] 딜러는 처음에 뽑은 카드 1장만 공개하고, 남은 카드는 최종 결과 확인 전까지 비공개한다.
  - [ ] 보유한 카드의 숫자 합산
  - [ ] 보유한 카드의 숫자 합산 유효성 확인
    - [ ] 합산 결과가 21을 초과하면 더 이상 카드를 뽑지 않는다.
- [ ] 딜러와 플레이어 간 승패 결정
  - [ ] 딜러와 플레이어의 카드 숫자 합산 비교 
  - [ ] 딜러와 플레이어 둘 다 버스트일 시 딜러 승
  - [ ] 딜러와 플레이어 결과가 무승부일 때 딜러 승

## View
### InputView
- [ ] 플레이어 이름 입력
  - [ ] [예외처리] 쉼표 기준으로 분리하다(pobi,jason)
- [ ] 카드 추가로 받을지 말지 여부 입력
  - [ ] [예외처리] 'y' 또는 'n'이 아닌 입력
### OutputView
- [ ] 초기 게임 세팅
  - [ ] 플레이어 이름 입력 메세지 출력
  - [ ] 딜러와 플레이어 첫 카드 세팅 결과 출력
  - [ ] 딜러와 플레이어 별로 가진 카드를 출력
- [ ] 게임 진행
  - [ ] 플레이어가 카드를 추가로 받을지 여부 메세지 출력
  - [ ] 플레이어가 가진 카드 현황을 출력
  - [ ] 딜러 추가 카드 수령 메세지
- [ ] 게임 결과
  - [ ] 딜러와 플레이어 최종 카드 결과 출력
  - [ ] 딜러와 플레이어 최종 승패 결과 출력

## TODO
