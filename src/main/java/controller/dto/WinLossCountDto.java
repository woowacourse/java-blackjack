package controller.dto;


public record WinLossCountDto(
        int winCount,
        int lossCount,
        int drawCount
) {
}
