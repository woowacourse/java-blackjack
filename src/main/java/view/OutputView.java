package view;

import domain.participant.BattleResult;
import dto.BattleResultResponse;
import dto.BlackJackResultResponse;
import dto.CardDeckStatusResponse;
import dto.ParticipantResponse;
import java.util.List;
import java.util.Map;

public class OutputView {


    public void printDrawTwoCardsMessage(ParticipantResponse response) {
        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n",
            response.dealerName(),
            String.join(", ", response.playerNames())
        );
    }

    public void printCardDeckStatus(CardDeckStatusResponse response) {
        Map<String, List<String>> cardDeckNamesOfParticipant = response.cardDeckNamesOfParticipant();

        for (Map.Entry<String, List<String>> entry : cardDeckNamesOfParticipant.entrySet()) {
            String participantName = entry.getKey();
            List<String> cardNames = entry.getValue();

            System.out.printf("%s 카드: %s%n",
                participantName,
                String.join(", ", cardNames)
            );
        }
        System.out.println();
    }

    public void printDrawSingleCardToDealerMessage(String nickname, int stayThreshold) {
        System.out.printf("%s는 %d이하라 한장의 카드를 더 받았습니다.%n",
            nickname,
            stayThreshold
        );
    }

    public void printBlackJackResult(List<BlackJackResultResponse> resultResponses) {
        System.out.println();
        for (BlackJackResultResponse response : resultResponses) {
            System.out.printf("%s카드: %s - 결과: %d%n",
                response.nickname(),
                String.join(", ", response.cardNames()),
                response.totalScore()
            );
        }
    }

    public void printBattleResult(List<BattleResultResponse> resultResponses) {
        System.out.println("\n## 최종 승패");
        for (BattleResultResponse response : resultResponses) {
            String nickname = response.nickname();
            Map<BattleResult, Integer> battleResultCount = response.battleResult();

            System.out.printf("%s: %s%n", nickname, parseBattleResult(battleResultCount));
        }
    }

    private String parseBattleResult(Map<BattleResult, Integer> battleResultCount) {
        StringBuilder result = new StringBuilder();
        for (BattleResult battleResult : BattleResult.values()) {
            if (!battleResultCount.containsKey(battleResult)) {
                continue;
            }

            int count = battleResultCount.get(battleResult);
            result.append(count).append(battleResult.getName()).append(" ");
        }

        return result.toString();
    }
}
