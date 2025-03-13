package blackjack.model.player;

import blackjack.model.card.BlackJackCards;
import blackjack.model.card.CardDeck;
import blackjack.model.card.initializer.CardDeckInitializer;
import java.util.List;

public class Dealer extends BlackJackPlayer {

    private static final int INITIAL_DRAW_AMOUNT = 2;
    private static final int SINGLE_DRAW_AMOUNT = 1;
    private static final int DEALER_DRAWABLE_POINT = 17;
    private static final String DEFAULT_NAME = "딜러";

    private final CardDeck cardDeck;

    public Dealer(final String name, final CardDeck cardDeck) {
        super(name);
        this.cardDeck = cardDeck;
    }

    public Dealer(final CardDeckInitializer cardDeckInitializer) {
        this(DEFAULT_NAME, CardDeck.initializeFrom(cardDeckInitializer));
    }

    public void dealInitialCards(final List<Player> players) {
        receiveCards(drawCard(INITIAL_DRAW_AMOUNT));
        players.forEach(player -> player.receiveCards(drawCard(INITIAL_DRAW_AMOUNT)));
    }

    public boolean drawSelf() {
        try {
            drawMoreCard(this);
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    public boolean canDrawMoreCard(final BlackJackPlayer blackJackPlayer) {
        return blackJackPlayer.canDrawMoreCard();
    }

    public void drawMoreCard(final BlackJackPlayer blackJackPlayer) {
        if (canDrawMoreCard(blackJackPlayer)) {
            blackJackPlayer.receiveCards(drawCard(SINGLE_DRAW_AMOUNT));
            return;
        }
        throw new IllegalStateException("카드를 더 뽑을 수 없습니다.");
    }

    public boolean isDraw(final Player player) {
        if (player.isBust() || isBust()) {
            return false;
        }
        return calculateOptimalPoint() == player.calculateOptimalPoint();
    }

    public boolean isWin(final Player player) {
        if (player.isBust()) {
            return true;
        }
        if (isBust()) {
            return false;
        }
        return calculateOptimalPoint() > player.calculateOptimalPoint();
    }

    public boolean isBlackJack(final BlackJackPlayer blackJackPlayer) {
        return blackJackPlayer.calculateOptimalPoint() == BLACKJACK_POINT && blackJackPlayer.hasCardSize(2);
    }

    private BlackJackCards drawCard(final int amount) {
        return cardDeck.draw(amount);
    }

    @Override
    public BlackJackCards openInitialCards() {
        return new BlackJackCards(blackJackCards.getFirst());
    }

    @Override
    protected boolean canDrawMoreCard() {
        return getMinimumPoint() < DEALER_DRAWABLE_POINT;
    }
}
