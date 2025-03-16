package blackjack.user.dealer;

import blackjack.card.Card;
import blackjack.card.CardDeck;
import blackjack.card.CardHand;
import blackjack.game.GameResult;
import blackjack.user.player.Player;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dealer {

    private static final int DEALER_DISTRIBUTE_CARD_THRESHOLD = 17;
    private static final int DEALER_OPEN_INITIAL_CARD_COUNT = 1;
    private static final double BLACKJACK_PROFIT_RATE = 1.5;
    private static final int DRAW_PROFIT = 0;

    private final CardDeck cardDeck;
    private final CardHand cardHand;

    public Dealer(final CardDeck cardDeck, final CardHand cardHand) {
        this.cardDeck = cardDeck;
        this.cardHand = cardHand;
    }

    public static Dealer createDealer(final CardDeck cardDeck) {
        CardHand cardHand = new CardHand(DEALER_DISTRIBUTE_CARD_THRESHOLD);
        return new Dealer(cardDeck, cardHand);
    }

    public List<Card> pickCards(int count) {
        return Stream.generate(cardDeck::pickRandomCard)
            .limit(count)
            .collect(Collectors.toList());
    }

    public void addCards(int count) {
        List<Card> cards = pickCards(count);
        cardHand.addCards(cards);
    }

    public List<Card> openInitialCards() {
        return cardHand.openInitialCards(DEALER_OPEN_INITIAL_CARD_COUNT);
    }

    public GameResult judgePlayerResult(final Player player) {
        CardHand playerCards = player.getCardHand();
        if (playerCards.isBust()) {
            return GameResult.LOSE;
        }
        if (playerCards.isBlackjack() && this.cardHand.isBlackjack()) {
            return GameResult.DRAW;
        }
        if (playerCards.isBlackjack() || this.cardHand.isBust()) {
            return GameResult.WIN;
        }
        return GameResult.fromDenominationsSum(this, player);
    }

    public int calculateProfitForPlayer(final Player player) {
        GameResult playerGameResult = judgePlayerResult(player);
        return calculateProfit(playerGameResult, player.getCardHand().isBlackjack(), player.getBetAmount().getPrincipal());
    }

    private int calculateProfit(final GameResult gameResult, final boolean isBlackjack, final int principal) {
        if (gameResult.isWin() && isBlackjack) {
            return (int) (principal * BLACKJACK_PROFIT_RATE);
        }
        if (gameResult.isWin()) {
            return principal;
        }
        if (gameResult.isLose()) {
            return -principal;
        }
        return DRAW_PROFIT;
    }

    public CardHand getCardHand() {
        return cardHand;
    }
}
