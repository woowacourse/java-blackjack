package blackjack.dto;

import blackjack.domain.card.Card;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class PersonStatusDto {

    private final String name;
    private final List<String> cards;

    private PersonStatusDto(String name, List<String> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static PersonStatusDto of(String name, List<Card> cards) {
        List<String> collect = cards.stream()
                .map(card -> card.getRank() + card.getSuit())
                .collect(toList());
        return new PersonStatusDto(name, collect);
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }
}