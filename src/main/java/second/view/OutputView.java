package second.view;

import first.domain.gamer.AllGamers;
import second.domain.BlackJackGame;
import second.domain.gamer.Dealer;
import second.domain.gamer.Gamer;
import second.domain.gamer.Player;
import second.domain.result.ResultType;
import second.domain.result.Results;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NAME_DELIMITER = ", ";

    public static void printInitialCards(List<Player> players, Dealer dealer) {
        System.out.printf(
                "딜러와 %s에게 2장의 카드를 나누었습니다. \n"
                , parsePlayersName(players)
                , parseDealerInitialState(dealer)
                , parseGamersState(players)
        );
    }

    private static String parsePlayersName(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(NAME_DELIMITER));
    }

    private static String parseDealerInitialState(Dealer dealer) {
        String dealerInitialCard = dealer.getHandCards()
                .getOneCard()
                .toString();

        return String.format("%s: %s \n", dealer.getName(), dealerInitialCard);
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
        return String.format("%s: %s", gamer.getName(), gamer.getHandCards().toString());
    }

    public static void printScore(Gamer gamer) {
        System.out.printf("%s - 결과: %s\n", parseGamerState(gamer), gamer.getScore().getValue());
    }

    public static void printResults(Results results) {
        System.out.printf(
                "## 최종 승패 \n%s \n%s"
                , parseDealerResultToString(results.getDealerResult())
                , parsePlayerResultsToString(results.getPlayerResults())
        );
    }

    private static String parseDealerResultToString(Map<ResultType, Integer> dealerWinLoses) {
        return String.format(
                "딜러: %s %s %s %s \n"
                , parseNullToZero(dealerWinLoses.get(ResultType.WIN))
                , ResultType.WIN.getName()
                , parseNullToZero(dealerWinLoses.get(ResultType.LOSE))
                , ResultType.LOSE.getName()
        );
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
        System.out.printf("%s: %s \n", gamer.getName(), gamerCardNames);
    }

    private OutputView() {
    }
}
