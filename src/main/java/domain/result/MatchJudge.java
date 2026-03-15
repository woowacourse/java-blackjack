package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;

public class MatchJudge {
    private final Dealer dealer;
    private final Player player;

    public MatchJudge(Dealer dealer, Player player) {
        this.dealer = dealer;
        this.player = player;
    }

    public MatchCase judge() {
        for (MatchCase matchCase : MatchCase.values()) {
            if (matchCase.isMatch(dealer, player)) {
                return matchCase;
            }
        }
        return MatchCase.WIN;
    }
}
