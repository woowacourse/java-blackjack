package blackjack.user;

import blackjack.card.Card;
import blackjack.card.CardDeck;
import blackjack.card.CardHand;
import java.util.List;

public class Player {

    private static final int PLAYER_OPEN_INITIAL_CARD_COUNT = 2;
    private static final int PLAYER_DISTRIBUTE_CARD_THRESHOLD = 21;

    private final PlayerName playerName;
    protected final CardHand cards;

    public Player(final PlayerName playerName, CardHand cards) {
        this.playerName = playerName;
        this.cards = cards;
    }

    public List<Card> openInitialCards() {
        return cards.openInitialCards(PLAYER_OPEN_INITIAL_CARD_COUNT);
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
