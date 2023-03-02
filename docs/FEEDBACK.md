# 페어 프로그래밍을 하며 궁금했던 것들

- CardNumber가 일정 CardNumber보다 클 경우를 판단하기위해
1. isGreaterThan method를 사용하는 것이 좋다
2. Comparable을 구현하는게 좋다
둘의 장단점을 비교했을 때 무엇이 더 좋은 것인가요?.?

isGraterThan의 장점
- 객체에게 메시지를 보내어 결과값을 반환받을 수 있다.

Comparable의 장점
- 해당 객체가 비교가능한 값 객체임을 명시할 수 있다.
- Collection Api를 사용할 수 있다.
- 비교 관련된 로직을 compareTo로 집중할 수 있다.
