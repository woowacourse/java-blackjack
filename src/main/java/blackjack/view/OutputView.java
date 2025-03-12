package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.domain.GameResults;
import blackjack.domain.card.Card;
import blackjack.domain.player.Gambler;
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

        System.out.printf(NEW_LINE + "딜러와 %s에게 2장을 나누었습니다." + NEW_LINE, playerNames);
        printCardsToPlayers(players);
    }

    public void printCardsToPlayer(final Player player) {
        String cards = player.getCards().getCards().stream()
                .map(this::formatCardMessage)
                .collect(Collectors.joining(", "));

        System.out.printf(NEW_LINE + "%s카드: %s",
                player.getName(),
                cards
        );
    }

    public void printDealerHitAndDealCard() {
        System.out.println(NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printResultCardsToPlayers(final Players players) {
        printResultCardsToPlayer(players.getDealer());
        players.getGamblers().forEach(this::printResultCardsToPlayer);
    }

    public void printGameResults(final GameResults gameResults) {
        Map<Gambler, GameResult> results = gameResults.getGameResults();

        System.out.println(NEW_LINE + "## 최종 승패");
        System.out.printf("딜러: %s" + NEW_LINE, formatDealerResultRate(gameResults));

        results.entrySet().forEach(this::printGamblerGameResult);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    private void printCardsToPlayers(final Players players) {
        printCardsToPlayer(players.getDealer());
        players.getGamblers().forEach(this::printCardsToPlayer);
        System.out.println();
    }

    private String formatCardMessage(Card card) {
        return CardNumberView.getNumberMessage(card.getNumber()) + CardShapeView.getShapeMessage(card.getShape());
    }

    private void printResultCardsToPlayer(final Player player) {
        printCardsToPlayer(player);
        System.out.printf(" - 결과: %d" + NEW_LINE, player.getCardScore());
    }

    private String formatDealerResultRate(final GameResults gameResults) {
        StringBuilder dealerRate = new StringBuilder();
        if (gameResults.getDealerWin() > 0) {
            dealerRate.append(String.format("%d승 ", gameResults.getDealerWin()));
        }
        if (gameResults.getDealerDraw() > 0) {
            dealerRate.append(String.format("%d무 ", gameResults.getDealerDraw()));
        }
        if (gameResults.getDealerLose() > 0) {
            dealerRate.append(String.format("%d패 ", gameResults.getDealerLose()));
        }
        return dealerRate.toString();
    }

    private void printGamblerGameResult(final Map.Entry<Gambler, GameResult> entry) {
        String name = entry.getKey().getName();
        GameResult gameResult = entry.getValue();
        System.out.println(name + ": " + formatPlayerResultRate(gameResult));
    }

    private String formatPlayerResultRate(final GameResult gameResult) {
        return GameResultView.getResultMessage(gameResult);
    }
}
