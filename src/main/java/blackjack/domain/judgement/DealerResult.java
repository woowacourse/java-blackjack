package blackjack.domain.judgement;

public record DealerResult(
        long dealerWinCount,
        long dealerDrawCount,
        long dealerLoseCount
) {
}
