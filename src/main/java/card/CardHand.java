package card;

import java.util.ArrayList;
import java.util.List;

public final class CardHand {
    private final List<Card> cards;

    private CardHand(final Card card1, final Card card2) {
        this.cards = new ArrayList<>(List.of(card1, card2));
    }

    public static CardHand drawInitialHand(final Deck deck) {
        Card card1 = deck.draw();
        Card card2 = deck.draw();
        return new CardHand(card1, card2);
    }

    public void add(Card newCard) {
        cards.add(newCard);
    }

    public GameScore calculateScore() {
        GameScore totalScore = new GameScore(cards.stream()
                .mapToInt(Card::score)
                .sum());
        if (hasAce()) {
            return totalScore.withAce();
        }
        return totalScore;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return calculateScore().isBust();
    }

    public boolean isBlackJack() {
        return calculateScore().isBlackJack();
    }

    public boolean isDealerStay() {
        return !calculateScore().isDealerHit();
    }


    public List<Card> getCards() {
        return cards;
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }
}
