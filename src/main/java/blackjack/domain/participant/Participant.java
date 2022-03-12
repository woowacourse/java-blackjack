package blackjack.domain.participant;

import java.util.List;

import blackjack.domain.card.CardHands;
import blackjack.domain.card.Deck;

public abstract class Participant {

    private final String name;
    protected CardHands cards = new CardHands();

    protected Participant(final String name, final Deck deck) {
        validateNameNotBlank(name);
        this.name = name;
        drawTwoCard(deck);
    }

    private static void validateNameNotBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("플레이어명은 공백이 될 수 없습니다.");
        }
    }

    private void drawTwoCard(final Deck deck) {
        drawCard(deck);
        drawCard(deck);
    }

    public void drawCard(final Deck deck) {
        cards.addCard(deck.drawCard());
    }

    public void drawCards(final Deck deck, final CardDrawCallback callback) {
        while (isPossibleToDrawCard() && callback.isContinuable(getParticipantName())) {
            drawCard(deck);
            callback.onUpdate(name, cards.getCardNames());
        }
    }

    public abstract boolean isPossibleToDrawCard();

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public String getParticipantName() {
        return name;
    }

    public List<String> getCardNames() {
        return cards.getCardNames();
    }

    public int getScore() {
        return cards.calculateScore();
    }

}
