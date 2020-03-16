package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.result.Result;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String CARD = " 카드: ";
    private static final String RESULT = " - 결과 : ";
    private static final String DELIMITER = ", ";
    private static final String NEW_LINE = "\n";
    private static final String COLON = ": ";
    private static final String WINS = "승 ";
    private static final String DRAWS = "무 ";
    private static final String LOSES = "패";
    private static final String DEALER = "딜러";

    public static void printInitialCardDistribution(BlackjackGame game) {
        List<Player> players = game.getPlayers();
        System.out.println(players.stream()
                .map(User::getName)
                .collect(Collectors.joining(DELIMITER)) +
                "에게 2장의 카드를 나누었습니다.\n\n" +
                showInitialCardStatus(game.getDealer()) + "\n" +
                players.stream()
                        .map(OutputView::showInitialCardStatus)
                        .collect(Collectors.joining(NEW_LINE))
        );
    }

    public static void printUserStatus(User user) {
        System.out.println(showCurrentCardStatus(user));
    }

    public static void printBusted(String name) {
        System.out.println(name + "이(가) 버스트 되었습니다");
    }

    public static void printDealerHitMoreCard() {
        System.out.println("딜러는 " + Dealer.THRESHOLD + "이하라 한 장의 카드를 더 받았습니다\n");
    }

    public static void printFinalCardScore(BlackjackGame game) {
        System.out.println("\n## 결과 : \n" + parseFinalScoreAnnouncement(game) + "\n");
    }

    public static void printFinalResult(Map<Player, Result> totalResult) {
        System.out.println("## 최종 승패");
        System.out.println(parseDealerFinalResult(calculatePlayerResultCount(totalResult)));
        System.out.println(parseAllPlayerResults(totalResult));
    }


    private static String showInitialCardStatus(User user) {
        return parsePlayerStatus(user.getName(), user.getInitialCards());
    }

    public static String showCurrentCardStatus(User user) {
        return parsePlayerStatus(user.getName(), user.getCards());
    }

    private static String parsePlayerStatus(String name, List<Card> cards) {
        return name + CARD + String.join(DELIMITER, parseCardsName(cards));
    }

    private static List<String> parseCardsName(List<Card> cards) {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.toList());
    }

    private static String showFinalResultPerUsers(User user) {
        return showCurrentCardStatus(user) + RESULT + user.getTotalScore();
    }

    private static Map<Result, Integer> calculatePlayerResultCount(Map<Player, Result> totalResult) {
        Map<Result, Integer> playerResult = new HashMap<>();
        playerResult.put(Result.WIN, 0);
        playerResult.put(Result.DRAW, 0);
        playerResult.put(Result.LOSE, 0);

        for (Result r : totalResult.values()) {
            playerResult.put(r, playerResult.get(r) + 1);
        }
        return playerResult;
    }

    private static String parseFinalScoreAnnouncement(BlackjackGame game) {
        return showFinalResultPerUsers(game.getDealer()) + "\n" +
                game.getPlayers().stream()
                        .map(OutputView::showFinalResultPerUsers)
                        .collect(Collectors.joining(NEW_LINE));
    }

    private static String parseAllPlayerResults(Map<Player, Result> totalResult) {
        StringBuilder sb = new StringBuilder();
        totalResult.forEach((player, result) ->
                sb.append(player.getName()).append(COLON).append(result.getName()).append(NEW_LINE));
        return sb.toString();
    }

    private static String parseDealerFinalResult(Map<Result, Integer> playerResult) {
        return DEALER + COLON +
                playerResult.get(Result.LOSE) + WINS +
                playerResult.get(Result.DRAW) + DRAWS +
                playerResult.get(Result.WIN) + LOSES +
                NEW_LINE;
    }
}
