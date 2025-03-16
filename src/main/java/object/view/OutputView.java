package object.view;

import java.util.List;
import java.util.Map;
import object.game.GameResult;
import object.participant.Participant;
import response.BattleResultResponse;
import response.BlackJackResultResponse;
import response.CardDeckStatusResponse;
import response.ParticipantResponse;

public class OutputView {
    public void printDrawTwoCardsMessage(ParticipantResponse response) {
        GameEffect.playSoundEffect("cardShuffle.wav");
        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n",
                response.dealerName(),
                String.join(", ", response.playerNames())
        );
    }

    public void printCardDeckStatus(CardDeckStatusResponse response) {
        GameEffect.playSoundEffect("cardDraw.wav");
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
        GameEffect.delay(1000);
        GameEffect.playSoundEffect("cardDraw.wav");
        System.out.printf("%s는 %d이하라 한장의 카드를 더 받았습니다.%n",
                nickname,
                stayThreshold
        );
        GameEffect.delay(1000);
    }

    public void printBlackJackResult(List<BlackJackResultResponse> resultResponses) {
        System.out.println();
        for (BlackJackResultResponse response : resultResponses) {
            GameEffect.delay(500);
            GameEffect.playSoundEffect(response);
            System.out.printf("%s 카드: %s - 결과: %d%n",
                    response.nickname(),
                    String.join(", ", response.cardNames()),
                    response.totalScore()
            );
            GameEffect.delay(500);
        }
    }

    public void printBattleResult(List<BattleResultResponse> resultResponses) {
        GameEffect.delay(500);
        System.out.println("\n## 게임 결과");
        for (BattleResultResponse response : resultResponses) {
            String nickname = response.nickname();
            Map<GameResult, Integer> battleResultCount = response.battleResult();

            System.out.printf("%s: %s%n", nickname, parseBattleResult(battleResultCount));
        }
    }

    public void printFinalProfit(List<Participant> participants) {
        GameEffect.delay(1000);
        GameEffect.playSoundEffect("betResultRevealed.wav");
        System.out.println("\n## 최종 수익");
        for (Participant participant : participants) {
            int profit = participant.getProfit();
            String nickname = participant.getNickname();
            System.out.printf("%s: %,d%n", nickname, profit);
        }
        GameEffect.delay(4000);
    }

    private String parseBattleResult(Map<GameResult, Integer> battleResultCount) {
        StringBuilder result = new StringBuilder();
        for (GameResult gameResult : GameResult.values()) {
            if (!battleResultCount.containsKey(gameResult)) {
                continue;
            }

            int count = battleResultCount.get(gameResult);
            result.append(count).append(gameResult.getName()).append(" ");
        }

        return result.toString();
    }
}
