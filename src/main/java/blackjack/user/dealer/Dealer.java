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

    public void addCards(final int count) {
        List<Card> cards = pickCards(count);
        cardHand.addCards(cards);
    }

    public List<Card> pickCards(final int count) {
        return Stream.generate(cardDeck::pickRandomCard)
            .limit(count)
            .collect(Collectors.toList());
    }

    public List<Card> openInitialCards() {
        return cardHand.openInitialCards(DEALER_OPEN_INITIAL_CARD_COUNT);
    }

    public int calculateProfitForPlayer(final Player player) {
        GameResult playerGameResult = judgePlayerResult(player.getCardHand());
        return calculateProfit(playerGameResult, player.getCardHand().isBlackjack(),
            player.getBetAmount().getPrincipal());
    }

    private GameResult judgePlayerResult(final CardHand playerHand) {
        if (playerHand.isBust()) {
            return GameResult.LOSE;
        }
        if (playerHand.isBlackjack() && this.cardHand.isBlackjack()) {
            return GameResult.DRAW;
        }
        if (playerHand.isBlackjack() || this.cardHand.isBust()) {
            return GameResult.WIN;
        }
        return GameResult.fromDenominationsSum(this.getCardHand().calculateDenominations(),
            playerHand.calculateDenominations());
    }

    private int calculateProfit(final GameResult gameResult, final boolean isBlackjack,
        final int principal) {
        return gameResult.calculateProfit(isBlackjack, principal);
    }

    public CardHand getCardHand() {
        return cardHand;
    }
}
