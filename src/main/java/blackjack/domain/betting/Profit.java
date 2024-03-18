package blackjack.domain.betting;

public record Profit(int value) {

    static Profit of(final Money money, final GameResult status) {
        return new Profit((int) (status.getProfitRate() * money.value()));
    }
}
