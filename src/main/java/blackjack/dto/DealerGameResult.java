package blackjack.dto;

public record DealerGameResult(
    int winCount,
    int tieCount,
    int loseCount,
    long profit
) {

}
