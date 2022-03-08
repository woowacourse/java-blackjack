# java-blackjack

블랙잭 미션 저장소

## 기능 요구 사항

> ## 전체 진행
`이름 받기 -> 기본 카드 공개 -> 플레이어 카드 추가 여부 -> 딜러 추가 카드 여부 -> 카드 및 점수 출력 -> 최종 승패 출력`


> ## 입력

- [ ] **이름 받기**
    - [ ] 쉼표 기준으로 받기
    - **예외처리**
        - [ ] 플레이어 이름은 공백이 아니어야한다.
        - [ ] 1명 이상이어야한다.

- [ ] 플레이어 카드 추가 여부
    - [ ] y또는 n으로만 입력 받기
    - [ ] y일 경우에는 카드 한장 더 추가 및 추가 여부 물어보기
    - [ ] n일 경우에는 카드 그만 받기
    - **예외처리**
        - [ ] y또는 n 이외의 값이 들어왔을 경우

> # 진행

- [ ] 딜러 추가 카드 여부
    - [ ] 16이하면 추가 지급
    - [ ] 17이상이면 추가 지급 없음

> ## 출력

- [ ] 기본 카드 공개
    - [ ] 플레이어와 딜러에게 카드 2장 지급
    - [ ] 플레이어는 카드 2장 출력
    - [ ] 딜러는 1장만 출력


- [ ] 카드 및 점수 결과 출력
    - [ ] 카드 number를 더해서 점수
    - [ ] Ace카드 1 또는 11 여부

- [ ] 최종 승패 출력
    - [ ] 플레이어들의 승패 여부
    - [ ] 딜러의 전적

---

## 논의사항

- 플레이어가 카드를 다 받은 뒤 딜러의 카드 합이 16이하면 한장을 추가로 받는다. (17이상이면 안받음)
    - 논의사항 : 안받을 경우 `안내메시지(딜러는 17이상이라 카드를 받지 않았습니다.)` 메세지를 출력해야하는가?
- A,8,3 일 경우 블랙잭

### 페어 룰

- 페어 프로그래밍 체인지 타임 (10분)
- 페어와 헤어지는 시점 (목요일 00시)
- [코드 컨벤션](https://github.com/woowacourse/woowacourse-docs/tree/master/styleguide/java)
- 커밋 작성 방법(한글)
- [프로그램 설계 방법](https://whimsical.com/sudaltest-JGN5vZ4gSkYxZGZJPjnnX3@2Ux7TurymNM4tJSA7FqU)
- 매일 저녁 하루 회고