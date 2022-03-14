package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.Result;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printInitCardHandStatus(Dealer dealer, Players players) {
        List<Player> readPlayers = players.getPlayers();

        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n",
                dealer.getName().getValue(),
                readPlayers.stream()
                        .map(player -> player.getName().getValue())
                        .collect(Collectors.joining(", ")));

        printInitDealerCardHandStatus(dealer);
        for (Player player : readPlayers) {
            printCardHandStatus(player);
        }
        printEmptyLine();
    }

    private static void printInitDealerCardHandStatus(Dealer dealer) {
        System.out.println(dealer.getName().getValue() + " 카드: " + dealer.getCards().getCardHand().get(0));
    }

    public static void printCardHandStatus(Participant participant) {
        System.out.println(showCardHandStatus(participant));
    }

    public static String showCardHandStatus(Participant participant) {
        return String.format("%s 카드: %s",
                participant.getName().getValue(),
                participant.getCards().getCardHand().stream()
                        .map(Card::toString)
                        .collect(Collectors.joining(", ")));
    }

    public static void printDealerStatus() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalStatus(Dealer dealer, Players players) {
        printEmptyLine();
        System.out.println(showCardHandStatus(dealer) + " 결과 - " + dealer.calculateBestScore());
        for (Player readPlayer : players.getPlayers()) {
            System.out.println(showCardHandStatus(readPlayer) + " 결과 - " + readPlayer.calculateBestScore());
        }
    }

    public static void printFinalResult(Dealer dealer, Players players) {
        printEmptyLine();
        System.out.println("## 최종 승패");
        Map<Player, Result> resultCounter = players.judgeResult(dealer);
        System.out.println(dealer.getName().getValue() + ": " +
                resultCounter.values().stream()
                        .filter(result -> result == Result.WIN).count() + Result.WIN.getResult() + " " +
                resultCounter.values().stream()
                        .filter(result -> result == Result.DRAW).count() + Result.DRAW.getResult() + " " +
                resultCounter.values().stream()
                        .filter(result -> result == Result.LOSE).count() + Result.LOSE.getResult());
        resultCounter.forEach(
                (player, result) -> System.out.println(player.getName().getValue() + ": " + result.getResult()));
    }

    public static void printEmptyLine() {
        System.out.println();
    }
}
