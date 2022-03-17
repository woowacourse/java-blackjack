package blackjack.domain.result;

import blackjack.domain.betting.Money;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;

import java.util.Objects;

public class ParticipantResult {

    private final Participant participant;
    private final Result result;

    public ParticipantResult(final Player player, final Result result) {
        validateParticipant(player);
        this.participant = (Participant) player;
        this.result = result;
    }

    private void validateParticipant(final Player player) {
        if (!player.isParticipant()) {
            throw new IllegalArgumentException("[ERROR] 참가자가 아닙니다.");
        }
    }

    public void calculate() {
        if (result.isWin() && participant.isBlackjack()) {
            participant.increaseBlackjackBetting();
            return;
        }
        if (result.isWin()) {
            participant.increaseBetting();
        }
        if (result.isLose()) {
            participant.decreaseBetting();
        }
    }

    public Money calculateDealer() {
        return new Money(-participant.betting().profit());
    }

    public Result getResult() {
        return this.result;
    }
}
