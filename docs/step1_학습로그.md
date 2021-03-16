# 학습로그 1-1

# [Java] LinkedList - 1
## 내용
- LinkedList는 크기로 초기화가 되지 않음
- LinkedList는 Queue, List 인터페이스를 구현함. 따라서 List 기능인 shuffle과 Queue를 같이 사용하려면 LinkedList 타입으로 받아주어야 한다. List타입으로 지정하면 Queue 구현이 안되고, Queue 타입으로 지정하면 shuffle 사용이 되지 않아 다운캐스팅 없이 LinkedList 타입으로 받아줘야 두 가지 인터페이스의 API를 모두 사용이 가능하다.
## 링크
- [javadocs LinkedList](https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html)

# [OOP] Interface & abstract Class - 5
## 내용
- 언제 interface를 사용하고 언제 abstract를 사용하는게 좋은걸까?
> abstract classes : defining characteristics of an object type, **specifying what an object is.**
> interface : definining capabilities that we promise to provide & establishing a contract about **what the object can do**.
- 추상클래스는 어떤 객체인지!를 정의하고 싶을 때, interface는 객체가 어떤 일을 할 수 있는지 정의하고 싶을 때 목적을 두는 것 같다.
- 인터페이스도 결국 중복 코드가 발생하기 때문에 추상 클래스가 되어야 하나 생각이 들기도 하지만, 어떤 목적을 의도하냐에 따라 달라지는 듯 하다.
- 추상클래스는 좀 더 객체를 구체화하는 성격이 강한 듯하다.
## 링크
- [stackoverflow 관련 답글](https://stackoverflow.com/questions/479142/when-to-use-an-interface-instead-of-an-abstract-class-and-vice-versa)

# [OOP] 상속과 캡슐화 - 4
## 내용
- 상속을 사용하면 캡슐화가 깨진다는 말이 어떤 의미일까?
- 캡슐화는 객체의 정보를 은닉(내부화)하여 외부에서는 밖으로 노출될 필요가 없는 정보들을 알 수 없도록 하는 것이다. 즉, 외부에서 함부로 내부 정보를 알수도 사용할수도 없게 하는 것이라고 생각한다.
- 상속을 사용하려면 public으로 열리는 정보들이 많아지게 되고, 자식 클래스에서 많은 정보를 알 수 밖에 없게 된다. 또한, 그 정보의 변경이 가능해진다. 이런 점에서 캡슐화가 깨진다는 의미로 사용되는 듯하다. 하지만, 상속을 사용해도 private 등을 사용할 수도 있기 때문에 상속을 한다고 모든 캡슐화가 깨지는 것은 아닌 듯 하다. "비밀이 항상 밝혀지는건 아니지만, 비밀을 많이 알면 세어나갈 위험이 큰 것" 처럼 상속을 사용하면 캡슐화가 깨질 가능성이 크다라고 생각한다. 좀 더 공부해볼 필요가 있다.👀

## 링크
[스스로 추가 공부하며 정리한 내용](https://nauni.tistory.com/167?category=913481)

# 학습로그 1-2

# [Java] Stream - 2
## 내용
- 2중 for문을 사용하기 위한 flatmap 사용. 
```
List<Card> cards = Arrays.stream(Suits.values())
        .flatMap(suit -> Arrays.stream(Denominations.values())
                .map(denomination -> Card.from(suit, denomination))
        )
        .collect(Collectors.toList());
```
- flatMap 내부에서 map을 한 번 더 연결해주어야 사용가능하다. flatMap 외부에서 suit를 사용하려면 사용이 불가능하다. 중간연산은 내부 값을 사용하여 다른 값으로 리턴해주기 때문이다.
원하는 대로 출력하려면 flatMap 내부 스트림에서 map을 한 번 더 연결해준다.
  

# [OOP] 가독성 - 3
## 내용
- 부정연산자(!)는 가독성을 해치고, 실수도 유발할 수 있는 부분이라 반대 의미의 메서드를 만들어서 사용하는 것이 좋다.
- compareTo 같은 경우 직접 연산을 해도 되긴 하지만 조금 위험해서요, 기본 타입의 경우는 Wrapper 클래스에서 비교 메서드를 제공하고 있다.
```
Integer.compare(score, o.score);
```
- 하나씩 선언과정을 끊는 것이 좋음
```
// BEFORE
return sumCards().isHigherThan(MINIMUM_SCORE_OF_NOT_TAKING_CARD);
// AFTER
Score score = sumCards();
return score.isHigherThan(MINIMUM_SCORE_OF_NOT_TAKING_CARD);
```
- boolean 반환 메서드의 경우 바로 return 값으로 사용하는 것 고려
- 불필요한 정보를 다 알아야 할까? 라는 접근 보다는, 메서드 시그니처를 보았을 때 명확해야한다.
- API를 설계할 때 다음 두 가지를 고려해보면 좋다.
    1. 이 메서드가 선언되었을 때의 모양이 자연스러운가?
    2. 클라이언트가 이 메서드를 사용할 때 사용법을 고민하지 않을 수 있는가?
    
# [OOP] abstract class - 3

## 내용
- abstract class () implements (interface) 는 interface의 모든 메소드를 구체화할 필요가 없다. 일부만 구체화하고 abstract class를 상속받을 자식 클래스에게 구체화 의무를 넘길 수 있다.
## 링크
[stackoverflow 관련 답글](https://stackoverflow.com/questions/197893/why-an-abstract-class-implementing-an-interface-can-miss-the-declaration-impleme)