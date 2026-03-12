package blackjack.view;

import blackjack.model.card.Card;
import blackjack.model.gameresult.ProfitResult;
import blackjack.model.user.Dealer;
import blackjack.model.user.Player;
import blackjack.model.user.User;
import blackjack.model.user.Users;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final int BLACKJACK_SCORE = 21;

    public static void printInitCards(Users users) {
        List<Player> players = users.getPlayers();
        Dealer dealer = users.getDealer();

        List<String> names = players.stream()
                .map(Player::getName)
                .toList();
        StringBuilder sb = new StringBuilder();
        sb.append("딜러와 ");
        sb.append(String.join(", ", names));
        sb.append("에게 2장을 나누었습니다.");
        System.out.println(sb);

        printDealerCard(dealer);
        for (Player player : players) {
            printPlayerCards(player);
        }
        System.out.println();
    }

    private static void printDealerCard(Dealer dealer) {
        Card firstCard = dealer.cards().getFirst();
        System.out.println("딜러카드: " + getCardFormat(firstCard));
    }

    public static void printPlayerCards(Player player) {
        String cardsFormat = getCardsFormat(player);
        System.out.println(player.getName() + "카드: " + cardsFormat);
    }

    public static void printCantHit() {
        System.out.println("카드의 합계가 " + BLACKJACK_SCORE + " 이상입니다. 더이상 카드를 받을 수 없습니다.");
    }

    public static void printDealerHit() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public static void printHandStatus(Users users) {
        Dealer dealer = users.getDealer();
        List<Player> players = users.getPlayers();
        StringBuilder sb = new StringBuilder();

        createHandStatusFormat(sb, dealer);
        for (Player player : players) {
            createHandStatusFormat(sb, player);
        }
        System.out.println(sb);
    }

    public static void printGameResult(ProfitResult profitResult, Users users) {
        System.out.println();
        System.out.println("## 최종 수익");

        Dealer dealer = users.getDealer();
        System.out.println(dealer.getName() + ": " + profitResult.dealerProfit());

        Map<Player, Integer> playersProfit = profitResult.playersProfit();
        for (Player player : users.getPlayers()) {
            System.out.print(player.getName() + ": ");
            System.out.println(playersProfit.get(player));
        }
    }

    public static void printError(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

    private static void createHandStatusFormat(StringBuilder sb, User user) {
        sb.append(user.getName()).append(" 카드: ");
        sb.append(getCardsFormat(user));
        sb.append(" - 결과: ").append(user.totalScore()).append("\n");
    }

    private static String getCardsFormat(User user) {
        return user.cards().stream()
                .map(OutputView::getCardFormat)
                .collect(Collectors.joining(", "));
    }

    private static String getCardFormat(Card card) {
        return card.getRank().getFormat() + card.getSuit().getFormat();
    }
}
