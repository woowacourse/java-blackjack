package blackjack.view;

import blackjack.dto.*;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String ERROR_HEADER = "[ERROR] ";
    private static final String CARD_SETUP_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_NAME = "딜러";
    private static final String DELIMITER = ": ";
    private static final String JOINER = ", ";
    private static final String LINE_SEPARATOR = "";
    private static final String CARD = "카드";
    private static final String DEALER_POSSIBLE_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String RESULT_MESSAGE = " - 결과: ";
    private static final String FINAL_RESULT = "## 최종 수익";

    public void showError(String message) {
        printMessage(ERROR_HEADER + message);
    }

    public void showInitStatus(List<String> playerNames) {
        printMessage(LINE_SEPARATOR);
        String names = String.join(JOINER, playerNames);
        String message = String.format(CARD_SETUP_MESSAGE, names);

        printMessage(message);
    }

    public void showDealerFirstCard(CardDto dealerFirstCard) {
        System.out.println(DEALER_NAME + DELIMITER + mapCard(dealerFirstCard));
    }

    public void showPlayerNameHand(PlayerNameHandDto playerNameHand) {
        String name = playerNameHand.getName();
        List<CardDto> hand = playerNameHand.getHand();
        printMessage(name + CARD + DELIMITER + convertCard(hand));
    }

    public void showDealerDrawPossible() {
        printMessage(LINE_SEPARATOR);
        printMessage(DEALER_POSSIBLE_MESSAGE);
    }

    public void showTotalScoreDealer(DealerHandScoreDto dealerHandScore) {
        List<CardDto> hand = dealerHandScore.getHand();
        int score = dealerHandScore.getScore();
        printMessage(LINE_SEPARATOR);
        printMessage(DEALER_NAME + DELIMITER + convertCard(hand) + RESULT_MESSAGE + score);
    }

    public void showTotalScorePlayer(PlayerNameHandScoreDto playerNameHandScore) {
        String name = playerNameHandScore.getName();
        List<CardDto> hand = playerNameHandScore.getHand();
        int score = playerNameHandScore.getScore();
        printMessage(name + DELIMITER + convertCard(hand) + RESULT_MESSAGE + score);
    }

    public void showAllPlayerNameHand(List<PlayerNameHandDto> allPlayerNameAndHand) {
        for (PlayerNameHandDto playerNameAndHand : allPlayerNameAndHand) {
            showPlayerNameHand(playerNameAndHand);
        }
    }

    public void showAllPlayerNameHandScore(List<PlayerNameHandScoreDto> allPlayerNameHandScore) {
        for (PlayerNameHandScoreDto playerNameHandScore : allPlayerNameHandScore) {
            showTotalScorePlayer(playerNameHandScore);
        }
    }

    public void showTotalResult(ResultDto dealerPlayerResult) {
        showDealerResult(dealerPlayerResult.getDealerResult());
        showAllPlayerResult(dealerPlayerResult.getPlayerResult());
    }

    private void showDealerResult(Double dealerResult) {
        printMessage(LINE_SEPARATOR);
        printMessage(FINAL_RESULT);
        showPlayerResult(DEALER_NAME, dealerResult);
    }

    private void showAllPlayerResult(Map<String, Double> playerResult) {
        for (String name : playerResult.keySet()) {
            showPlayerResult(name, playerResult.get(name));
        }
    }

    private void showPlayerResult(String name, Double result) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        printMessage(name + DELIMITER + decimalFormat.format(result));
    }

    private String convertCard(List<CardDto> inputHand) {
        return inputHand.stream()
                .map(this::mapCard)
                .collect(Collectors.joining(JOINER));
    }

    private String mapCard(CardDto card) {
        return NumberMapper.map(card.getDenomination()) + SuitMapper.map(card.getSuit());
    }

    private void printMessage(String message) {
        System.out.println(message);
    }
}
