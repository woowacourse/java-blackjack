package cardGame.dto;

import dealer.Dealer;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import player.Name;
import player.Player;
import player.Players;
import player.dto.SinglePlayerStatusDto;

public record ParticipantsTotalGameResultDto(Map<SinglePlayerStatusDto, Integer> totalGameResult) {

    public static ParticipantsTotalGameResultDto of(Players players, Dealer dealer) {
        Map<SinglePlayerStatusDto, Integer> totalGameResult = new LinkedHashMap<>();

        addDealerResult(totalGameResult, dealer);
        addPlayerResult(players, totalGameResult);

        return new ParticipantsTotalGameResultDto(Collections.unmodifiableMap(totalGameResult));
    }

    private static void addDealerResult(Map<SinglePlayerStatusDto, Integer> totalGameResult, Dealer dealer) {
        SinglePlayerStatusDto singlePlayerCardStatus = new SinglePlayerStatusDto(new Name("딜러"), dealer.getCards());

        totalGameResult.put(singlePlayerCardStatus, dealer.getMaxGameScore());
    }

    private static void addPlayerResult(Players players, Map<SinglePlayerStatusDto, Integer> totalGameResult) {
        for (Player player : players.getPlayers()) {

            SinglePlayerStatusDto singlePlayerStatusDto = SinglePlayerStatusDto.from(player);
            totalGameResult.put(singlePlayerStatusDto, player.getMaxGameScore());
        }
    }
}
