package domain;

import java.util.List;

public class Player {

    private List<Card> cards;

    private Player(List<Card> cards) {
        this.cards = cards;
    }

    public static Player of(List<Card> cards) {
        return new Player(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int cardScore = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        if (isBustWithAce(cardScore)) {
            cardScore -= 10;
        }

        return cardScore;
    }

    private boolean isBustWithAce(int cardScore) {
        return cardScore > 21 && cards.stream().anyMatch(Card::isAce);
    }
}
