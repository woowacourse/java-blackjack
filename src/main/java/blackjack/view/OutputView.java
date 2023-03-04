package blackjack.view;

import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.Referee;
import blackjack.domain.Result;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final int DEALER_DISPLAY_COUNT = 1;
    private static final String BURST_NAME = "BURST";
    private static final String BURST_CODE = "-1";
    private static final String DELIMITER = ", ";

    private OutputView() {
    }

    public static void printInitCardDeck(Participant dealer, Players players) {
        System.out.println();
        final List<String> playerNames = players.getPlayerNames();
        final String dealerName = dealer.getName().getValue();
        final String processedPlayernames = String.join(DELIMITER, playerNames);
        final List<String> displayCards = dealer.getCardDeck().getCardsInfo().subList(0,
            DEALER_DISPLAY_COUNT);
        final String processedDisplayCards = String.join(DELIMITER, displayCards);

        System.out.println(String.format("%s와 %s에게 2장을 나누었습니다.", dealerName, processedPlayernames));
        System.out.println(String.format("%s: %s", dealerName, processedDisplayCards));

        for (int index = 0; index < playerNames.size(); index++) {
            printParticipantCardDeck(players.getPlayers().get(index));
        }
        System.out.println();
    }

    public static void printParticipantCardDeck(Player player) {
        final String cards = String.join(DELIMITER, player.getCardDeck().getCardsInfo());

        System.out.println(String.format("%s카드 %s", player.getName().getValue(), cards));
    }

    public static void printParticipantCardDeck(Player player, int rawScore) {
        List<String> cards = player.getCardDeck().getCardsInfo();

        System.out.println(formatFinalCardDeckAndScore(player, cards, convertToBurst(rawScore)));
    }

    public static void printDealerPickMessage(Dealer dealer) {
        System.out.println(
            String.format("%s는 16 이하라 한장의 카드를 더 받았습니다.", dealer.getName().getValue()));
    }

    public static void printFinalCardDeckAndScore(Participant dealer, Players players, Referee referee) {
        System.out.println();
        final List<String> playerNames = players.getPlayerNames();
        final List<String> dealerCards = dealer.getCardDeck().getCardsInfo();

        String dealerScore = convertToBurst(referee.calculateDeckScore(dealer.getCardDeck()));
        System.out.println(formatFinalCardDeckAndScore(dealer, dealerCards, dealerScore));
        printFinalPlayerCardDeck(players, referee, playerNames);
        System.out.println();
    }

    private static void printFinalPlayerCardDeck(Players players, Referee referee, List<String> playerNames) {
        for (int index = 0; index < playerNames.size(); index++) {
            printParticipantCardDeck(players.getPlayers().get(index),
                    referee.calculateDeckScore(players.getPlayers().get(index).getCardDeck()));
        }
    }

    private static String formatFinalCardDeckAndScore(Participant player, List<String> cards, String score) {
        return String.format("%s카드: %s - 결과: %s", player.getName().getValue(),
                String.join(DELIMITER, cards), score);
    }

    private static String convertToBurst(int number) {
        String score = String.valueOf(number);

        if (score.equals(BURST_CODE)) {
            score = BURST_NAME;
        }
        return score;
    }

    public static void printResult(Map<String, Long> dealerResult, Dealer dealer, Players players,
        List<Result> results) {
        System.out.println("## 최종 승패");
        List<String> names = players.getPlayerNames();

        printDealerResult(dealerResult, dealer);
        System.out.println();

        for (int i = 0; i < names.size(); i++) {
            System.out.println(String.format("%s: %s", names.get(i), results.get(i).getResult()));
        }
    }

    private static void printDealerResult(Map<String, Long> dealerResult, Dealer dealer) {
        System.out.print(String.format("%s: ", dealer.getName().getValue()));
        for (Result result : Result.values()) {
            if (dealerResult.containsKey(result.getResult())) {
                Long count = dealerResult.get(result.getResult());
                System.out.print(String.format("%d%s ", count, reverse(result)));
            }
        }
    }

    private static String reverse(Result result) {
        if (result == Result.WIN) {
            return Result.LOSE.getResult();
        }
        if (result == Result.LOSE) {
            return Result.WIN.getResult();
        }
        return result.getResult();
    }

    public static void printBurstMessage() {
        System.out.println(">>>Burst<<< 너~무 아쉬워요:( ");
    }

    public static void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void println() {
        System.out.println();
    }
}
