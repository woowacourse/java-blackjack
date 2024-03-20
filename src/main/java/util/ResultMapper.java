package util;

import controller.dto.BettingInfo;
import controller.dto.BettingInfos;
import controller.dto.DealerFaceUpResult;
import controller.dto.DealerFaceUpResultInfo;
import controller.dto.PlayerFaceUpResult;
import controller.dto.PlayerFaceUpResultInfo;
import java.util.List;
import model.participant.Dealer;
import model.participant.Player;

public class ResultMapper {
    private ResultMapper() {
        throw new AssertionError("정적 유틸리티 클래스는 생성자를 사용할 수 없습니다.");
    }

    public static PlayerFaceUpResult toPlayerFaceUpResult(Player player) {
        return new PlayerFaceUpResult(player.getName(), player.getCards(), player.getHand());
    }

    public static DealerFaceUpResult toDealerFaceUpResult(Dealer dealer) {
        return new DealerFaceUpResult(dealer.getCards(), dealer.getHand());
    }

    public static DealerFaceUpResultInfo fromDealerFaceUpResult(DealerFaceUpResult dealerFaceUpResult) {
        return new DealerFaceUpResultInfo(dealerFaceUpResult.getCardsAsStrings(), dealerFaceUpResult.hand());
    }

    public static List<PlayerFaceUpResultInfo> fromPlayerFaceUpResult(List<PlayerFaceUpResult> playerFaceUpResults) {
        return playerFaceUpResults.stream()
                .map(result -> new PlayerFaceUpResultInfo(result.getPartipantNameAsString(), result.getCardsAsStrings(),
                        result.hand()))
                .toList();
    }

    public static PlayerFaceUpResultInfo fromPlayerFaceUpResult(PlayerFaceUpResult result) {
        return new PlayerFaceUpResultInfo(result.getPartipantNameAsString(), result.getCardsAsStrings(), result.hand());
    }

    public static BettingInfos toPlayerBettingResult(List<Player> players) {
        return BettingInfos.from(players.stream()
                .map(player -> new BettingInfo(player.getName(), player.calculateEarning()))
                .toList());
    }
}
