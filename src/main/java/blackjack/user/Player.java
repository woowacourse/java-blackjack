package blackjack.user;

import blackjack.card.CardDeck;
import blackjack.card.CardHand;

public class Player {

    private final PlayerName playerName;
    protected final CardHand cards;

    public Player(final PlayerName playerName, CardHand cards) {
        this.playerName = playerName;
        this.cards = cards;
    }

    public void addCards(CardDeck cardDeck, int count) {
        cards.addCards(cardDeck, count);
    }

    public boolean isPossibleToAdd() {
        return cards.isPossibleToAdd();
    }

    public PlayerName getName() {
        return playerName;
    }

    public CardHand getCards() {
        return cards;
    }
}
