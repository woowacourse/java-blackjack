package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.TotalResult;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String CARD = " 카드: ";
    private static final String RESULT = " - 결과 : ";
    private static final String DELIMITER = ", ";
    private static final String NEW_LINE = "\n";
    private static final String COLON = ": ";
    private static final String DEALER = "딜러";

    public static void printInitialCardDistribution(BlackjackGame game) {
        List<Player> players = game.getPlayers();
        System.out.println(players.stream()
                .map(User::getName)
                .collect(Collectors.joining(DELIMITER)) +
                "에게 " + BlackjackGame.INITIAL_CARDS + "장의 카드를 나누었습니다.\n\n" +
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

    public static void printFinalResult(TotalResult totalResult, Money dealerProfit) {
        System.out.println("## 최종 수익");
        System.out.println(parseDealerFinalResult(dealerProfit));
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

    private static String showFinalResultPerUser(User user) {
        return showCurrentCardStatus(user) + RESULT + parseFinalScorePerUser(user.getTotalScore());
    }

    private static String parseFinalScorePerUser(int totalScore) {
        if(totalScore == 0) {
            return "버스트";
        }
        return Integer.toString(totalScore);
    }

    private static String parseFinalScoreAnnouncement(BlackjackGame game) {
        return showFinalResultPerUser(game.getDealer()) + "\n" +
                game.getPlayers().stream()
                        .map(OutputView::showFinalResultPerUser)
                        .collect(Collectors.joining(NEW_LINE));
    }

    private static String parseAllPlayerResults(TotalResult totalResult) {
        StringBuilder sb = new StringBuilder();
        totalResult.getResult().forEach((player, result) ->
                sb.append(player.getName()).append(COLON).append(player.getProfitByResult(result)).append(NEW_LINE));
        return sb.toString();
    }

    private static String parseDealerFinalResult(Money dealerProfit) {
        return DEALER + COLON + dealerProfit;
    }
}
