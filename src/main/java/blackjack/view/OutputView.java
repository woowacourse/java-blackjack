package blackjack.view;

import blackjack.domain.Profit;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.Result;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {

    private static final String SPLIT_DELIMITER = ", ";
    private static final String NEW_LINE = System.lineSeparator();
    private static final StringBuilder finalResult = new StringBuilder();

    private OutputView() {
    }

    public static void printInitCard(final List<Player> players, final Card firstCardOfDealer) {
        String playerNames = printPlayerNames(players);
        System.out.println(NEW_LINE + "딜러와 " + playerNames + "에게 2장을 나누었습니다.");

        System.out.println("딜러: " + printCard(firstCardOfDealer));
        for (final Player player : players) {
            printParticipantCards(player.getName(), player.getCards());
            System.out.println();
        }
    }

    private static String printPlayerNames(final List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(SPLIT_DELIMITER));
    }

    public static void printParticipantCards(final String playerName, final Set<Card> cards) {
        System.out.print(playerName + "카드: " + printCards(cards));
    }

    private static String printCard(final Card card) {
        return card.getRank().getRankFormat() + card.getSuit().getValue();
    }

    private static String printCards(final Set<Card> cards) {
        return cards.stream()
                .map(OutputView::printCard)
                .collect(Collectors.joining(SPLIT_DELIMITER));
    }

    public static void printDealerReceiveOneMoreCard() {
        System.out.println();
        System.out.println(NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardsWithSum(final List<Player> players, final Dealer dealer) {
        System.out.println(NEW_LINE);
        printParticipantCards("딜러", dealer.getCards());
        System.out.println(" - 결과: " + dealer.calculateSumOfRank());
        for (Player player : players) {
            printParticipantCards(player.getName(), player.getCards());
            System.out.println(" - 결과: " + player.calculateSumOfRank());
        }
    }

    public static void printFinalProfit(final Profit dealerProfit, final Map<Player, Profit> playerProfit) {
        System.out.println(NEW_LINE + "## 최종 수익");
        System.out.println("딜러: " + dealerProfit.getProfit());
        for (final Player player : playerProfit.keySet()) {
            System.out.println(player.getName() + ": " + playerProfit.get(player).getProfit());
        }
    }

    public static void printFinalResult(final Map<Result, Integer> dealerResult, final Map<Player, Result> playerResult) {
        for (final Result result : dealerResult.keySet()) {
            generateDealerResult(dealerResult, result);
        }
        System.out.println(NEW_LINE + "## 최종 승패");
        System.out.println("딜러: " + finalResult);
        for (final Player player : playerResult.keySet()) {
            System.out.println(player.getName() + ": " + playerResult.get(player).getValue());
        }
    }

    private static void generateDealerResult(Map<Result, Integer> dealerResult, Result result) {
        finalResult.append(dealerResult.get(result)).append(result.getValue());
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
