package blackjack.dto;

import blackjack.domain.card.Card;
import java.util.List;
import java.util.stream.Collectors;

public class CardCountingResult {
    private final String name;
    private final List<String> cards;
    private final int count;

    public CardCountingResult(String playerName, List<Card> cards, int count) {
        this.name = playerName;
        this.cards = toCardNames(cards);
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }

    public int getCount() {
        return count;
    }

    private List<String> toCardNames(List<Card> cards) {
        return cards.stream()
            .map(card -> card.getNumber().getName() + card.getSuit().getName())
            .collect(Collectors.toList());
    }
}
