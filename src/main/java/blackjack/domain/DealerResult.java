package blackjack.domain;

public record DealerResult(
        long dealerWinCount,
        long dealerDrawCount,
        long dealerLoseCount
) {
}
