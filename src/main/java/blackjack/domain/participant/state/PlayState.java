package blackjack.domain.participant.state;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHands;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.BettingAmount;

public abstract class PlayState {

    private final CardHands cards;
    private final BettingAmount bettingAmount;

    protected PlayState(final List<Card> cards, final int amount) {
        this.cards = new CardHands(cards);
        this.bettingAmount = new BettingAmount(amount);
    }

    protected PlayState(final PlayState state) {
        this(state.getCards(), state.getBettingAmount());
    }

    public abstract PlayState betAmount(final int amount);

    public abstract PlayState drawCard(final Deck deck);

    public void addCard(final Deck deck) {
        cards.addCard(deck.drawCard());
    }

    public abstract boolean isPossibleToDrawCard();

    public int getScore() {
        return cards.calculateScore();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public int getBettingAmount() {
        return bettingAmount.getAmount();
    }

}
