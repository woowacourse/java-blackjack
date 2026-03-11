package blackjack.view;

import static java.util.stream.Collectors.joining;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResults;
import java.util.List;

public class OutputView {

    private static final String DELIMITER = ", ";

    private static final String INITIAL_DEAL_MESSAGE = "%n딜러와 %s에게 2장을 나누었습니다.%n";
    private static final String DEALER_CARD_MESSAGE = "딜러카드: %s%n";
    private static final String PLAYER_CARD_MESSAGE = "%s카드: %s%n";

    private static final String DEALER_HIT_MESSAGE = "%n딜러는 16이하라 한장의 카드를 더 받았습니다.%n";

    private static final String FINAL_CARDS_FORMAT = "%s카드: %s - 결과: %d%n";

    private static final String FINAL_RESULTS_HEADER = "%n## 최종 수익%n";
    private static final String PROFIT_FORMAT = "%s: %d%n";

    private OutputView() {}

    public static void printInitialDeal(final Players players, final Dealer dealer) {
        final String playerNames = players.getPlayers().stream()
                .map(Player::getName)
                .collect(joining(DELIMITER));
        System.out.printf(INITIAL_DEAL_MESSAGE, playerNames);
        System.out.printf(DEALER_CARD_MESSAGE, dealer.getOpenCard().getDisplayName());
        players.getPlayers().forEach(OutputView::printPlayerCards);
        System.out.println();
    }

    public static void printPlayerCards(final Player player) {
        System.out.printf(PLAYER_CARD_MESSAGE, player.getName(), formatCards(player.getCards()));
    }

    public static void printDealerHit() {
        System.out.printf(DEALER_HIT_MESSAGE);
    }

    public static void printFinalCards(final Players players, final Dealer dealer) {
        System.out.println();
        printParticipantFinalCards(dealer.getName(), dealer.getCards(), dealer.calculateScore().getValue());
        players.getPlayers().forEach(player ->
                printParticipantFinalCards(player.getName(), player.getCards(), player.calculateScore().getValue())
        );
    }

    private static void printParticipantFinalCards(final String name, final List<Card> cards, final int score) {
        System.out.printf(FINAL_CARDS_FORMAT, name, formatCards(cards), score);
    }

    public static void printFinalProfits(final GameResults gameResults, final Dealer dealer) {
        System.out.printf(FINAL_RESULTS_HEADER);
        System.out.printf(PROFIT_FORMAT, dealer.getName(), gameResults.getDealerProfit().getAmount());
        gameResults.getPlayerProfits().forEach((player, profit) ->
                System.out.printf(PROFIT_FORMAT, player.getName(), profit.getAmount())
        );
    }

    private static String formatCards(final List<Card> cards) {
        return cards.stream()
                .map(Card::getDisplayName)
                .collect(joining(DELIMITER));
    }
}
