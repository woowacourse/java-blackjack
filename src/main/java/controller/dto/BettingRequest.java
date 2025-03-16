package controller.dto;

public record BettingRequest(
        String playerName,
        int bettingMoney
) {
}
