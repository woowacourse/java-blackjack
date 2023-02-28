package blackjack.domain;

public class Person {
    private final String name;
    private final Cards cards;

    public Person(String name) {
        this.name = name;
        this.cards = new Cards();
    }
}
