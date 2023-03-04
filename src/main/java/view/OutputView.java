package view;

import domain.card.Card;
import domain.user.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    private static final String INPUT_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ASK_ONE_MORE_CARD_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String SETUP_COMPLETE_MESSAGE = "\n딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String DEALER_DRAW_RESULT_MESSAGE = "\n딜러는 16이하라 %d장의 카드를 더 받았습니다.\n\n";

    private static final String FINAL_RESULT_NOTICE_MESSAGE = "## 최종 승패";
    private static final String DEALER_FINAL_RESULT_MESSAGE = "딜러: %d승 %d패\n";
    private static final String WIN_COUNT_MESSAGE = ": 승";
    private static final String LOSE_COUNT_MESSAGE = ": 패";

    private static final String DRAW_RESULT_DELIMITER = ", ";
    private static final String NAME_CARD_DELIMITER = "카드 : ";
    private static final String CARD_SCORE_DELIMITER = " - 결과: ";

    public void printInputPlayerNameMessage() {
        System.out.println(INPUT_PLAYER_NAME_MESSAGE);
    }

    public void printSetUpResult(HashMap<String, List<Card>> setUpResult) {
        printSetUpCompleteMessage(setUpResult);
        printUserCards(setUpResult);
    }

    public void printAskOneMoreCardMessage(String name) {
        System.out.printf(ASK_ONE_MORE_CARD_MESSAGE, name);
    }

    public void printPlayerDrawResult(String name, List<Card> cards) {
        String cardSymbols = cards.stream()
                .map(Card::getSymbol)
                .collect(Collectors.joining(DRAW_RESULT_DELIMITER));

        System.out.println(name + NAME_CARD_DELIMITER + cardSymbols);
    }

    private void printSetUpCompleteMessage(HashMap<String, List<Card>> setUpResult) {
        String playerNames = String.join(DRAW_RESULT_DELIMITER,
                new ArrayList<>(setUpResult.keySet()).subList(1, setUpResult.size()));

        System.out.printf(SETUP_COMPLETE_MESSAGE, playerNames);
    }

    private void printUserCards(HashMap<String, List<Card>> setUpResult) {
        for (Entry<String, List<Card>> cardsByUser : setUpResult.entrySet()) {
            String name = cardsByUser.getKey();
            String cards = cardsByUser.getValue().stream()
                    .map(Card::getSymbol)
                    .collect(Collectors.joining(DRAW_RESULT_DELIMITER));

            System.out.println(name + NAME_CARD_DELIMITER + cards);
        }
        System.out.println();
    }

    public void printDealerDrawResult(int dealerDrawCount) {
        System.out.printf(DEALER_DRAW_RESULT_MESSAGE, dealerDrawCount);
    }

    public void printUsersCardResult(Map<User, List<Card>> userResult) {
        for (Entry<User, List<Card>> cardsByUser : userResult.entrySet()) {
            String name = cardsByUser.getKey().getName();
            String cards = cardsByUser.getValue().stream()
                    .map(Card::getSymbol)
                    .collect(Collectors.joining(DRAW_RESULT_DELIMITER));
            int score = cardsByUser.getKey().getScore();

            System.out.println(name + NAME_CARD_DELIMITER + cards + CARD_SCORE_DELIMITER + score);
        }
        System.out.println();
    }

    public void printFinalResult(Map<Boolean, Integer> dealerResult, Map<String, Boolean> userResult) {
        System.out.println(FINAL_RESULT_NOTICE_MESSAGE);
        System.out.printf(DEALER_FINAL_RESULT_MESSAGE, dealerResult.get(true), dealerResult.get(false));
        for (Entry<String, Boolean> userFinalResult : userResult.entrySet()) {
            if (userFinalResult.getValue()) {
                System.out.println(userFinalResult.getKey() + WIN_COUNT_MESSAGE);
                continue;
            }
            System.out.println(userFinalResult.getKey() + LOSE_COUNT_MESSAGE);
        }
    }
}
