package blackjack.view;

import blackjack.domain.GameResult;
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
                .map(player -> player.getName().getName())
                .collect(Collectors.joining(", "));

        System.out.printf(NEW_LINE + "딜러와 %s에게 2장을 나누었습니다.", playerNames);
        printCardsToPlayers(players);
        println();
        println();
    }

    public void printCardsToPlayer(final Player player) {
        String cards = player.getHand().getCards().getCards().stream()
                .map(this::formatCardMessage)
                .collect(Collectors.joining(", "));

        System.out.printf(NEW_LINE + "%s카드: %s",
                player.getName().getName(),
                cards
        );
    }

    public void println() {
        System.out.printf(NEW_LINE);
    }

    public void printDealerHitAndDealCard() {
        System.out.println(NEW_LINE + "딜러는 " + Player.HIT_THRESHOLD + "이하라 한장의 카드를 더 받았습니다.");
    }

    public void printResultCardsToPlayers(final Players players) {
        printResultCardsToPlayer(players.getDealer());
        players.getGamblers().forEach(this::printResultCardsToPlayer);
    }

    public void printGameResults(final GameResult gameResults) {
        Map<Player, Integer> gameResult = gameResults.getGameResults();

        System.out.println(NEW_LINE + NEW_LINE + "## 최종 수익");

        gameResult.entrySet().forEach(this::printGamblerGameResult);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    private void printCardsToPlayers(final Players players) {
        printInitCardsToPlayer(players.getDealer());
        players.getGamblers().forEach(this::printInitCardsToPlayer);
    }

    private void printInitCardsToPlayer(final Player player) {
        String cards = player.getOpenedCards().stream()
                .map(this::formatCardMessage)
                .collect(Collectors.joining(", "));

        System.out.printf(NEW_LINE + "%s카드: %s",
                player.getName().getName(),
                cards
        );
    }

    private String formatCardMessage(final Card card) {
        return CardNumberView.getNumberMessage(card.getNumber()) + CardShapeView.getShapeMessage(card.getShape());
    }

    private void printResultCardsToPlayer(final Player player) {
        printCardsToPlayer(player);
        System.out.printf(" - 결과: %d", player.getCardScore());
    }

    private void printGamblerGameResult(final Map.Entry<Player, Integer> entry) {
        String name = entry.getKey().getName().getName();
        System.out.println(name + ": " + entry.getValue());
    }
}
