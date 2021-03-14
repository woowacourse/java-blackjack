# [Java] BigDecimal VS BigInteger - 1

## 내용

- 돈과 관련된 것은 BigDecimal을 사용하는 것이 좋다.
- 둘다 문자열로 표현된다. 하지만, BigDecimal은 소수점 자리도 표현되지만 BigInteger는 소수점 표현이 되지 않는다.

# [Java] Integer.compare() - 1

## 내용

- compareTo 같은 경우 직접 연산을 해도 되긴 하지만 조금 위험할 수 있다.
- 기본 타입의 경우는 Wrapper 클래스에서 비교 메서드를 제공한다. `Integer.compare(score, o.score)`

# [Java] Cache - 3

## 내용

- List와 같이 자료구조를 담는 클래스는 캐싱하여 static으로 관리하여 사용할 시, 문제가 될 수 있다.
- `public static final Cards EMPTY_CARDS = new Cards(Collections.emptyList())`를 사용하려고 했더니 테스트케이스에서
  통과되지 않는 경우가 발생하였다.
- 불변객체가 아니라면 캐싱하지 않는다.