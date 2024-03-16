package util;

import model.participant.Dealer;
import model.participant.Player;
import model.participant.dto.DealerFaceUpResult;
import model.participant.dto.PlayerFaceUpResult;
import model.participant.dto.PlayerMatchResult;

public class ResultMapper {
    private ResultMapper() {
        throw new IllegalStateException("Static Util Class");
    }

    public static PlayerFaceUpResult toPlayerFaceUpResult(Player player) {
        return new PlayerFaceUpResult(player.getName(), player.getCards(), player.getHand());
    }

    public static DealerFaceUpResult toDealerFaceUpResult(Dealer dealer) {
        return new DealerFaceUpResult(dealer.getCards(), dealer.getHand());
    }

    public static PlayerMatchResult toPlayerMatchResult(Player player, Dealer dealer) {
        int dealerHand = dealer.getHand();
        return new PlayerMatchResult(player.getName(), player.determineMatchResult(dealerHand));
    }
}
