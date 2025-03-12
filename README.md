# 블랙잭 요구 사항

## 플레이어 입력

- [ ] 쉼표 기준으로 파싱하기
- 예외
    - [ ] 최대 6명, 넘으면 재입력 받기
    - [ ] 공백이면 재입력 받기

## 베팅 금액 입력받기

- [ ] 플레이어별로 베팅 금액 입력받기
- 예외
    - [ ] 양수로만, 아니면 재입력받기
    - [ ] 10단위로만, 아니면 재입력받기

## 카드 초기 분배

- [ ] 딜러에게 2장 분배하기
- [ ] 플레이어들에게 2장씩 분배하기

## 카드 추가 분배

### 플레이어 카드 추가 분배 (push가 아닌 경우)

- [ ] 카드들의 합 구하기
- [ ] 카드들의 최소 합이 21미만이고, 최대합이 21이 아니면 재입력 받을 지 물어보기
- [ ] y 이면, 카드 추가 분배
    - [ ] 받은 카드 포함, 전체 카드 출력하기
- [ ] n 이면, 턴 종료

### 딜러 카드 추가 분배

- [ ] 카드들의 합구하기
- [ ] 카드들의 최소합 16이하이고, 최대합이 21이 아니면 추가 분배하기
- [ ] 카드들의 최소합이 17이상이면 턴 종료

## 딜러와 플레이어의 전체 카드, 합 출력

- [ ] 딜러와 플레이어가 가진 카드들 보여주기
- [ ] 카드의 합 계산해서 보여주기

## 승퍠 결과 계산하기

- [ ] 블랙잭인 딜러와 플레이어 찾기
- [ ] push인 경우
    - [ ] 합이 21인 딜러와 플레이어를 제외한 나머지 패 처리
    - [ ] 플레이어는 무승무 처리
- [ ] push가 아닌 경우
    - [ ] 플레이어가 bust면, 무조건 패 처리
    - [ ] 플레이어가 bust가 아니면
        - [ ] 딜러가 bust면 플레이어 승 처리
        - [ ] 딜러가 bust가 아니면 21에 가까운 사람 승 처리

## 베팅 금액 정산하기

- [ ] 플레이어의 정산금 계산하기
- [ ] 플레이어의 수익금 계산해서 뵤여주기
- [ ] 딜러의 수익금 계산해서 보여주기

