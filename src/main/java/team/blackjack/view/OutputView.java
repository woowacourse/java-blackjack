package team.blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import team.blackjack.service.dto.DrawResult;
import team.blackjack.service.dto.GameResult;
import team.blackjack.service.dto.GameResult.DealerResult;
import team.blackjack.service.dto.GameResult.PlayerResult;
import team.blackjack.service.dto.ScoreResult;
import team.blackjack.domain.Result;

public class OutputView {

    public static void println(String message) {
        System.out.println(message);
    }

    public static void printPlayerNameRequest() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printDrawResult(DrawResult result) {
        String playerNames = String.join(", ", result.playerNames());
        println("딜러와 %s에게 2장을 나누었습니다.".formatted(playerNames));

        println("딜러카드: %s".formatted(result.dealerCard()));
        for (Entry<String, List<String>> entry : result.playerCards().entrySet()) {
            printPlayerCards(entry.getKey(), entry.getValue());
        }
    }

    public static void printAskDrawCard(String playerName) {
        println("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)".formatted(playerName));
    }

    public static void printPlayerCards(String playerName, List<String> cardNames) {
        println("%s카드: %s".formatted(playerName, String.join(", ", cardNames)));
    }

    public static void printDealerHitMessage() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printParticipantScoreResult(ScoreResult scoreResult) {
        println("");
        println("딜러의 최종 카드: %s - 결과: %d".formatted(String.join(", ", scoreResult.dealerCard()), scoreResult.dealerScore()));
        for (String playerName : scoreResult.playerNames()) {
            println("%s의 카드: %s - 결과: %d".formatted(playerName,
                    String.join(", ", scoreResult.playerCards().get(playerName)),
                    scoreResult.playerScores().get(playerName)));
        }
    }

     public static void printGameResult(GameResult result) {
         final DealerResult dealerResult = result.dealerResult();
         final Map<String, PlayerResult> playeredResultMap = result.playerResultMap();

         println("");
         println("## 최종 승패:");
         println("딜러: %d승 %d패 %d무".formatted(
                 dealerResult.countBy(Result.WIN),
                 dealerResult.countBy(Result.LOSE),
                 dealerResult.countBy(Result.DRAW))
         );

         playeredResultMap.entrySet().stream()
                 .map(entry -> "%s: %s".formatted(entry.getKey(), entry.getValue().result().getName()))
                 .forEach(OutputView::println);

    }

    public static void printBustMessage() {
        println("버스트 되었습니다. 더 이상 카드를 받을 수 없습니다.");
    }

    public static void printWrongInputMessage() {
        println("잘못된 입력입니다. y, n 중 하나를 입력해주세요.");
    }
}
