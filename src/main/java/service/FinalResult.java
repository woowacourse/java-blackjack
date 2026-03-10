package service;

public record FinalResult(
        String name,
        int winCount,
        int drawCount,
        int loseCount,
        boolean isDealer
) {
}
