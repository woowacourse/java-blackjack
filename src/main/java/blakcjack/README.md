# 블랙잭 요구사항

- [x] 이름 입력 받기
    - [x] 예외: 이름 중복
    - [x] 예외: null, 빈 문자열
- [x] 각 플레이어와 딜러에게 카드를 2장씩 나눠 주는 기능
- [x] 딜러는 하나의 카드만 공개하도록 하는 기능
- [x] 플레이어는 처음 받은 2개의 카드를 모두 공개하는 기능
- [x] 각 플레이어가 카드를 추가로 받을지 결정하는 기능
    - [x] 이미 카드의 합이 21을 이상이면 받지 않도록 한다.
- [x] 각 플레이어의 카드를 프린트 하는 기능
- [x] 딜러가 추가로 카드를 받는 기능
    - 17 이상이 될 때까지 계속받는다

- [x] 각 플레이어와 딜러의 최종 카드 현황 출력 기능
- [x] 게임 승패 출력 기능

## 고려하고 있는 사항

- 사용자 응답 (y,n)을 담을 클래스 생성
- 점수 클래스 생성
- 구조 변경 고민
    - Outcome의 역할 고민 (player와 dealer를 받아 승무패를 얻어내는 로직의 위치)
    - Card 리스트를 가지는 일급컬렉션 생성
    - Player 리스트를 가지는 Players 생성
    - DTO를 만들만한 것 있는지 생각

## 1단계 피드백 이후 리팩토링 목록

- [x] 커스텀 예외 추가
    - [x] 카드 생성 실패 (Card)
        - Card의 필드가 enum(Symbol, Number)이라 컴파일 시 체크할 수 있다고 생각하여 해당 예외를 만들지 않음
        - 대신 FailedCacheHitException을 만듦 (현재 프로그램에서는 Cache 초기화 실수한 경우에만 예외 발생할 듯)
    - [x] 덱에 카드가 없는 경우 EmptyDeckException 발생 (Deck)
    - [x] 플레이어 중복시 GameInitializationFailureException 발생 (BlackjackGame)
    - [x] 카드를 더 뽑을지에 대한 응답이 올바르지 않은 경우 InvalidReplyException 발생 (AdditionalCardReply)
- [ ] 예외에 따라 뷰에 안내가 필요한 경우 추가
    - [x] 카드가 없을 시 뷰에 안내 메시지를 출력하고 게임 종료
- [x] 사용자 응답 (y,n)을 담을 enum 생성 (AdditionalCardReply ,view package)
- [ ] 메서드 분리, stream 또는 forEach 사용할 부분 체크
    - [x] OutputView 리팩터링
    - [x] Controller 리팩터링
    - [x] BlackjackGame 리팩터링
- [ ] 구조 변경
    - [ ] OutcomeStatistics DTO로 변경
