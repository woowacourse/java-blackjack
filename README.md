# java-blackjack

블랙잭 미션 저장소

## 기능 요구 사항

1. Card
- [x] 점수 가치 변환
  - enum에서 label에 대응하는 score 반환
  - `"2" -> 2, "J" -> 10`(A 제외)
- [x] 카드 정보 `String` 반환(ex. `"2하트"`)

2. Hand
- [x] 랜덤 카드 뽑기
- [x] 점수의 합 계산
- [x] 카드 정보들 `List<String>` 반환
- [x] 버스트 (21 초과 시) 상태 반환
- [x] ACE카드 처리(버스트 시, 1로 변환 처리)

3. Participant
- 공통
  - [x] 참가자 이름 반환
- 딜러
  - [x] 카드를 더 받을 수 있는 상태(16이하)인지 반환
- 플레이어
  - [x] 카드를 더 받을 수 있는 상태(20이하)인지 반환

4. GameTable
- [x] 참가자 상태 관리

5. ScoreBoard
- [ ] 참가자 별 베팅 금액 저장 
- [ ] 승패 판독

## 피드백
- [x] HandTest isBusted() 테스트 추가하기
- [x] isExactlyInstanceOf()로 변경
- [ ] PlayedGameResult Dto화
- [ ] BlackJackRule 관련
- [x] AppConfig 에 DrawStrategy 변경
- [x] WinDrawLose 네이밍
- [x] 레포지토리 책임 조정하기
  - 레포지토리 삭제
- [ ] domain의 common 패키지 조정하기
- [x] DrawStrategy 한 곳에서만 상태를 관리하게 하기
  - 피드백에 따라 카드나 전략을 파라미터로 전달하는 방식에 대해 많은 고민을 해봤습니다.
  객체의 깊이가 깊어짐에 따라 파라미터로 주입되어야 하는 체인이 길어져 영향 범위가 커질 것이라 판단했습니다.
  그래서 하나의 카드 덱을 공유하는 블랙잭 특성상 `AppConfig`에서 단 하나의 덱 인스턴스를 생성해 주입하되, 
  여러 참가자 인스턴스가 생성되는 지점에서는 `sharedDeck`이라는 네이밍으로 공유 의도를 명시했습니다.
