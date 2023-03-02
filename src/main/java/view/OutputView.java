package view;

import domain.Dealer;
import domain.Player;
import domain.Players;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String INITIAL_DISTRUIBUTE_MESSAGE = "%s와 %s에게 2장을 나누었습니다." + System.lineSeparator();

    public void printInitialCards(Dealer dealer, Players players) {
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
}
