package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.playerstatus.Blackjack;
import blackjack.domain.participant.playerstatus.Hit;
import blackjack.domain.participant.playerstatus.PlayerStatus;

public abstract class Participant {

    private static final int INITIAL_CARD_COUNT = 2;

    protected final Cards cards;
    protected PlayerStatus playerStatus;

    public Participant() {
        this.cards = new Cards();
        this.playerStatus = Hit.getInstance();
    }

    public void initCards(Deck deck) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            cards.add(deck.drawCard());
        }
    }

    public int getScore() {
        return cards.sumValue();
    }

    public Cards getCards() {
        return cards;
    }

    public boolean isBlackjack() {
        return playerStatus == Blackjack.getInstance();
    }

    protected boolean checkBlackjack() {
        return cards.isBlackjack(INITIAL_CARD_COUNT);
    }

    public PlayerStatus getStatus() {
        return playerStatus;
    }

    public abstract void hit(Deck deck);

    public abstract String getName();

    public abstract boolean isDrawable();
}
