# java-blackjack

## 입력
- [x] 참여자의 이름을 입력 받는다.
- [x] [예외] 한글자 이상의 영어이름을 ,로 연결하여 입력하지 않은 경우
- [ ] 플레이어의 카드 숫자 합이 21 이하이면 카드를 더 받을지 입력 받는다.

## 출력
- [x] 딜러가 카드를 받으면 안내 메시지를 출력한다.
- [x] 딜러와 플레이어가 현재 가지고 있는 카드를 모두 출력한다.
- [ ] 딜러와 플레이어의 모든 카드와 합계를 출력한다.
- [ ] 딜러와 플레이어의 판정 결과(최종 승패)를 출력한다.

## 핵심 기능
- [x] 카드를 분배한다.
- [x] 플레이어가 카드를 뽑는다.
- [x] 플레이어가 가진 카드들의 합계를 구한다.
- [x] 플레이어의 카드 숫자 합이 21 이하인지 확인한다.
- [x] 딜러가 카드를 뽑는다.
- [x] 딜러가 가진 카드들의 합계를 구한다.
- [x] 딜러의 카드 숫자 합이 21 이하인지 확인한다.
- [x] 딜러의 카드 숫자 합이 16 이하인지 확인한다.
- [x] 딜러와 플레이어의 카드 숫자 합으로 승패를 판정한다.
- [x] [예외] 이름에 빈값이 입력된 경우

## 기능 요구 사항
블랙잭 게임을 변형한 프로그램을 구현한다.  
블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
게임을 완료한 후 각 플레이어별로 승패를 출력한다.

## 실행 결과
```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

딜러와 pobi, jason에게 2장을 나누었습니다.
딜러카드: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

딜러는 16이하라 한장의 카드를 더 받았습니다.

딜러카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17

## 최종 승패
딜러: 1승 1패
pobi: 승 
jason: 패
```