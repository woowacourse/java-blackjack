package ui;

import domain.BlackjackResult;
import domain.CardHand;
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
        players.forEach(player -> OutputView.printCardsStatusOfPlayer(player.getNameValue(), player.getCardHand()));
        System.out.println();
    }

    private static String transformCardToPrint(CardHand cardHand) {
        List<String> cardTexts = cardHand.getCards().stream().map(cardPrintMapper::transformToPrintCard)
                .collect(Collectors.toList());
        return String.join(", ", cardTexts);
    }

    private static String dealerHavingCardFirst(Dealer dealer) {
        return cardPrintMapper.transformToPrintCard(dealer.getFirstCard());
    }

    private static void printCardsStatusOfDealer(Dealer dealer) {
        System.out.printf("%s카드: %s", "딜러", dealerHavingCardFirst(dealer));
        System.out.println();
    }

    public static void printCardsStatusOfPlayer(String playerName, CardHand cardHand) {
        System.out.printf("%s카드: %s", playerName, transformCardToPrint(cardHand));
        System.out.println();
    }

    private static void printCardsStatusAndScoreOfDealer(Dealer dealer) {
        System.out.printf("%s 카드: %s", "딜러", transformCardToPrint(dealer.getCardHand()));
        System.out.println(" - 결과: " + dealer.calculateScore());
    }

    private static void printCardsStatusAndScoreOfPlayer(Player player) {
        System.out.printf("%s카드: %s", player.getNameValue(),
                transformCardToPrint(player.getCardHand()));
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

    public static void printResults(BlackjackResult blackjackResult) {
        System.out.println();
        System.out.println("## 최종 수익");
        System.out.printf("딜러: %d", blackjackResult.getDealerResult().getBettingResult().getResult());
        System.out.println();
        blackjackResult.getPlayerResults().forEach(playerResult -> System.out.println(
                playerResult.getName().getValue() + ": " + playerResult.getBettingResult().getResult()));
    }
}
