package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.Result;
import java.util.List;

public abstract class Player {

    private static final String FIRST_RECEIVED_CARD_SIZE_EXCEPTION_MESSAGE = "처음 제공받는 카드는 2장이어야 합니다.";

    private final String name;
    private final Cards cards;

    public Player(final String name, final List<Card> cards) {
        this.name = name;
        this.cards = new Cards(cards);
        validateReceivedCardsSize(this.cards);
    }

    private void validateReceivedCardsSize(final Cards cards) {
        if (!cards.isInitialCards()) {
            throw new IllegalArgumentException(FIRST_RECEIVED_CARD_SIZE_EXCEPTION_MESSAGE);
        }
    }

    public abstract boolean canDrawCard();

    public final boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public final boolean isBust() {
        return cards.isBust();
    }

    public final void drawCard(final Card card) {
        cards.addCard(card);
    }

    public final Result findResult(final Player otherPlayer) {
        return Result.findResult(this, otherPlayer);
    }

    public final String getName() {
        return name;
    }

    public final List<Card> getCardsToList() {
        return cards.getCards();
    }

    public final int getTotalScore() {
        return cards.calculateScore(cards.sum(), cards.getCountOfAce());
    }
}
