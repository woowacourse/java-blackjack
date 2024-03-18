package util;

import controller.dto.BettingInfo;
import controller.dto.BettingInfos;
import java.util.List;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.dto.DealerFaceUpResult;
import model.participant.dto.PlayerFaceUpResult;

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

    public static BettingInfos toPlayerBettingResult(List<Player> players) {
        return BettingInfos.from(players.stream()
                .map(player -> new BettingInfo(player.getName(), player.calculateEarning()))
                .toList());
    }
}
