package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private final String name;
    private final List<Card> cards;

    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public void pickCard(Card card) {
        cards.add(card);
    }

    public boolean isPossibleToPickCard() {
        return calculateScore() <= 21;
    }

    public int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
