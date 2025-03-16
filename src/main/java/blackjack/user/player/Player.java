package blackjack.user.player;

import blackjack.card.Card;
import blackjack.card.CardHand;
import java.util.List;

public class Player {

    private static final int PLAYER_DISTRIBUTE_CARD_THRESHOLD = 21;
    private static final int PLAYER_OPEN_INITIAL_CARD_COUNT = 2;

    private final PlayerName playerName;
    private final CardHand cardHand;
    private BetAmount betAmount;

    public Player(final PlayerName playerName, final CardHand cardHand, final BetAmount betAmount) {
        this.playerName = playerName;
        this.cardHand = cardHand;
        this.betAmount = betAmount;
    }

    public static Player createPlayer(final PlayerName playerName, final BetAmount betAmount) {
        CardHand cardHand = new CardHand(PLAYER_DISTRIBUTE_CARD_THRESHOLD);
        return new Player(playerName, cardHand, betAmount);
    }

    public void addCards(final List<Card> cards) {
        cardHand.addCards(cards);
    }

    public void updateAmount(final BetAmount betAmount) {
        this.betAmount = betAmount;
    }

    public List<Card> openInitialCards() {
        return cardHand.openInitialCards(PLAYER_OPEN_INITIAL_CARD_COUNT);
    }

    public PlayerName getName() {
        return playerName;
    }

    public CardHand getCardHand() {
        return cardHand;
    }

    public BetAmount getBetAmount() {
        return betAmount;
    }
}
