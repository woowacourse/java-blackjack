package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Player;
import blackjack.view.expressions.DenominationExpressions;
import blackjack.view.expressions.SuitExpressions;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printInitialHandOfEachPlayer(final Dealer dealer, final List<Player> players) {
        printInitialDistributionMessage(dealer, players);
        printDealerCard(dealer);
        players.forEach(OutputView::printPlayerCard);
    }

    private static void printInitialDistributionMessage(final Dealer dealer, final List<Player> players) {
        final String names = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));

        printLineSeparator();
        final String initialDistributionMessage = String.format("%s와 %s에게 2장을 나누었습니다.", dealer.getName(), names);
        System.out.println(initialDistributionMessage);
    }

    private static void printDealerCard(final Dealer dealer) {
        final Card card = dealer.getFirstCard();
        System.out.println(dealer.getName() + ": " + getCardInfo(card));
    }

    public static void printPlayerCard(final Player player) {
        final String playerCardInfo = getPlayerCardInfo(player);
        System.out.println(playerCardInfo);
    }

    public static void printDealerHitMessage(final Dealer dealer) {
        final String dealerHitMessage = String.format("%s는 16이하라 한장의 카드를 더 받았습니다.", dealer.getName());
        System.out.println(dealerHitMessage);
        printLineSeparator();
    }

    public static void printPlayerCardWithScore(final Participant participant) {
        final String playerCardInfo = getPlayerCardInfo(participant);
        System.out.println(playerCardInfo + " - 결과: " + participant.getScore());
    }

    private static String getPlayerCardInfo(final Participant participant) {
        return participant.getName() + "카드: " +
                participant.getCards()
                        .stream()
                        .map(OutputView::getCardInfo)
                        .collect(Collectors.joining(", "));
    }

    private static String getCardInfo(final Card card) {
        return DenominationExpressions.mapCardNumberToString(card.getNumber()) +
                SuitExpressions.mapCardShapeToString(card.getShape());
    }

    public static void printProfits(final Participants participants) {

        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();

        printLineSeparator();
        System.out.println("## 최종 수익");
        System.out.println(
                String.format("%s: %d", dealer.getName(), participants.calculateDealerProfit()));

        players.forEach(player ->
                System.out.println(String.format("%s: %d", player.getName(),
                        player.getBettingAmount().findProfit(dealer.judgePlayerStatus(player)).getValue())));
    }

    private static void printLineSeparator() {
        System.out.println();
    }
}
