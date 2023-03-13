package ui;

import domain.PlayerRevenues;
import domain.user.AbstractUser;
import domain.user.Dealer;
import domain.user.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private final static CardPrintMapper cardPrintMapper = new CardPrintMapper();

    public static void printCardsStatus(Dealer dealer, List<Player> players) {
        List<String> nameValues = players.stream().map(Player::getNameValue).collect(Collectors.toList());
        System.out.println();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.", String.join(", ", nameValues));
        System.out.println();
        printCardsStatusOfDealer(dealer);
        players.forEach(OutputView::printCardsStatusOfPlayer);
        System.out.println();
    }

    public static String userHavingCards(AbstractUser user) {
        List<String> cardTexts = user.getCards().stream().map(cardPrintMapper::transformToPrintCard)
                .collect(Collectors.toList());
        return String.join(", ", cardTexts);
    }

    public static String dealerHavingCardFirst(Dealer dealer) {
        return cardPrintMapper.transformToPrintCard(dealer.getFirstCard());
    }

    public static void printCardsStatusOfDealer(Dealer dealer) {
        System.out.printf("%s카드: %s", "딜러", dealerHavingCardFirst(dealer));
        System.out.println();
    }

    public static void printCardsStatusOfPlayer(Player player) {
        System.out.printf("%s카드: %s", player.getNameValue(), userHavingCards(player));
        System.out.println();
    }

    private static void printCardsStatusAndScoreOfDealer(Dealer dealer) {
        System.out.printf("%s 카드: %s", "딜러", userHavingCards(dealer));
        System.out.println(" - 결과: " + dealer.calculateScore());
    }

    private static void printCardsStatusAndScoreOfPlayer(Player player) {
        System.out.printf("%s카드: %s", player.getNameValue(), userHavingCards(player));
        System.out.println(" - 결과: " + player.calculateScore());
    }

    public static void announceAddCardToDealer() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardsStatusWithScore(Dealer dealer, List<Player> players) {
        System.out.println();
        printCardsStatusAndScoreOfDealer(dealer);
        players.forEach(OutputView::printCardsStatusAndScoreOfPlayer);
    }

    public static void printResults(PlayerRevenues playerRevenues) {
        System.out.println();
        System.out.println("## 최종 수익");
        System.out.printf("딜러: %d", playerRevenues.dealerRevenue());
        System.out.println();
        playerRevenues.getRepository().forEach((player, result) -> System.out.println(
                player.getNameValue() + ": " + playerRevenues.findByPlayer(player)));
    }
}
