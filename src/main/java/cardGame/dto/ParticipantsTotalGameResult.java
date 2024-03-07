package cardGame.dto;

import dealer.Dealer;
import java.util.LinkedHashMap;
import java.util.Map;
import player.Name;
import player.Player;
import player.Players;
import player.dto.SinglePlayerStatusDto;

public record ParticipantsTotalGameResult(Map<SinglePlayerStatusDto, Integer> totalGameResult) {

    public static ParticipantsTotalGameResult of(Players players, Dealer dealer) {
        Map<SinglePlayerStatusDto, Integer> totalGameResult = new LinkedHashMap<>();
        addDealerResult(totalGameResult, dealer);
        
        for (int i = 0; i < players.getSize(); i++) {
            Player player = players.getSinglePlayer(i);
            SinglePlayerStatusDto singlePlayerStatusDto = SinglePlayerStatusDto.from(player);
            totalGameResult.put(singlePlayerStatusDto, player.getMaxGameScore());
        }

        return new ParticipantsTotalGameResult(totalGameResult);
    }

    private static void addDealerResult(Map<SinglePlayerStatusDto, Integer> totalGameResult, Dealer dealer) {
        totalGameResult.put(new SinglePlayerStatusDto(new Name("딜러"), dealer.getCards()), dealer.getMaxGameScore());
    }
}
