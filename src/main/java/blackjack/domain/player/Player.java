package blackjack.domain.player;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public abstract class Player {

    private static final String FIRST_RECEIVED_CARD_SIZE_EXCEPTION_MESSAGE = "처음 제공받는 카드는 2장이어야 합니다.";

    private final String name;
    protected final Cards cards;

    public Player(String name, List<Card> cards) {
        this.name = name;
        this.cards = new Cards(cards);
        validateReceivedCardsSize(this.cards);
    }

    private void validateReceivedCardsSize(Cards cards) {
        if (!cards.isFirstReceivedCards()) {
            throw new IllegalArgumentException(FIRST_RECEIVED_CARD_SIZE_EXCEPTION_MESSAGE);
        }
    }

    public abstract boolean isPossibleToPickCard();

    public final Boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public final void pickCard(Card card) {
        cards.addCard(card);
    }

    public final Result findResult(Player otherPlayer) {
        return Result.findResult(cards, otherPlayer.cards);
    }

    public final String getName() {
        return name;
    }

    public final List<Card> getCards() {
        return cards.getCards();
    }

    public final int getTotalScore() {
        return cards.calculateScore(cards.getTotalScore(), cards.getCountOfAce());
    }
}
