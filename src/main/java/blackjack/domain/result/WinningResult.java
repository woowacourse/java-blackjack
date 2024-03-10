package blackjack.domain.result;

import blackjack.domain.participant.ParticipantName;
import blackjack.domain.participant.Players;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WinningResult {
    private final Map<ParticipantName, WinStatus> participantsWinStatus;

    private WinningResult(final Map<ParticipantName, WinStatus> participantsWinStatus) {
        this.participantsWinStatus = participantsWinStatus;
    }

    public static WinningResult of(final Players players, final Score dealerScore) {
        return new WinningResult(players.determineWinStatus(dealerScore));
    }

    public Map<WinStatus, Long> summarizeDealerWinningResult() {
        return participantsWinStatus.values().stream()
                .map(WinStatus::opposite)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Map<ParticipantName, WinStatus> getParticipantsWinStatus() {
        return Collections.unmodifiableMap(participantsWinStatus);
    }
}
