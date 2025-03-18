# java-blackjack

블랙잭 미션 저장소

# 요구사항

## 플레이어 이름

- [x] 게임에 참여할 사람의 이름을 입력한다.
    - [x] 이름은 쉼표를 기준으로 분리한다.
    - [x] 게임의 최소 1명, 최대 4명까지 가능하다.
    - [x] 닉네임의 길이는 최소 2자, 최대 5자까지 가능하다.
    - [x] 닉네임은 중복을 허용하지 않는다.

## 플레이어 배팅

- [x] 플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다.
    - [x] 배팅 금액은 1,000원 이상, 100,000원 이하여야 한다.

## 카드

- [x] 에이스는 경우에 따라 1 또는 11로 적용된다.
    - [x] 딜러의 경우, 첫 에이스는 11로 적용되고, 그 다음 에이스부터는 1로 적용된다.
    - [x] 플레이어의 경우, 1 또는 11중에 유리한 방향으로 적용된다.
    - [x] JACK, KING, QUEEN은 10으로 간주한다.

## 게임 세팅

- [x] 덱은 하나로 구성된다.
- [x] 게임 시작 시, 딜러, 플레이어에게 모두 2장씩 분배한다.
- [x] 플레이어는 모든 카드를 공개하고, 딜러는 1장만 공개한다.
- [x] 카드의 합이 21을 넘을 경우 버스트 처리된다.

## 게임 진행

- [x] 플레이어들에게 카드를 더 받을 것인지 묻는다.
    - [x] 플레이어는 자신의 카드 합이 20 이하인 경우 카드를 뽑을 수 있다.
    - [x] 버스트 될 경우, 다음 사람으로 자동으로 넘어간다.
- [x] 딜러는 처음에 받은 2장의 합계가 16 이하라면 카드를 더 받는다.

## 게임 결과

- [x] 딜러가 이기는 경우
    - [x] 딜러가 버스트가 아니면서 플레이어가 버스트일 때
    - [x] 둘 다 버스트가 아니면서 플레이어보다 합계가 높을 때
    - [x] 둘 다 버스트일 때
- [x] 플레이어가 이기는 경우
    - [x] 딜러가 버스트면서 플레이어가 버스트가 아닐 때
    - [x] 둘 다 버스트가 아니면서 딜러보다 합계가 높을 때
- [x] 무승부인 경우
    - [x] 둘 다 블랙잭일 때
    - [x] 둘 다 버스트가 아니면서 합계가 같을 때
- [x] 플레이어는 패배 시 배팅 금액을 모두 잃게 된다.
- [x] 플레이어는 처음 두 장의 카드 합이 21일 경우 블랙잭이며 배팅 금액의 1.5배를 받는다. 이후 이 플레이어는 게임을 진행하지 않는다.
- [x] 동시에 블랙잭이라면 플레이어는 배팅한 금액을 돌려받는다.
- [x] 딜러가 버스트라면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 배팅 금액을 받는다.

# 셀프 체크 리스트

## 코드 품질

- [x] 테스트 커버리지가 100퍼인가? (뷰, 컨트롤러, Getter, equals 등 제외)
- [x] 디미터의 법칙을 지켰는가?
- [x] 불필요한 개행, 공백 컨벤션을 지켰는가?
- [x] 메서드 순서 컨벤션을 지켰는가?
- [x] 불변 객체를 사용했는가? (가능한 경우)
- [x] 상수는 적절히 추출되었는가?
- [x] 매직 넘버/문자열이 없는가?

## 네이밍

- [x] 모든 이름이 도메인 용어와 일치하는가?
- [x] 메서드 이름이 동사로 시작하는가?
- [x] 클래스 이름이 명사로 되어 있는가?
- [x] Boolean 변수/메서드가 is/has/can 등으로 시작하는가?
- [x] 약어 사용을 피했는가? (IDE, IO 등 표준 약어 제외)
