package view;

import domain.participant.GameResult;
import domain.participant.Participant;
import domain.participant.ParticipantsResult;
import java.util.Map;

public class OutputViewOfResult extends OutputView {

    private static final String RESULT_MESSAGE = "## 최종 결과";

    @Override
    public void printGameResult(ParticipantsResult participantsResult) {
        System.out.println();
        System.out.println(RESULT_MESSAGE);
        Map<GameResult, Integer> dealerResult = participantsResult.getDealerResult();
        Map<Participant, GameResult> playersResult = participantsResult.getPlayersResult();
        printDealerResult(dealerResult);
        printPlayersResult(playersResult);
    }

    private void printDealerResult(Map<GameResult, Integer> dealerResult) {
        System.out.print("딜러: ");
        System.out.println(dealerFormatter(dealerResult));
    }

    private void printPlayersResult(Map<Participant, GameResult> playersResult) {
        for (Participant player : playersResult.keySet()) {
            System.out.println(player.getName() + ": " + playersResult.get(player).getKoreanName());
        }
    }

    private String dealerFormatter(Map<GameResult, Integer> dealerResult) {
        StringBuilder stringBuilder = new StringBuilder();
        for (GameResult gameResult : GameResult.values()) {
            String koreanName = gameResult.getKoreanName();
            if (dealerResult.get(gameResult) == null) {
                continue;
            }
            stringBuilder.append(dealerResult.get(gameResult));
            stringBuilder.append(koreanName + " ");
        }
        return stringBuilder.toString();
    }
}
