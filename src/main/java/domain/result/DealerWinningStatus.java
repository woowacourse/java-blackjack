package domain.result;

import domain.BlackJackWinningStatus;

public record DealerWinningStatus(String opponentName, BlackJackWinningStatus status) {
}
