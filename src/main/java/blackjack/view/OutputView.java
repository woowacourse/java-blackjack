package blackjack.view;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    public static final String BUST_MESSAGE = "버스트 되었습니다!!!";

    public static void printInitialInfo(Player dealer, Players players) {
        printInitialInfoHead(dealer, players);

        printInitialDealerCard(dealer);
        printAllPlayerCards(players);
    }

    private static void printInitialInfoHead(Player dealer, Players players) {
        String playerNames = players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));

        System.out.printf("%s와 %s에게 %d장을 나누었습니다." + NEW_LINE,
                dealer.getName(), playerNames, dealer.countCards());
    }

    private static void printInitialDealerCard(Player dealer) {
        String dealerCards = dealer.getCards().stream()
                .limit(1)
                .map(Card::getName)
                .collect(Collectors.joining(", "));

        System.out.printf("%s : %s" + NEW_LINE, dealer.getName(), dealerCards);
    }

    private static void printAllPlayerCards(Players players) {
        for (Player player : players.getPlayers()) {
            printUserCard(player);
        }
    }

    public static void printUserCard(Player player) {
        String userCards = createUserCardInfo(player);
        System.out.printf("%s : %s" + NEW_LINE, player.getName(), userCards);

        printIfBust(player);
    }

    private static String createUserCardInfo(Player player) {
        return player.getCards().stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

	public static void printDealerTurn(Dealer dealer) {
        System.out.printf("딜러는 %s미만이라 한장의 카드를 더 받았습니다." + NEW_LINE,
                Dealer.MINIMUM_NUMBER_TO_STAY);

        printIfBust(dealer);
    }

    private static void printIfBust(Player player) {
        if (player.isBust()) {
            System.out.println(BUST_MESSAGE);
        }
    }

    public static void printFinalInfo(Player dealer, Players players) {
        List<Player> users = new ArrayList<>();
        users.add(dealer);
        users.addAll(players.getPlayers());

        for (Player player : users) {
            String userCards = createUserCardInfo(player);
            String score = createResultScore(player);

            System.out.printf("%s : %s - 결과: %s" + NEW_LINE,
                    player.getName(), userCards, score);
        }
    }

    private static String createResultScore(Player player) {
        if (player.isBust()) {
            return "bust";
        }
        return String.valueOf(player.calculateScore().getScore());
    }

    public static void printResult(Result result) {
        System.out.println("## 최종 승패");
        System.out.println("딜러" + " : " + result.getDealerResult());
        for (Player player : result.getPlayerResults().keySet()) {
            String playerResult = createPlayerResult(player, result);
            System.out.println(playerResult);
        }
    }

    private static String createPlayerResult(Player player, Result result) {
        String resultWord = boolToResultWord(result.isWinner(player));
        return String.format("%s : %s", player.getName(), resultWord);
    }

    private static String boolToResultWord(boolean bool) {
        if (bool) {
            return "승";
        }
        return "패";
    }
}
