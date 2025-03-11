package blackjack.domain.participant;

import blackjack.domain.GameResult;

public record Payout(
        int value
) {

    public static Payout zero() {
        return new Payout(0);
    }

    public static Payout blackjackPayout(final int value) {
        int blackjackResult = (int) Math.floor(value * 1.5);
        return new Payout(blackjackResult);
    }

    public Payout bet(final int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("베팅 금액은 0원 이상 입력해주세요.");
        }
        return new Payout(this.value + amount);
    }

    public Payout add(Payout other) {
        return new Payout(this.value + other.value);
    }

    public Payout calculatePayout(final Dealer dealer, final Player player) {
        GameResult result = GameResult.getPlayerGameResultFrom(dealer, player);
        if (result == GameResult.LOSE) {
            return negate();
        }
        if (player.isBlackjack()) {
            return calculatePayoutWithBlackjack(dealer);
        }
        if (result == GameResult.DRAW) {
            return zero();
        }
        return this;
    }

    private Payout calculatePayoutWithBlackjack(final Dealer dealer) {
        if (dealer.isBlackjack()) {
            return this;
        }
        return blackjackPayout(value);
    }

    public Payout negate() {
        return new Payout(-value);
    }
}
