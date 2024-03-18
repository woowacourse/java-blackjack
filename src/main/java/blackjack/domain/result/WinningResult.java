package blackjack.domain.result;

import blackjack.domain.Name;
import blackjack.domain.status.WinStatus;
import java.util.Collections;
import java.util.Map;

public class WinningResult {
    private final Map<Name, WinStatus> participantsWinStatus;

    public WinningResult(final Map<Name, WinStatus> participantsWinStatus) {
        this.participantsWinStatus = participantsWinStatus;
    }

    public Map<Name, WinStatus> getParticipantsResult() {
        return Collections.unmodifiableMap(participantsWinStatus);
    }
}
