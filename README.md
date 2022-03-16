### 각 클래스 역할과 책임

`BlackJackApplication`
- 블랙잭 어플리케이션 전체 흐름을 제어하는 역할 담당

`BlackJackController`
- Main에서 보내는 요구사항을 도메인 비즈니스 로직을 라우팅 하여 원하는 값을 반환하는 역할 담당

`BlackJackReferee`
- 플레이어와 딜러를 입력 받아, 결과를 반환하는 역할 담당

`BlackJackResult`
- 플레이어와 딜러 점수를 입력 받아, 승패 결과를 반환하는 역할 담당

`BlackJackGame`
- BlackJack 게임에서 필요로 하는 요구사항에 대한 응답을 담당(블랙잭 도메인과의 협력)

`Card`
- 블랙잭 카드에 관한 정의를 담당

`CardFactory`
- 블랙잭 카드 뭉치를 관리하는 역할을 담당

`Dealer`
- 블랙잭 딜러의 역할을 담당

`Player`
- 블랙잭 플레이어의 역할을 담당

`Gamer`
- 블랙잭 참여자의 공통된 역할을 담당


# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)
