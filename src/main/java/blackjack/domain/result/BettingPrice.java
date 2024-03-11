package blackjack.domain.result;

import blackjack.exception.BettingPriceOutOfBoundException;

public class BettingPrice {
    private static final int MIN_BETTING_PRICE = 1_000;
    private static final int MAX_BETTING_PRICE = 100_000;

    private final int bettingPrice;

    public BettingPrice(int bettingPrice) {
        validateBettingPrice(bettingPrice);
        this.bettingPrice = bettingPrice;
    }

    private void validateBettingPrice(int bettingPrice) {
        if (bettingPrice < MIN_BETTING_PRICE || bettingPrice > MAX_BETTING_PRICE) {
            throw new BettingPriceOutOfBoundException(bettingPrice);
        }
    }

    public double calculateProfit(HandResult playerResult) {
        return playerResult.getProfitRate() * bettingPrice;
    }
}
