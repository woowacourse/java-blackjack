package blackjack.domain.participants;


import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public class Player {
    private final String name;
    private final Cards cards;
    private final Score score;

    public Player(String name, Cards cards) {
        this.name = name.trim();
        this.cards = cards;
        this.score = new Score(cards);
    }

    public void take(Card card1, Card card2) {
        this.cards.take(card1, card2);
    }

    public void additionalTake(Card card) {
        this.cards.additionalTake(card);
    }

    public boolean canTake() {
        return score.canTake();
    }

    public int calculateMaxScore() {
        return cards.calculateMaxScore();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public Cards getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }
}
