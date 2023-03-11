package blackjack.view;

import static java.lang.String.format;

import blackjack.domain.game.Bets;
import blackjack.domain.game.Money;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class OutputView {
    private static OutputView INSTANCE = new OutputView();
    private static final String NEW_LINE = System.lineSeparator();
    private static final int INITIAL_DRAW_COUNT = 2;
    private static final int DEALER_OPEN_CARD_INDEX = 0;
    private static final String DELIMITER = ", ";

    private OutputView() {
    }

    public static OutputView getInstance() {
        return INSTANCE;
    }

    public void printInitialDraw(final List<Player> players) {
        System.out.println(NEW_LINE + generateNameMessages(players) + "에게 " + INITIAL_DRAW_COUNT + "장을 나누었습니다.");
        System.out.println(generateInitialDrawMessages(players) + NEW_LINE);
    }

    private String generateNameMessages(final List<Player> players) {
        return players.stream()
                .map(Player::getNameValue)
                .collect(Collectors.joining(DELIMITER));
    }

    private String generateInitialDrawMessages(final List<Player> players) {
        return players.stream()
                .map(this::generateInitialDrawMessage)
                .collect(Collectors.joining(NEW_LINE));
    }

    private String generateInitialDrawMessage(final Player player) {
        if (player.isDealer()) {
            return generateCardMessage(player, player.getSymbols().get(DEALER_OPEN_CARD_INDEX));
        }
        return generateCardMessage(player, generateSymbolMessage(player));
    }

    private String generateCardMessage(final Player player, final String message) {
        return format("%s 카드: %s", player.getNameValue(), message);
    }

    private String generateSymbolMessage(final Player player) {
        return String.join(DELIMITER, player.getSymbols());
    }

    public void printDealerDraw(final Dealer dealer) {
        final String dealerDrawMessage = NEW_LINE + "딜러는 16이하라 한 장의 카드를 더 받았습니다." + NEW_LINE;
        System.out.println(dealerDrawMessage.repeat(dealer.getCardCount() - INITIAL_DRAW_COUNT));
    }

    public void printPlayerDraw(final Player player) {
        System.out.println(generateCardMessage(player, generateSymbolMessage(player)));
    }

    public void printPlayerCardsWithScore(final Player player) {
        System.out.println(generateCardMessage(player, generateSymbolMessage(player) + generateScoreMessage(player)));
    }

    private String generateScoreMessage(final Player player) {
        return format(" - 결과: %d", player.score());
    }

    public void printBetResult(final Bets bets) {
        System.out.println(NEW_LINE + "## 최종 수익");
        System.out.println(String.format("%s: %s", "딜러", bets.getDealerProfit().getValue()));
        System.out.println(generateBetResultMessage(bets.getBets()));
    }

    private String generateBetResultMessage(final Map<Name, Money> bets) {
        return bets.keySet().stream()
                .map(name -> String.format("%s: %s", name.getValue(), bets.get(name).getValue()))
                .collect(Collectors.joining(NEW_LINE));
    }

    public void printException(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
