package blackjack.dto;

import static java.util.stream.Collectors.toList;

import blackjack.domain.Card;
import blackjack.domain.Person;
import java.util.List;

public class PersonStatusResponse {
    private final String name;
    private final List<String> cards;

    public PersonStatusResponse(String name, List<String> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static PersonStatusResponse of(Person person) {
        return new PersonStatusResponse(person.getName(), getCardsStatus(person.getCards()));
    }

    public static PersonStatusResponse ofStart(Person person) {
        return new PersonStatusResponse(person.getName(), getCardsStatus(person.getStartCards()));
    }

    private static List<String> getCardsStatus(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getRank() + card.getSuit())
                .collect(toList());
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }
}
