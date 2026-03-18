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
- [x] 참가자 별 베팅 금액 저장 

## 피드백
- [x] CardInfo 제거하거나 변경
- [ ] 네이밍 
  - [ ] InfiniteDeck
  - [ ] BlackJackNumber -> Score
- [ ] PlayedGameResult -> Participants 에서 직접
- [ ] 예외처리 NumberFormatException
 