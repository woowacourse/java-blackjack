package blackjack.gamer;

import blackjack.card.CardDeck;
import blackjack.card.Hand;
import blackjack.result.BlackjackMatchResult;
import blackjack.result.ProfitResult;
import blackjack.state.started.finished.Stay;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Gamer {

    private static final String NAME = "딜러";
    private static final int HIT_THRESHOLD = 16;

    public Dealer(Hand hand) {
        super(hand);
    }

    private BlackjackMatchResult determineMatchResultFor(Player player) {
        return getHand().determineMatchResultFor(player.getHand());
    }

    public ProfitResult calculateProfits(List<Player> players) {
        BigDecimal dealerProfit = BigDecimal.valueOf(0);
        Map<Player, BigDecimal> playerProfits = new LinkedHashMap<>();

        for (Player player : players) {
            BlackjackMatchResult playerResult = determineMatchResultFor(player);
            BigDecimal profit = player.getProfit(playerResult);

            dealerProfit = dealerProfit.subtract(profit);
            playerProfits.put(player, profit);
        }

        return new ProfitResult(dealerProfit, playerProfits);
    }

    @Override
    public void initialDeal(CardDeck cardDeck) {
        state = state.initialDeal(cardDeck);
        checkState(getHand());
    }

    private void checkState(Hand hand) {
        if (hand.calculateTotalPoint() > HIT_THRESHOLD && !hand.isBlackjack()) {
            state = new Stay(hand);
        }
    }

    @Override
    public String getNickname() {
        return NAME;
    }

    @Override
    public void hit(CardDeck deck) {
        super.hit(deck);
    }
}
