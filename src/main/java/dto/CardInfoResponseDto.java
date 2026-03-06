package dto;

import domain.player.Participants;
import domain.player.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record CardInfoResponseDto(Map<String, List<String>> participantsInfo, List<Integer> score) {

    public CardInfoResponseDto(Participants participants) {
        this(createParticipantsInfo(participants), createParticipantsScoreInfo(participants));
    }

    private static Map<String, List<String>> createParticipantsInfo(Participants participants) {
        Map<String, List<String>> participantsInfo = new HashMap<>();

        // 딜러 정보
        participantsInfo.put(
                participants.getDealer().getName(),
                participants.getDealer().getCardStatus()
        );

        // 플레이어 정보
        List<Player> gamblersInfo = participants.getGamblers().getGamblersInfo();

        participantsInfo.putAll(
                gamblersInfo.stream()
                        .collect(Collectors.toMap(Player::getName, Player::getCardStatus))
        );

        return participantsInfo;
    }

    private static List<Integer> createParticipantsScoreInfo(Participants participants) {
        // 딜러의 점수 가져오기
        List<Integer> score = new ArrayList<>();
        score.add(participants.getDealer().getTotalValue());

        // 플레이어의 점수
        score.addAll(
                participants.getGamblers().getGamblersInfo().stream()
                .map(Player::getTotalValue)
                .toList());

        return score;
    }
}
