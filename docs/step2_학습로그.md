# [Java] BigDecimal VS BigInteger - 1

## 내용

- 돈과 관련된 것은 BigDecimal을 사용하는 것이 좋다.
- 둘다 문자열로 표현된다. 하지만, BigDecimal은 소수점 자리도 표현되지만 BigInteger는 소수점 표현이 되지 않는다.

# [Java] Integer.compare() - 1

## 내용
- compareTo 같은 경우 직접 연산을 해도 되긴 하지만 조금 위험할 수 있다.
- 기본 타입의 경우는 Wrapper 클래스에서 비교 메서드를 제공한다. `Integer.compare(score, o.score)`