package blackjack.dto;

public record DealerGameResult(
    int dealerWin,
    int dealerTie,
    int dealerLose
) {

}
