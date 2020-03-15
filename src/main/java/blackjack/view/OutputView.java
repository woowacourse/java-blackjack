package blackjack.view;

import blackjack.domain.result.GameResult;
import blackjack.domain.result.Outcome;
import blackjack.domain.result.Score;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participants;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import java.util.Map;

public class OutputView {

    public static void printCardDistribution(Participants participants) {
        System.out.printf("\n%s와 %s에게 2장의 카드를 나누었습니다.\n"
            , participants.getDealerName()
            , String.join(", ", participants.getPlayerNames()));
    }

    public static void printUsersCards(Participants participants) {
        Dealer dealer = participants.getDealer();
        System.out.printf("%s: %s\n", dealer.getName(), dealer.getFirstCardInfo());
        for (Player player : participants.getPlayers()) {
            printPlayerCards(player);
        }
        System.out.println();
    }

    public static void printPlayerCards(Player player) {
        System.out.println(getUserCards(player));
    }

    private static String getUserCards(User user) {
        return String.format("%s: %s", user.getName()
            , String.join(", ", user.getCardsInfos()));
    }

    public static void printDealerOneMoreCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printUsersCardsAndScore(Participants participants) {
        System.out.println();
        for (User user : participants.getParticipants()) {
            printUserCardsAndScore(user);
        }
    }

    public static void printUserCardsAndScore(User user) {
        Score userScore = user.getScore();
        System.out.printf("%s - 결과: %s\n", getUserCards(user), userScore.getScore());
    }

    public static void printFinalResult(Participants participants, GameResult gameResult) {
        System.out.println("\n## 최종 승패");
        printDealerFinalResult(participants.getDealer(), gameResult.getDealerResults());
        printPlayerFinalResult(gameResult.getPlayersResult());
    }

    private static void printPlayerFinalResult(Map<Player, Outcome> playersResult) {
        for (Player player : playersResult.keySet()) {
            Outcome outcome = playersResult.get(player);
            System.out.printf("%s: %s\n", player.getName(), outcome.getName());
        }
    }

    private static void printDealerFinalResult(Dealer dealer, Map<Outcome, Integer> gameResult) {
        System.out.printf("%s: ", dealer.getName());
        for (Outcome outcome : gameResult.keySet()) {
            printDealerFinalOutcome(gameResult, outcome);
        }
        System.out.println();
    }

    private static void printDealerFinalOutcome(Map<Outcome, Integer> gameResult, Outcome outcome) {
        if (gameResult.get(outcome) != 0) {
            System.out.print(gameResult.get(outcome) + outcome.getConverseName() + " ");
        }
    }

    public static void printExceptionMessage(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }
}
