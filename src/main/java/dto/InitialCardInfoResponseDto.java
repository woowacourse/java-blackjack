package dto;

import domain.game.Game;
import domain.player.Gambler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record InitialCardInfoResponseDto(Map<String, List<String>> initialParticipantsInfo) {

    public InitialCardInfoResponseDto(Game game) {
        this(createInitialParticipantsInfo(game));
    }

    private static Map<String, List<String>> createInitialParticipantsInfo(Game game) {
        Map<String, List<String>> initialParticipantsInfo = new HashMap<>();

        // 딜러 정보
        initialParticipantsInfo.put(
                game.getDealer().getName(),
                List.of(game.getDealer().getFirstHand())
        );

        // 플레이어 정보
        List<Gambler> gamblersInfo = game.getGamblers().getGamblersInfo();

        initialParticipantsInfo.putAll(
                gamblersInfo.stream()
                        .collect(Collectors.toMap(Gambler::getName, Gambler::getHand))
        );

        return initialParticipantsInfo;
    }
}
