package blackjack.dto;

public record DealerResultDto(
        int winCount,
        int tieCount,
        int loseCount) {
}
