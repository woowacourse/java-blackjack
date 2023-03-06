package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.Result;
import blackjack.dto.DealerCardsScoreResponse;
import blackjack.dto.DealerPlayerResultResponse;
import blackjack.dto.PlayerNameCardsResponse;
import blackjack.dto.PlayerNameCardsScoreResponse;

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
    private static final String FINAL_RESULT = "## 최종 승패";

    public void showError(String message) {
        printMessage(ERROR_HEADER + message);
    }

    public void showInitStatus(List<String> playerNames) {
        printMessage(LINE_SEPARATOR);
        String names = String.join(JOINER, playerNames);
        String message = String.format(CARD_SETUP_MESSAGE, names);

        printMessage(message);
    }

    public void showDealerFirstCard(Card dealerFirstCard) {
        System.out.println(DEALER_NAME + DELIMITER + mapCard(dealerFirstCard));
    }

    public void showPlayerNameCards(PlayerNameCardsResponse playerNameCards) {
        String name = playerNameCards.getName();
        List<Card> cards = playerNameCards.getCards();
        printMessage(name + CARD + DELIMITER + convertCard(cards));
    }

    public void showDealerDrawPossible() {
        printMessage(LINE_SEPARATOR);
        printMessage(DEALER_POSSIBLE_MESSAGE);
    }

    public void showDealerDrawImpossible() {
        printMessage(LINE_SEPARATOR);
        printMessage(DEALER_IMPOSSIBLE_MESSAGE);
    }

    public void showTotalScoreDealer(DealerCardsScoreResponse dealerCardsScore) {
        List<Card> cards = dealerCardsScore.getCards();
        int score = dealerCardsScore.getScore();
        printMessage(LINE_SEPARATOR);
        printMessage(DEALER_NAME + DELIMITER + convertCard(cards) + RESULT_MESSAGE + score);
    }

    public void showTotalScorePlayer(PlayerNameCardsScoreResponse playerNameCardsScore) {
        String name = playerNameCardsScore.getName();
        List<Card> cards = playerNameCardsScore.getCards();
        int score = playerNameCardsScore.getScore();
        printMessage(name + DELIMITER + convertCard(cards) + RESULT_MESSAGE + score);
    }

    public void showAllPlayerNameCards(List<PlayerNameCardsResponse> allPlayerNameAndCards) {
        for (PlayerNameCardsResponse playerNameAndCards : allPlayerNameAndCards) {
            showPlayerNameCards(playerNameAndCards);
        }
    }

    public void showAllPlayerNameCardsScore(List<PlayerNameCardsScoreResponse> allPlayerNameCardsScore) {
        for (PlayerNameCardsScoreResponse playerNameCardsScore : allPlayerNameCardsScore) {
            showTotalScorePlayer(playerNameCardsScore);
        }
    }

    public void showTotalResult(DealerPlayerResultResponse dealerPlayerResult) {
        showDealerResult(dealerPlayerResult.getDealerResult());
        showAllPlayerResult(dealerPlayerResult.getPlayerResult());
    }

    private void showDealerResult(Map<Result, Integer> results) {
        printMessage(LINE_SEPARATOR);
        printMessage(FINAL_RESULT);
        printMessage(DEALER_NAME + DELIMITER + ResultMapper.getDealerResult(results));
    }

    private void showAllPlayerResult(Map<String, Result> playerResult) {
        for (String name : playerResult.keySet()) {
            showPlayerResult(name, playerResult.get(name));
        }
    }

    private void showPlayerResult(String name, Result result) {
        printMessage(name + DELIMITER + ResultMapper.map(result));
    }

    private String convertCard(List<Card> inputCards) {
        return inputCards.stream()
                .map(this::mapCard)
                .collect(Collectors.joining(JOINER));
    }

    private String mapCard(Card card) {
        return NumberMapper.map(card.getNumber()) + SymbolMapper.map(card.getSymbol());
    }

    private void printMessage(String message) {
        System.out.println(message);
    }
}
