package view;

import dto.result.ParticipantProfit;
import dto.result.ProfitResult;
import java.util.List;
import dto.result.ParticipantCurrentHand;

public class OutputView {
    private static final String JOIN_DELIMITER = ", ";
    private static final String RESULT_DELIMITER = ": ";

    private static final String CARD_TEXT = "카드: ";
    private static final String DEALER_DRAW_ONE_CARD_TEXT = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_RESULT_TEXT = "## 최종 수익";
    private static final String DEALER_INIT_DECK_TEXT = "딜러와 ";
    private static final String INIT_DECK_TEXT = "에게 2장을 나누었습니다.";
    private static final String DEALER_CARD_TEXT = "딜러카드: ";
    private static final String SCORE_TEXT = " - 결과: ";

    public static void printInitDeck(List<ParticipantCurrentHand> players, String dealerFirstCard) {
        List<String> playerNames = players.stream()
                .map(ParticipantCurrentHand::name)
                .toList();

        printInitDeckDrawMessage(playerNames);
        printDealerInitDeck(dealerFirstCard);
        printPlayersCurrentDeck(players);
        printNewLine();
    }

    public static void printPlayerCurrentDeck(ParticipantCurrentHand participantHand) {
        System.out.println(participantHand.name() + CARD_TEXT + String.join(JOIN_DELIMITER, participantHand.deck()));
    }

    public static void printDealerCardDrawMessage() {
        System.out.println(DEALER_DRAW_ONE_CARD_TEXT);
        printNewLine();
    }

    public static void printParticipantHandWithScore(ParticipantCurrentHand dealerResult, List<ParticipantCurrentHand> players) {
        printPlayerHandWithScore(dealerResult);
        for(ParticipantCurrentHand participantResult : players) {
            printPlayerHandWithScore(participantResult);
        }
        printNewLine();
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printProfitResult(ProfitResult profitResult) {
        System.out.println(FINAL_RESULT_TEXT);
        printDealerResult(profitResult.dealerProfit());
        printPlayersProfit(profitResult.playerProfit());
    }

    private static void printInitDeckDrawMessage(List<String> players) {
        System.out.println(DEALER_INIT_DECK_TEXT + String.join(JOIN_DELIMITER, players) + INIT_DECK_TEXT);
    }

    private static void printDealerInitDeck(String firstCard) {
        System.out.println(DEALER_CARD_TEXT + firstCard);
    }

    private static void printPlayersCurrentDeck(List<ParticipantCurrentHand> players) {
        for(ParticipantCurrentHand participantHand : players) {
            printPlayerCurrentDeck(participantHand);
        }
    }

    private static void printPlayerHandWithScore(ParticipantCurrentHand participantResult) {
        System.out.println(
                participantResult.name() + CARD_TEXT + String.join(JOIN_DELIMITER, participantResult.deck()) + SCORE_TEXT + participantResult.score());
    }

    private static void printDealerResult(ParticipantProfit dealerProfit) {
        System.out.println(dealerProfit.name() + ": " + dealerProfit.profit());
    }

    private static void printPlayersProfit(List<ParticipantProfit> playersProfit) {
       playersProfit.forEach(playerProfit -> System.out.println(playerProfit.name() + RESULT_DELIMITER + playerProfit.profit()));
    }

}
