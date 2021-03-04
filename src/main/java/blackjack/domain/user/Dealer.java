package blackjack.domain.user;

import blackjack.domain.MatchResultType;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Dealer implements User {
    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_HIT_THRESHOLD = 16;

    private final Cards cards;

    public Dealer() {
        this.cards = new Cards();
    }

    public boolean canHit() {
        return getScore() <= DEALER_HIT_THRESHOLD;
    }

    public Card getFirstCard() {
        return this.cards.getFirstCard();
    }

    public MatchResultType compare(Player player) {
        return MatchResultType.getStatus(this.cards.getScore(), player.getScore());
    }

    @Override
    public void hit(Card card) {
        this.cards.addCard(card);
    }

    @Override
    public String getCards() {
        return this.cards.getCards();
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    @Override
    public int getScore() {
        return cards.getScore();
    }
}
