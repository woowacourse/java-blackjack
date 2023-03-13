package view;

import domain.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String INITIAL_DISTRUIBUTE_MESSAGE = "%s와 %s에게 2장을 나누었습니다." + System.lineSeparator();
    private static final String DEALER_DISTRIBUTE_MESSAGE = "%s 16이하라 한장의 카드를 더 받았습니다." + System.lineSeparator();
    private static final String DEALER_CARDS_RESULT_MESSAGE = "%s 카드: %s - 결과: %d" + System.lineSeparator();
    private static final String PLAYER_CARDS_RESULT_MESSAGE = "%s카드: %s - 결과: %d" + System.lineSeparator();
    private static final String PLAYER_RESULT_MESSAGE = "%s: %s" + System.lineSeparator();
    private static final String SPLIT_DELIMITER = ", ";
    private static final String NAME_DELIMITER = ": ";
    public static final String FINAL_PROFIT_MESSAGE = "## 최종 수익";


    public void printInitialCards(Dealer dealer, Players players) {
        System.out.println();
        System.out.printf(INITIAL_DISTRUIBUTE_MESSAGE, dealer.getName(), players.getPlayersName().stream()
                .collect(Collectors.joining(SPLIT_DELIMITER)));
        Card dealerCard = dealer.getCards().get(0);
        System.out.println(dealer.getName() + NAME_DELIMITER + getCardInformation(dealerCard));
        for (String key : players.getInformation().keySet()) {
            List<Card> value = players.getInformation().get(key);
            System.out.println(key + "카드: " + value.stream()
                    .map(this::getCardInformation)
                    .collect(Collectors.joining(SPLIT_DELIMITER)));
        }
    }

    private String getCardInformation(Card card) {
        return card.getCardNumber().getName() + card.getCardPattern().getPattern();
    }

    public void printPlayerCardsInformation(Player player) {
        System.out.println(player.getName() + "카드: " +
                player.getCards().stream().
                        map(this::getCardInformation).collect(Collectors.joining(", ")));

    }

    public void printDistributeDealer(Dealer dealer) {
        System.out.println();
        System.out.printf(DEALER_DISTRIBUTE_MESSAGE, dealer.getName());
    }

    public void printCardsResult(Dealer dealer, Players players) {
        System.out.println();
        System.out.printf(DEALER_CARDS_RESULT_MESSAGE, dealer.getName(), dealer.getCards().stream()
                .map(this::getCardInformation)
                .collect(Collectors.joining(SPLIT_DELIMITER)), dealer.getCardsSum());
        for (String key : players.getInformation().keySet()) {
            List<Card> value = players.getInformation().get(key);
            System.out.printf(PLAYER_CARDS_RESULT_MESSAGE, key, value.stream()
                    .map(this::getCardInformation)
                    .collect(Collectors.joining(SPLIT_DELIMITER)), players.getCardsSum(key));
        }
    }

    public void printWinnerResult(Dealer dealer, BettingTable bettingTable) {
        System.out.println();
        System.out.println(FINAL_PROFIT_MESSAGE);
        Map<Player, Money> result = bettingTable.getBettingTable();
        System.out.println(dealer.getName() + NAME_DELIMITER + -bettingTable.sum());
        for (Player player : result.keySet()) {
            System.out.printf(PLAYER_RESULT_MESSAGE, player.getName(),
                    result.get(player).getMoney());
        }
    }

}
