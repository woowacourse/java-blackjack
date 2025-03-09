package blackjack.model.game;

import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;

public enum ParticipantResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    private final String detail;

    ParticipantResult(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }
}
