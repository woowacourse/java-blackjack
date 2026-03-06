package dto;

import domain.player.Participants;
import domain.player.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record InitialCardInfoResponseDto(Map<String, List<String>> initialParticipantsInfo) {

    public InitialCardInfoResponseDto(Participants participants) {
        this(createInitialParticipantsInfo(participants));
    }

    private static Map<String, List<String>> createInitialParticipantsInfo(Participants participants) {
        Map<String, List<String>> initialParticipantsInfo = new HashMap<>();

        // 딜러 정보
        initialParticipantsInfo.put(
                participants.getDealer().getName(),
                List.of(participants.getDealer().getFirstCardStatus())
        );

        // 플레이어 정보
        List<Player> gamblersInfo = participants.getGamblers().getGamblersInfo();

        initialParticipantsInfo.putAll(
                gamblersInfo.stream()
                        .collect(Collectors.toMap(Player::getName, Player::getCardStatus))
        );

        return initialParticipantsInfo;
    }
}
