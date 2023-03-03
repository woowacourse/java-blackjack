package view;

import domain.Dealer;
import domain.Player;
import domain.Players;
import domain.Result;

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


    public void printInitialCards(Dealer dealer, Players players) {
        System.out.println();
        System.out.printf(INITIAL_DISTRUIBUTE_MESSAGE, dealer.getName().getName(), players.getPlayersName()
                .stream()
                .collect(Collectors.joining(", ")));
        System.out.println(dealer.getName().getName() + ": " + dealer.getInfo().get(dealer.getName().getName()).get(0));
        for (String key : players.getInfo().keySet()) {
            List<String> value = players.getInfo().get(key);
            System.out.println(key + "카드: " + value.stream().collect(Collectors.joining(", ")));
        }
    }

    public void printPlayerCardsInfo(Player player) {
        System.out.println(player.getName().getName() + "카드: " +
                player.getCards().stream().collect(Collectors.joining(", ")));

    }

    public void printDistributeDealer(Dealer dealer) {
        System.out.println();
        System.out.printf(DEALER_DISTRIBUTE_MESSAGE, dealer.getName().getName());
    }

    public void printCardsResult(Dealer dealer, Players players) {
        System.out.println();
        System.out.printf(DEALER_CARDS_RESULT_MESSAGE, dealer.getName().getName(), dealer.getCards()
                .stream()
                .collect(Collectors.joining(", ")), dealer.getCardsSum());
        for (String key : players.getInfo().keySet()) {
            List<String> value = players.getInfo().get(key);
            System.out.printf(PLAYER_CARDS_RESULT_MESSAGE, key, value.stream()
                    .collect(Collectors.joining(", ")), players.getCardsSum(key));
        }
    }

    public void printWinnerResult(Map<String, List<Result>> dealerResult, Map<String, Result> playerResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        String name = dealerResult.keySet().stream().findFirst().get();
        List<Result> dealerResults = dealerResult.get(name);
        System.out.printf(DEALER_RESULT_MESSAGE, name, dealerResults.stream().filter(s->s == Result.LOSE).count(),
                dealerResults.stream().filter(s->s == Result.DRAW).count(),
                dealerResults.stream().filter(s->s == Result.WIN).count());
        for(String key : playerResult.keySet()) {
            System.out.printf(PLAYER_RESULT_MESSAGE, key,playerResult.get(key).getResult());
        }
    }


}
