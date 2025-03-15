package view;

import java.util.List;
import java.util.Map;
import model.betting.Bet;
import model.deck.Card;
import model.deck.Gameable;
import model.participant.Dealer;
import model.participant.Players;
import model.result.GameResult;
import model.participant.Player;
import model.result.ParticipantWinningResult;

public final class OutputView {
    private static final String JOIN_DELIMITER = ", ";
    private static final String DEALER_PRINT_MESSAGE = "딜러";

    /**
     * 첫 카드 배분 결과 출력
     */
    public static void printInitialDeal(final Players players, Dealer dealer) {
        printCardDivisionStart(players.getPlayers());
        printInitialDealerDeal(dealer);
        players.getPlayers().forEach(OutputView::printInitialPlayersDeal);
    }

    private static void printCardDivisionStart(final List<Player> players) {
        List<String> playerNames = players.stream()
                .map(Player::getName)
                .toList();
        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.\n", String.join(JOIN_DELIMITER, playerNames));
    }

    private static void printInitialDealerDeal(Dealer dealer) {
        printParticipantAndHands(dealer.openInitialDeal(), DEALER_PRINT_MESSAGE);
        System.out.println();
    }

    private static void printInitialPlayersDeal(final Player player) {
        printParticipantAndHands(player.openInitialDeal(), player.getName());
        System.out.println();
    }

    /**
     * 카드 뽑기 1턴 결과 출력
     */
    public static void printHitOrStandQuestion(final Player player) {
        System.out.println();
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printPlayerHitResult(final Player player) {
        System.out.println();
        printParticipantAndHands(player.getHandCards(), player.getName());
    }

    public static void printDealerHitResult() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    /**
     * 카드 뽑기 최종 결과 출력
     */
    public static void printDealerFinalScore(Dealer dealer) {
        printParticipantAndHands(dealer.getHandCards(), DEALER_PRINT_MESSAGE);
        printFinalScore(dealer);
    }

    public static void printPlayersFinalScore(final Players players) {
        players.getPlayers().forEach(player -> {
            printPlayerHitResult(player);
            printFinalScore(player);
        });
    }

    private static void printFinalScore(Gameable gameable) {
        System.out.println(" - 결과: " + gameable.calculateFinalScore());
    }

    /**
     * 최종 승패 결과 출력
     */
    public static void printDealerFinalResult(final Map<GameResult, Integer> dealerWinning) {
        System.out.println("\n## 최종 승패");
        System.out.printf("%s: %s\n", DEALER_PRINT_MESSAGE, getGameResultMessage(dealerWinning));
    }

    public static void printPlayerFinalResult(final ParticipantWinningResult participantWinningResult) {
        Map<Player, GameResult> playerResults = participantWinningResult.getResult();
        for (Player player : playerResults.keySet()) {
            GameResult playerResult = participantWinningResult.getResult().get(player);
            System.out.println(player.getName() + ": " + playerResult.getResultMeaning());
        }
    }

    private static String getGameResultMessage(final Map<GameResult, Integer> dealerWinning) {
        String message = "";
        for (GameResult gameResult : GameResult.values()) {
            if (dealerWinning.containsKey(gameResult)) {
                message += (dealerWinning.get(gameResult) + gameResult.getResultMeaning() + " ");
            }
        }
        return message;
    }

    private static void printParticipantAndHands(List<Card> cards, String name) {
        List<String> cardsName = getCardNameMessagesFrom(cards);
        System.out.printf("%s카드: %s", name, String.join(JOIN_DELIMITER, cardsName));
    }

    private static List<String> getCardNameMessagesFrom(List<Card> cards) {
        return cards.stream()
                .map(Card::getCardName)
                .toList();
    }

    public static void printExceptionMessage(final String message) {
        System.out.println("[ERROR] " + message);
    }

    public static void printDealerRevenue(Dealer dealer) {
        System.out.println("\n## 최종 수익");
        System.out.printf("%s: %,d\n", DEALER_PRINT_MESSAGE, dealer.calculateRevenue());
    }

    public static void printPlayersRevenue(Player player, Bet bet) {
        int revenue = bet.calculateBetterRevenue();
        System.out.printf("%s: %,d\n", player.getName(), revenue);
    }
}
