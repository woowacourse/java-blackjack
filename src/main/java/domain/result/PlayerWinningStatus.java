package domain.result;

import domain.BlackJackWinningStatus;

public record PlayerWinningStatus(String playerName, BlackJackWinningStatus status) {
}
