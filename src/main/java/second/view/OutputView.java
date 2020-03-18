package second.view;

import second.domain.BlackJackGame;
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

    private static final String NAME_DELIMITER = ", ";

    public static void printInitialCards(List<Player> players, Dealer dealer) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("딜러와 ");
        stringBuilder.append(parsePlayersName(players));
        stringBuilder.append("에게 2장의 카드를 나누었습니다.\n");
        stringBuilder.append(parseDealerInitialState(dealer));
        stringBuilder.append(parseGamersState(players));

        System.out.println(stringBuilder.toString());
    }

    private static String parsePlayersName(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(NAME_DELIMITER));
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

    private static String parseGamersState(List<Player> players) {
        return players
                .stream()
                .map(OutputView::parseGamerState)
                .collect(Collectors.joining("\n"));
    }

    public static void printScore(BlackJackGame blackJackGame) {
        printScore(blackJackGame.getDealer());

        blackJackGame.getPlayers()
                .forEach(OutputView::printScore);
    }

    private static String parseGamerState(Gamer gamer) {
        return gamer.getName() + ": " + gamer.getHandCards().toString();
    }

    public static void printScore(Gamer gamer) {
        System.out.printf("%s - 결과: %s\n", parseGamerState(gamer), gamer.getScore().getValue());
    }

    public static void printResults(Results results) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("##최종승패\n");
        stringBuilder.append(parseDealerResultToString(results.getDealerResult()));
        stringBuilder.append(parsePlayerResultsToString(results.getPlayerResults()));

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

    public static void printGamerCards(final Gamer gamer) {
        final String gamerCardNames = parseGamerState(gamer);
        System.out.println(String.format("%s: %s", gamer.getName(), gamerCardNames));
    }

    private OutputView() { }
}
