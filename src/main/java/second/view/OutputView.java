package second.view;

import second.domain.BlackJackGame;
import second.domain.gamer.Dealer;
import second.domain.gamer.Gamer;
import second.domain.gamer.Player;
import second.domain.result.Result;
import second.domain.result.Results;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NAME_DELIMITER = ", ";

    public static void printInitialCards(List<Player> players, Dealer dealer) {
        System.out.printf(
                "딜러와 %s에게 2장의 카드를 나누었습니다. \n%s%s"
                , parsePlayersName(players)
                , parseDealerInitialState(dealer)
                , parseGamersState(players)
        );
        printEmptyLine();
        printEmptyLine();
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
        printEmptyLine();
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

    public static void printProfit(Results results) {
        printEmptyLine();
        System.out.printf(
                "## 최종 수익 \n%s \n"
                , results.getDealerResult().toString());

        List<Result> playerResults = results.getPlayerResults();
        for (Result result: playerResults) {
            System.out.println(result.toString());
        }
    }

    public static void printGamerCards(final Gamer gamer) {
        System.out.println(parseGamerState(gamer) + "\n");
    }

    private static void printEmptyLine() {
        System.out.println();
    }

    private OutputView() {
    }
}

