package blackjack.model.blackjack_player.dealer;

import blackjack.model.blackjack_player.Hand;
import blackjack.model.blackjack_player.player.Player;
import blackjack.model.card.BlackJackCard;
import blackjack.model.card.BlackJackCards;
import blackjack.model.card.CardDeck;
import blackjack.model.card.initializer.CardDeckInitializer;
import java.util.List;

public class Dealer {

    private static final float BLACKJACK_REWARD_RATE = 1.5f;
    private static final int BLACKJACK_PLAYER_SINGLE_DRAW_AMOUNT = 1;
    private static final int BLACKJACK_DEALER_SINGLE_DRAW_AMOUNT = 1;
    private static final int PLAYER_DRAWABLE_POINT = 21;
    private static final int DEALER_DRAWABLE_POINT = 17;
    private static final int BLACKJACK_PLAYER_INITIAL_DRAW_AMOUNT = 2;


    private final Hand hand;
    private final CardDeck cardDeck;

    public Dealer(final Hand hand, final CardDeck cardDeck) {
        this.hand = hand;
        this.cardDeck = cardDeck;
    }

    public Dealer(final CardDeckInitializer cardDeckInitializer) {
        this(Hand.empty(), CardDeck.initializeFrom(cardDeckInitializer));
    }

    public void dealInitialCards(final List<Player> players) {
        hand.addCards(drawCard(BLACKJACK_PLAYER_INITIAL_DRAW_AMOUNT));
        players.forEach(player -> player.receiveCards(drawCard(BLACKJACK_PLAYER_INITIAL_DRAW_AMOUNT)));
    }

    public boolean drawSelf() {
        if (canDrawMoreCard()) {
            hand.addCards(drawCard(BLACKJACK_DEALER_SINGLE_DRAW_AMOUNT));
            return true;
        }
        return false;
    }

    public void drawPlayerCards(final Player player) {
        if (canPlayerDrawMoreCard(player)) {
            player.receiveCards(drawCard(BLACKJACK_PLAYER_SINGLE_DRAW_AMOUNT));
            return;
        }
        throw new IllegalStateException("카드를 더 뽑을 수 없습니다.");
    }

    public void fight(final Player player) {
        if (isDraw(player)) {
            return;
        }
        if (isWin(player)) {
            player.loseMoney();
            return;
        }
        player.earnMoney(calculateReward(player));
    }

    private int calculateReward(final Player player) {
        int bettingMoney = player.getBettingMoney();
        if (player.isBlackjack()) {
            return Math.round(bettingMoney * BLACKJACK_REWARD_RATE);
        }
        return bettingMoney;
    }

    private boolean isDraw(final Player player) {
        if (player.isBust() || hand.isBust() || player.isBlackjack() || hand.isBlackjack()) {
            return false;
        }
        return hand.calculateOptimalPoint() == player.getOptimalPoint();
    }

    private boolean isWin(final Player player) {
        if (player.isBlackjack() || hand.isBust()) {
            return false;
        }
        if (player.isBust() || hand.isBlackjack()) {
            return true;
        }
        return hand.calculateOptimalPoint() > player.getOptimalPoint();
    }

    private BlackJackCards drawCard(final int amount) {
        return cardDeck.draw(amount);
    }

    public int getProfit(final List<Player> players) {
        return (-1) * players.stream()
                .mapToInt(Player::getProfit)
                .sum();
    }

    public int getOptimalPoint() {
        return hand.calculateOptimalPoint();
    }

    public BlackJackCards openCards() {
        return new BlackJackCards(getFirstCard());
    }

    private BlackJackCard getFirstCard() {
        return getAllCards().getFirst();
    }

    public BlackJackCards getAllCards() {
        return hand.getCards();
    }

    public boolean canPlayerDrawMoreCard(final Player player) {
        return player.getOptimalPoint() < PLAYER_DRAWABLE_POINT;
    }

    private boolean canDrawMoreCard() {
        return hand.getMinimumPoint() < DEALER_DRAWABLE_POINT;
    }
}
