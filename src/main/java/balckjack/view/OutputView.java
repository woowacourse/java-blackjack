package balckjack.view;

import balckjack.domain.Card;
import balckjack.domain.CardDeck;
import balckjack.domain.Dealer;
import balckjack.domain.Participant;
import balckjack.domain.Player;
import balckjack.domain.Players;
import balckjack.domain.Referee;
import balckjack.domain.Result;
import balckjack.domain.Score;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static final int DEALER_DISPLAY_COUNT = 1;

    private OutputView() {
    }

    public static void printInitCardDeck(Participant dealer, Players players) {
        System.out.println();
        final List<String> playerNames = players.getPlayerNames();
        final String dealerName = dealer.getName().getValue();
        final String processedPlayerNames = String.join(", ", playerNames);
        final String displayCards = convertCardDeckString(dealer.getCardDeck(),
            DEALER_DISPLAY_COUNT);

        System.out.println(String.format("%s와 %s에게 2장을 나누었습니다.", dealerName, processedPlayerNames));
        printParticipantsCardDeck(players, playerNames, dealerName, displayCards);
        System.out.println();
    }

    private static String convertCardDeckString(CardDeck cardDeck) {
        return cardDeck.getCards().stream().map(Card::toString).collect(Collectors.joining(", "));
    }

    private static String convertCardDeckString(CardDeck cardDeck, int displayCount) {
        return cardDeck.getCards().stream().map(Card::toString).limit(displayCount)
            .collect(Collectors.joining(", "));
    }

    private static void printParticipantsCardDeck(Players players, List<String> playerNames,
        String dealerName, String displayCards) {
        System.out.println(String.format("%s: %s", dealerName, displayCards));

        for (int index = 0; index < playerNames.size(); index++) {
            printParticipantCardDeck(players.getPlayers().get(index));
        }
    }

    public static void printParticipantCardDeck(Player player) {
        System.out.println(String.format("%s카드: %s", player.getName().getValue(),
            convertCardDeckString(player.getCardDeck())));
    }

    public static void printDealerPickMessage(Dealer dealer) {
        System.out.println(
            String.format("%s는 16 이하라 한장의 카드를 더 받았습니다.", dealer.getName().getValue()));
    }

    public static void printFinalCardDeck(Participant dealer, Players players, Referee referee) {
        System.out.println();
        final List<String> playerNames = players.getPlayerNames();

        printParticipantCardDeck(dealer, dealer.getCardDeck().calculateScore());
        for (int index = 0; index < playerNames.size(); index++) {
            printParticipantCardDeck(players.getPlayers().get(index),
                players.getPlayers().get(index).getCardDeck().calculateScore());
        }
        System.out.println();
    }

    private static void printParticipantCardDeck(Participant participant, Score score) {
        final String cards = convertCardDeckString(participant.getCardDeck());
        System.out.println(
            String.format("%s카드: %s - 결과 : %s", participant.getName().getValue(), cards, score));
    }

    private static String convertScoreToString(int number) {
        String score = String.valueOf(number);
        if (score.equals("-1")) {
            score = "Burst";
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
            System.out.println(String.format("%s: %s", names.get(i), results.get(i)));
        }
    }

    private static void printDealerResult(Map<String, Long> dealerResult, Dealer dealer) {
        System.out.print(String.format("%s: ", dealer.getName().getValue()));
        for (Result result : Result.values()) {
            if (dealerResult.containsKey(result)) {
                Long count = dealerResult.get(result);
                System.out.print(String.format("%d%s ", count, reverse(result)));
            }
        }
    }

    private static Result reverse(Result result) {
        if (result == Result.WIN) {
            return Result.LOSE;
        }
        if (result == Result.LOSE) {
            return Result.WIN;
        }
        return result;
    }

    public static void printBurstMessage() {
        System.out.println(">>>Burst<<< 너~무 아쉬워요:( ");
    }

    public static void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
