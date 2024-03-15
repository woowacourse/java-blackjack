package blackjack.domain.result;

import blackjack.domain.dealer.Dealer;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.participant.Players;
import java.util.Collections;
import java.util.Map;

public class WinningResult {
    private final Map<ParticipantName, WinStatus> participantsWinStatus;

    public WinningResult(final Map<ParticipantName, WinStatus> participantsWinStatus) {
        this.participantsWinStatus = participantsWinStatus;
    }

    public static WinningResult of(final Players players, final Dealer dealer) {
        ParticipantScoreStatus dealerScoreStatus = new ParticipantScoreStatus(dealer.isBlackjack(), dealer.calculate());
        return new WinningResult(players.determineWinStatus(dealerScoreStatus));
    }

    public Map<ParticipantName, WinStatus> getParticipantsResult() {
        return Collections.unmodifiableMap(participantsWinStatus);
    }
}
