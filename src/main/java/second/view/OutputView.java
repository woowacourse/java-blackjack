package second.view;

import second.domain.player.AllGamers;
import second.domain.player.Dealer;
import second.domain.player.Gamer;
import second.domain.player.Player;
import second.domain.result.ResultType;
import second.domain.result.Results;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class OutputView {
    public static void printEmptyLine() {
        System.out.println();
    }

    public static void printInitialCards(AllGamers allGamers) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("딜러와 ");
        stringBuilder.append(parsePlayersName(allGamers.getPlayers()));
        stringBuilder.append("에게 2장의 카드를 나누었습니다.\n");
        stringBuilder.append(parseDealerInitialState(allGamers.getDealer()));
        stringBuilder.append(parseGamersState(allGamers.getPlayers()));

        System.out.println(stringBuilder.toString());
    }

    private static String parsePlayersName(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    private static String parseDealerInitialState(Dealer dealer) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(dealer.getName());
        stringBuilder.append(": ");

        String dealerInitialCard = dealer.getHandCards()
                .getOneCard()
                .toString();
        stringBuilder.append(dealerInitialCard);
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

    public static void printGamerState(Gamer gamer) {
        System.out.println(parseGamerState(gamer));
    }

    private static String parseGamersState(List<Player> players) {
        return players
                .stream()
                .map(OutputView::parseGamerState)
                .collect(Collectors.joining("\n"));
    }

    private static String parseGamerState(Gamer gamer) {
        return gamer.getName() + ": " + gamer.getHandCards().toString();
    }

    public static void printCanNotDrawMessage(Player player) {
        System.out.println(player.getName() + "는 더이상 뽑을 수 없습니다.");
    }

    public static void printDealerCanDrawMore() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResults(Results results) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("##최종승패\n");
        stringBuilder.append(parseDealerResultToString(results.getDealerResult()));
        stringBuilder.append(parsePlayerResultsToString(results.getPlayerResults()));

        System.out.println(stringBuilder.toString());
    }

    public static void printScore(Gamer gamer) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(parseGamerState(gamer));
        stringBuilder.append(" - 결과: ");
        stringBuilder.append(gamer.getScore().getValue());
        stringBuilder.append("\n");

        System.out.println(stringBuilder.toString());
    }

    private static String parseDealerResultToString(Map<ResultType, Integer> dealerWinLoses) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("딜러: ");
        stringBuilder.append(parseNullToZero(dealerWinLoses.get(ResultType.WIN)));
        stringBuilder.append(ResultType.WIN.getName());
        stringBuilder.append(parseNullToZero(dealerWinLoses.get(ResultType.LOSE)));
        stringBuilder.append(ResultType.LOSE.getName());
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

    private static int parseNullToZero(Integer input) {
        if (input == null) {
            return 0;
        }

        return input;
    }

    private static String parsePlayerResultsToString(Map<ResultType, List<Player>> playerResults) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Player> winners = playerResults.get(ResultType.WIN);
        List<Player> losers = playerResults.get(ResultType.LOSE);

        appendPlayerResult(stringBuilder, winners, ": 승\n");

        appendPlayerResult(stringBuilder, losers, ": 패\n");

        return stringBuilder.toString();
    }

    private static void appendPlayerResult(StringBuilder stringBuilder, List<Player> players, String resultTail) {
        if (Objects.nonNull(players)) {
            for (Player winner : players) {
                stringBuilder.append(winner.getName());
                stringBuilder.append(resultTail);
            }
        }
    }
}
