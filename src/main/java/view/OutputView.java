package view;

import dto.ParticipantCurrentHandResponse;
import dto.ParticipantProfitResponse;
import model.result.ParticipantProfit;
import model.result.ProfitResult;
import java.util.List;

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

    public static void printInitDeck(List<ParticipantCurrentHandResponse> players, String dealerFirstCard) {
        List<String> playerNames = players.stream()
                .map(ParticipantCurrentHandResponse::name)
                .toList();

        printInitDeckDrawMessage(playerNames);
        printDealerInitDeck(dealerFirstCard);
        printPlayersCurrentDeck(players);
        printNewLine();
    }

    public static void printPlayerCurrentDeck(ParticipantCurrentHandResponse participantHand) {
        System.out.println(participantHand.name() + CARD_TEXT + String.join(JOIN_DELIMITER, participantHand.cards()));
    }

    public static void printDealerCardDrawMessage() {
        System.out.println(DEALER_DRAW_ONE_CARD_TEXT);
        printNewLine();
    }

    public static void printParticipantHandWithScore(ParticipantCurrentHandResponse dealerResult, List<ParticipantCurrentHandResponse> players) {
        printPlayerHandWithScore(dealerResult);
        players.forEach(OutputView::printPlayerHandWithScore);
        printNewLine();
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printProfitResult(ParticipantProfitResponse dealerProfit, List<ParticipantProfitResponse> playersProfit) {
        System.out.println(FINAL_RESULT_TEXT);
        printProfitResult(dealerProfit);
        printPlayersProfit(playersProfit);
    }

    private static void printInitDeckDrawMessage(List<String> players) {
        System.out.println(DEALER_INIT_DECK_TEXT + String.join(JOIN_DELIMITER, players) + INIT_DECK_TEXT);
    }

    private static void printDealerInitDeck(String firstCard) {
        System.out.println(DEALER_CARD_TEXT + firstCard);
    }

    private static void printPlayersCurrentDeck(List<ParticipantCurrentHandResponse> players) {
        players.forEach(OutputView::printPlayerCurrentDeck);
    }

    private static void printPlayerHandWithScore(ParticipantCurrentHandResponse participantResult) {
        System.out.println(
                participantResult.name() + CARD_TEXT + String.join(JOIN_DELIMITER, participantResult.cards()) + SCORE_TEXT + participantResult.score());
    }

    private static void printPlayersProfit(List<ParticipantProfitResponse> playersProfit) {
        playersProfit.forEach(OutputView::printProfitResult);
    }

    private static void printProfitResult(ParticipantProfitResponse profitResult) {
        System.out.println(profitResult.name() + ": " + profitResult.profit());
    }

}
