package blackjack.view;

import blackjack.dto.*;

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
    private static final String DEALER_IMPOSSIBLE_MESSAGE = "딜러는 17이상이라 카드를 받지 못합니다.";
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

    public void showDealerFirstCard(CardDTO dealerFirstCard) {
        System.out.println(DEALER_NAME + DELIMITER + mapCard(dealerFirstCard));
    }

    public void showPlayerNameHand(PlayerNameHandResponse playerNameHand) {
        String name = playerNameHand.getName();
        List<CardDTO> hand = playerNameHand.getHand();
        printMessage(name + CARD + DELIMITER + convertCard(hand));
    }

    public void showDealerDrawPossible() {
        printMessage(LINE_SEPARATOR);
        printMessage(DEALER_POSSIBLE_MESSAGE);
    }

    public void showDealerDrawImpossible() {
        printMessage(LINE_SEPARATOR);
        printMessage(DEALER_IMPOSSIBLE_MESSAGE);
    }

    public void showTotalScoreDealer(DealerHandScoreResponse dealerHandScore) {
        List<CardDTO> hand = dealerHandScore.getHand();
        int score = dealerHandScore.getScore();
        printMessage(LINE_SEPARATOR);
        printMessage(DEALER_NAME + DELIMITER + convertCard(hand) + RESULT_MESSAGE + score);
    }

    public void showTotalScorePlayer(PlayerNameHandScoreResponse playerNameHandScore) {
        String name = playerNameHandScore.getName();
        List<CardDTO> hand = playerNameHandScore.getHand();
        int score = playerNameHandScore.getScore();
        printMessage(name + DELIMITER + convertCard(hand) + RESULT_MESSAGE + score);
    }

    public void showAllPlayerNameHand(List<PlayerNameHandResponse> allPlayerNameAndHand) {
        for (PlayerNameHandResponse playerNameAndHand : allPlayerNameAndHand) {
            showPlayerNameHand(playerNameAndHand);
        }
    }

    public void showAllPlayerNameHandScore(List<PlayerNameHandScoreResponse> allPlayerNameHandScore) {
        for (PlayerNameHandScoreResponse playerNameHandScore : allPlayerNameHandScore) {
            showTotalScorePlayer(playerNameHandScore);
        }
    }

    public void showTotalResult(DealerPlayerResultResponse dealerPlayerResult) {
        showDealerResult(dealerPlayerResult.getDealerResult());
        showAllPlayerResult(dealerPlayerResult.getPlayerResult());
    }

    private void showDealerResult(Double dealerResult) {
        printMessage(LINE_SEPARATOR);
        printMessage(FINAL_RESULT);
        printMessage(DEALER_NAME + DELIMITER + dealerResult);
    }

    private void showAllPlayerResult(Map<String, Double> playerResult) {
        for (String name : playerResult.keySet()) {
            showPlayerResult(name, playerResult.get(name));
        }
    }

    private void showPlayerResult(String name, Double result) {
        printMessage(name + DELIMITER + result);
    }

    private String convertCard(List<CardDTO> inputHand) {
        return inputHand.stream()
                .map(this::mapCard)
                .collect(Collectors.joining(JOINER));
    }

    private String mapCard(CardDTO card) {
        return NumberMapper.map(card.getDenomination()) + SuitMapper.map(card.getSuit());
    }

    private void printMessage(String message) {
        System.out.println(message);
    }
}
