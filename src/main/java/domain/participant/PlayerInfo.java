package domain.participant;

import domain.game.GameResult;
import java.math.BigDecimal;

public final class PlayerInfo {

    private final ParticipantName participantName;
    private PlayerBet playerBet;

    private PlayerInfo(final ParticipantName participantName) {
        this.participantName = participantName;
    }

    public static PlayerInfo create(final String playerName) {
        final ParticipantName participantName = ParticipantName.create(playerName);

        return new PlayerInfo(participantName);
    }

    public void bet(final int betAmount) {
        playerBet = PlayerBet.create(betAmount);
    }

    public BigDecimal calculateBenefit(final GameResult gameResult) {
        final double prizeRatio = gameResult.prizeRatio();

        return playerBet.calculateBenefit(prizeRatio);
    }

    public String getName() {
        return participantName.getName();
    }
}
