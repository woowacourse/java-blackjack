package blackjack.view;

import static blackjack.domain.participant.Dealer.DEALER_NAME;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.PlayerResult;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printParticipantHands(Dealer dealer, List<Player> players) {
        System.out.printf("\n딜러와 %s에게 2장의 카드를 나누었습니다.", getPlayerNames(players));

        printOpenCard(dealer);
        for (Player player : players) {
            printHand(player);
        }
        System.out.println();
    }

    private static String getPlayerNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    private static void printOpenCard(Dealer dealer) {
        printHand(dealer.getName(), cardToString(dealer.getOpenCard()));
    }

    public static void printHand(Participant participant) {
        printHand(participant.getName(), cardsToString(participant.getCards()));
    }

    private static void printHand(String name, String cards) {
        System.out.printf("%s 카드: %s\n", name, cards);
    }

    public static void printDealerDrawMessage() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardResult(Dealer dealer, List<Player> players) {
        System.out.println();
        printCardResult(dealer.getName(), cardsToString(dealer.getCards()), dealer.getScore());

        for (Player player : players) {
            printCardResult(player.getName(), cardsToString(player.getCards()), player.getScore());
        }
    }

    public static void printGameResult(GameResult result) {
        System.out.println("\n## 최종 수익");
        printWinningMoney(DEALER_NAME, result.getDealerWinningMoney());
        for (PlayerResult playersResult : result.getPlayersResults()) {
            printWinningMoney(playersResult.getName(), playersResult.getWinningMoney());
        }
    }

    private static void printWinningMoney(String name, Money winningMoney) {
        System.out.printf("%s: %d\n", name, winningMoney.toInt());
    }

    private static void printCardResult(String name, String cards, int result) {
        System.out.printf("%s 카드: %s - 결과: %d\n", name, cards, result);
    }

    public static String cardsToString(List<Card> cards) {
        return cards.stream()
                .map(OutputView::cardToString)
                .collect(Collectors.joining(", "));
    }

    public static String cardToString(Card card) {
        return card.getRankInitial() + card.getSuitName();
    }
}
