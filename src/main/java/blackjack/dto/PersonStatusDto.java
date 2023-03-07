package blackjack.dto;

import blackjack.domain.Card;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class PersonStatusDto {

    private final String name;
    private final List<Card> cards;

    private PersonStatusDto(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static PersonStatusDto of(String name, List<Card> cards) {
        return new PersonStatusDto(name, cards);
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards.stream()
                .map(card -> card.getRank() + card.getSuit())
                .collect(toList());
    }
}
