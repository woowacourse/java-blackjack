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

- 점수 클래스 생성
- 구조 변경 고민
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
- [x] 예외에 따라 뷰에 안내가 필요한 경우 추가
    - [x] 카드가 없을 시 뷰에 안내 메시지를 출력하고 게임 종료
- [x] 사용자 응답 (y,n)을 담을 enum 생성 (AdditionalCardReply ,view package)
- [x] Participant의 종류를 표현할 ParticipantType enum 생성
- [x] 메서드 분리, stream 또는 forEach 사용할 부분 체크
    - [x] OutputView 리팩터링
    - [x] Controller 리팩터링
    - [x] BlackjackGame 리팩터링
- [x] Outcome이 가지고 있던 승무패 도출 로직을 Participant로 이동

## 1단계 머지 후 리팩토링 목록

- [x] Symbol과 Number 정보를 조합하여 Map<String, Card> 형태로 관리하던 Card Cache를 EnumMap<Symbol, EnumMap<Number, Card>> 형태로 변경
- [x] Score 클래스 생성
- [x] OutcomeStatistics DTO로 변경를 (OutcomeSummaryDto)
- [x] Cards 클래스 생성 현재 Participant가 가지고 있는 점수 계산 로직 이동
- [ ] ParticipantDto 생성
- [ ] ParticipantsDto 생성
- [ ] DTO를 이용하도록 OutputView, Controller 변경
- [ ] state 패턴을 이용하여 게임 진행 관리

## 2단계 추가 요구 사항

- [ ] 플레이어에게 배팅 금액을 입력 받는 기능
- [ ] 베팅 금액 관련 도메인 생성
    - [ ] 예외: 자연수가 아닌 값 입력
- [ ] 최종 수익을 계산 하는 기능
- [ ] 최종 수익을 출력하는 기능
