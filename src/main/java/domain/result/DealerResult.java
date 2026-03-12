package domain.result;

public record DealerResult(
        String name,
        int winCount,
        int drawCount,
        int loseCount
) {
}
