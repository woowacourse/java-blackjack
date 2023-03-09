package view;

import domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String INITIAL_DISTRUIBUTE_MESSAGE = "%s와 %s에게 2장을 나누었습니다." + System.lineSeparator();
    private static final String DEALER_DISTRIBUTE_MESSAGE = "%s 16이하라 한장의 카드를 더 받았습니다." + System.lineSeparator();
    private static final String DEALER_CARDS_RESULT_MESSAGE = "%s 카드: %s - 결과: %d" + System.lineSeparator();
    private static final String PLAYER_CARDS_RESULT_MESSAGE = "%s카드: %s - 결과: %d" + System.lineSeparator();
    private static final String DEALER_RESULT_MESSAGE = "%s: %d승 %d무 %d패" + System.lineSeparator();
    private static final String PLAYER_RESULT_MESSAGE = "%s: %s" + System.lineSeparator();
    private static final String SPLIT_DELIMITER = ", ";

    public String namimgCard(Card card) {
        return card.getCardNumber().getName() + card.getCardPattern().getPattern();
    }

    public void printInitialCards(Dealer dealer, Players players) {
        System.out.println();
        System.out.printf(INITIAL_DISTRUIBUTE_MESSAGE, dealer.getName(), players.getPlayersName()
                .stream()
                .collect(Collectors.joining(SPLIT_DELIMITER)));
        Card dealerCard = dealer.getCards().get(0);
        System.out.println(dealer.getName() + ": " + namimgCard(dealerCard));
        for (String key : players.getInfo().keySet()) {
            List<Card> value = players.getInfo().get(key);
            System.out.println(key + "카드: " + value.stream().map(this::namimgCard)
                    .collect(Collectors.joining(SPLIT_DELIMITER)));
        }
    }

    public void printPlayerCardsInfo(Player player) {
        System.out.println(player.getName() + "카드: " +
                player.getCards().stream().
                        map(this::namimgCard).collect(Collectors.joining(", ")));

    }

    public void printDistributeDealer(Dealer dealer) {
        System.out.println();
        System.out.printf(DEALER_DISTRIBUTE_MESSAGE, dealer.getName());
    }

    public void printCardsResult(Dealer dealer, Players players) {
        System.out.println();
        System.out.printf(DEALER_CARDS_RESULT_MESSAGE, dealer.getName(), dealer.getCards()
                .stream().map(this::namimgCard)
                .collect(Collectors.joining(SPLIT_DELIMITER)), dealer.getCardsSum());
        for (String key : players.getInfo().keySet()) {
            List<Card> value = players.getInfo().get(key);
            System.out.printf(PLAYER_CARDS_RESULT_MESSAGE, key, value.stream()
                    .map(this::namimgCard)
                    .collect(Collectors.joining(SPLIT_DELIMITER)), players.getCardsSum(key));
        }
    }

    public void printWinnerResult(GameResult gameResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        String dealerName = gameResult.getDealerResult().keySet().stream().findFirst().get();
        List<ResultType> dealerResult = gameResult.getDealerResult().get(dealerName);
        System.out.printf(DEALER_RESULT_MESSAGE, dealerName,
                Collections.frequency(dealerResult, ResultType.WIN),
                Collections.frequency(dealerResult, ResultType.DRAW),
                Collections.frequency(dealerResult, ResultType.LOSE));
        for (String playerName : gameResult.getPlayerResult().keySet()) {
            System.out.printf(PLAYER_RESULT_MESSAGE, playerName,
                    gameResult.getPlayerResult().get(playerName).getResultType());
        }
    }

}
