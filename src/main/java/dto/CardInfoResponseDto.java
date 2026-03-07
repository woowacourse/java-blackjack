package dto;

import domain.game.Game;
import domain.player.Gambler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record CardInfoResponseDto(Map<String, List<String>> participantsInfo, List<Integer> score) {
//
//    public CardInfoResponseDto(Game game) {
//        this(createParticipantsInfo(game), createParticipantsScoreInfo(game));
//    }
//
//    private static Map<String, List<String>> createParticipantsInfo(Game game) {
//        Map<String, List<String>> participantsInfo = new HashMap<>();
//
//        // 딜러 정보
//        participantsInfo.put(
//                game.getDealer().getName(),
//                game.getDealer().getHandInfo()
//        );
//
//        // 플레이어 정보
//        List<Gambler> gamblersInfo = game.getGamblers().getGamblersInfo();
//
//        participantsInfo.putAll(
//                gamblersInfo.stream()
//                        .collect(Collectors.toMap(Gambler::getName, Gambler::getHandInfo))
//        );
//
//        return participantsInfo;
//    }
//
//    private static List<Integer> createParticipantsScoreInfo(Game game) {
//        // 딜러의 점수 가져오기
//        List<Integer> score = new ArrayList<>();
//        score.add(game.getDealer().getTotalScore());
//
//        // 플레이어의 점수
//        score.addAll(
//                game.getGamblers().getGamblersInfo().stream()
//                .map(Gambler::getTotalScore)
//                .toList());
//
//        return score;
//    }
}
