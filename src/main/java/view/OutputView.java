package view;

import domain.Card;
import domain.Game;
import domain.GameResult;
import domain.Player;
import java.util.Arrays;
import java.util.List;

public class OutputView {
    private static final String DELIMITER = ", ";

    public void displayInitialDeal(Game game) {
        List<Player> players = game.getPlayers();
        List<String> playerNames = players.stream().map(Player::getName).toList();
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", String.join(DELIMITER, playerNames));
        displayInitialDealerCards(game);
        displayEmptyLine();
        displayAllPlayerAndCards(players);
        System.out.println();
    }

    public void displayAllPlayerAndCards(List<Player> players) {
        players.forEach((player) -> {
            displayPlayerAndCards(player);
            displayEmptyLine();
        });
    }

    public void displayDealerHitResult() {
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    public void displayEmptyLine() {
        System.out.println();
    }

    public void displayInitialDealerCards(Game game) {
        System.out.printf("딜러카드: %s", game.getDealerCards().getFirst().getNotation());
    }

    public void displayDealerCards(Game game) {
        List<String> cardNotations = game.getDealerCards().stream()
                .map(Card::getNotation)
                .toList();
        System.out.printf("딜러카드: %s", String.join(DELIMITER, cardNotations));
    }

    public void displayScore(Game game) {
        displayDealerCards(game);
        System.out.printf(" - 결과: %d%n", game.getDealerScore());
        for (Player player : game.getPlayers()) {
            displayPlayerAndCards(player);
            System.out.printf(" - 결과: %d%n", player.calculateScore());
        }
    }

    public void displayPlayerAndCards(Player player) {
        String name = player.getName();
        List<String> cardNotations = player.getCards().stream()
                .map(Card::getNotation)
                .toList();
        System.out.printf("%s카드: %s", name, String.join(DELIMITER, cardNotations));
    }

    public void displayGameResult(Game game) {
        displayEmptyLine();
        System.out.println("## 최종 승패");
        System.out.print("딜러: ");
        Arrays.stream(GameResult.values())
                .filter((gameResult) -> game.getDealerGameResultCount(gameResult) != 0)
                .forEach((gameResult) -> System.out.printf("%d%s ", game.getDealerGameResultCount(gameResult),
                        gameResult.getName()));
        displayEmptyLine();
        game.getPlayers()
                .forEach(player -> System.out.printf("%s: %s%n", player.getName(),
                        game.getPlayerGameResult(player).getName()));
    }
}
