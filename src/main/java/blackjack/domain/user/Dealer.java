package blackjack.domain.user;

import blackjack.domain.card.CardGroup;
import blackjack.domain.result.Score;

public class Dealer extends User {

    private static final double TIE_PROFIT_RATE = 0.0;
    private static final double BLACKJACK_PROFIT_RATE = 1.5;
    private static final double WIN_PROFIT_RATE = 1.0;
    private static final double LOSE_PROFIT_RATE = -WIN_PROFIT_RATE;
    private static final int FIRST_OPEN_CARD_COUNT = 1;
    private static final int DRAW_LIMIT_SCORE = 16;

    private static final DealerName dealerName = new DealerName();

    public Dealer(final CardGroup initialGroup) {
        super(initialGroup);
    }

    @Override
    public CardGroup getFirstOpenCardGroup() {
        return cardGroup.getSubCardGroup(FIRST_OPEN_CARD_COUNT);
    }

    public boolean isUnderDrawLimit() {
        return !getScore().isBigger(Score.from(DRAW_LIMIT_SCORE));
    }

    public Double calculateProfitRate(final Player player) {
        final Score dealerScore = getScore();
        final Score playerScore = player.getScore();
        if (playerScore.isBlackjack()) {
            return calculateProfitRateWhenPlayerIsBlackJack(dealerScore);
        }
        if (playerScore.isBust()) {
            return LOSE_PROFIT_RATE;
        }
        if (dealerScore.isBust()) {
            return WIN_PROFIT_RATE;
        }
        return calculateProfitRateByScoreValue(player);
    }

    private Double calculateProfitRateWhenPlayerIsBlackJack(final Score dealerScore) {
        if (dealerScore.isBlackjack()) {
            return TIE_PROFIT_RATE;
        }
        return BLACKJACK_PROFIT_RATE;
    }

    private Double calculateProfitRateByScoreValue(final Player player) {
        final Score dealerScore = getScore();
        final Score playerScore = player.getScore();

        if (playerScore.isBigger(dealerScore)) {
            return WIN_PROFIT_RATE;
        }
        if (playerScore.isEqual(dealerScore)) {
            return TIE_PROFIT_RATE;
        }
        return LOSE_PROFIT_RATE;
    }

    @Override
    public Name getName() {
        return dealerName;
    }
}
