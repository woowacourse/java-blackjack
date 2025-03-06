package view;

import domain.Card;
import domain.Game;
import domain.Player;
import java.util.List;

public class OutputView {
    private static final String DELIMITER = ", ";

    public void displayInitialDeal(Game game) {
        List<Player> players = game.getPlayers();
        List<String> playerNames = players.stream().map(Player::getName).toList();
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", String.join(DELIMITER, playerNames));
        System.out.printf("딜러카드: %s%n", game.getDealerCards().getFirst().getNotation());
        players.forEach(this::displayPlayerAndCards);
        System.out.println();
    }

    public void displayPlayerAndCards(Player player) {
        String name = player.getName();
        List<String> cardNotations = player.getCards().stream()
                .map(Card::getNotation)
                .toList();
        System.out.printf("%s카드: %s%n", name, String.join(DELIMITER, cardNotations));
    }

    public void displayDealerHitResult() {
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    public void displayEmptyLine() {
        System.out.println();
    }
}
