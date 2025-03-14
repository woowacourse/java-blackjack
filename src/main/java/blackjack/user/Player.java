package blackjack.user;

import blackjack.card.CardDeck;
import blackjack.card.CardHand;

public class Player {

    private static final int PLAYER_DISTRIBUTE_CARD_THRESHOLD = 21;

    private final PlayerName playerName;
    protected final CardHand cards;

    public Player(final PlayerName playerName, CardHand cards) {
        this.playerName = playerName;
        this.cards = cards;
    }

    public void addCards(CardDeck cardDeck, int count) {
        cards.addCards(cardDeck, count, PLAYER_DISTRIBUTE_CARD_THRESHOLD);
    }

    public boolean isPossibleToAdd() {
        return cards.isPossibleToAdd(PLAYER_DISTRIBUTE_CARD_THRESHOLD);
    }

    public PlayerName getName() {
        return playerName;
    }

    public CardHand getCards() {
        return cards;
    }
}
