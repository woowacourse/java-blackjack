package blackjack.view;

import blackjack.domain.GameResults;
import blackjack.domain.card.Card;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    public void printInitCardsToPlayers(final Players players) {
        String playerNames = players.getGamblers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));

        System.out.printf(NEW_LINE + "딜러와 %s에게 2장을 나누었습니다.", playerNames);
        printCardsToPlayers(players);
        System.out.println();
    }

    public void printCardsToPlayer(final Player player) {
        String cards = player.getCards().getCards().stream()
                .map(this::formatCardMessage)
                .collect(Collectors.joining(", "));

        System.out.printf("%s카드: %s",
                player.getName(),
                cards
        );
    }

    public void println() {
        System.out.printf(NEW_LINE);
    }

    public void printDealerHitAndDealCard() {
        System.out.println(NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printResultCardsToPlayers(final Players players) {
        println();
        printResultCardsToPlayer(players.getDealer());
        println();
        players.getGamblers().forEach(gambler -> {
            printResultCardsToPlayer(gambler);
            println();
        });
    }

    public void printGameResults(final GameResults gameResults) {
        Map<Player, Integer> gameResult = gameResults.getGameResults();

        System.out.println(NEW_LINE + "## 최종 수익");

        gameResult.entrySet().forEach(this::printGamblerGameResult);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    private void printCardsToPlayers(final Players players) {
        printCardsToPlayer(players.getDealer());
        println();
        players.getGamblers().forEach(gambler -> {
            printCardsToPlayer(gambler);
            println();
        });
    }

    private String formatCardMessage(Card card) {
        return CardNumberView.getNumberMessage(card.getNumber()) + CardShapeView.getShapeMessage(card.getShape());
    }

    private void printResultCardsToPlayer(final Player player) {
        printCardsToPlayer(player);
        System.out.printf(" - 결과: %d", player.getCardScore());
    }

    private void printGamblerGameResult(final Map.Entry<Player, Integer> entry) {
        String name = entry.getKey().getName();
        System.out.println(name + ": " + entry.getValue());
    }
}
