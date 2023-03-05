package blackjack.view;

import blackjack.domain.participant.Dealer;

import java.util.HashMap;
import java.util.List;

public class OutputView {
    private static final String CHANGE_LINE = "\n";
    private static final String DEALER_ANDE_PLAYER_DELIMITER = "와 ";
    private static final String PLAYERS_DELIMITER = ",";
    private static final String GIVE_TWO_CARD_MASSAGE = "에게 두장을 나누었습니다.";
    private static final String NOTICE_TOTAL_SCORE_UNDER_SIXTEEN = "는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String RESULT_DELIMITER = " - 결과 : ";
    private static final String FINAL_RESULT_MASSAGE = "## 최종 승패";
    private static final String DEALER_DELIMITER = " : ";
    private static final String WIN_COUNT_MASSAGE = "승 ";
    private static final String TIE_COUNT_MASSAGE = "무 ";
    private static final String LOSE_COUNT_MASSAGE = "패";
    private static final String PLAYER_SCORE_DELIMITER = " : ";
    private static final String PLAYER_DELIMITER = ", ";
    private static final String DEALER_NAME = "딜러";

    public void outputSplitMessage( final List<String> players) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CHANGE_LINE + DEALER_NAME + DEALER_ANDE_PLAYER_DELIMITER);
        stringBuilder.append(String.join(PLAYERS_DELIMITER, players) + GIVE_TWO_CARD_MASSAGE);
        System.out.println(stringBuilder);
    }
    public void outputParticipantCards(HashMap<String,List<String>> playerCards){
        outputPlayerCard(DEALER_NAME,playerCards.get(DEALER_NAME));
        changeLine();
        for(final String name:playerCards.keySet()){
            outputPlayerCard(name,playerCards.get(name));
        }
    }
    public void outputPlayerCard(final String name, final List<String> cards) {
        System.out.print(name + PLAYER_SCORE_DELIMITER + String.join(PLAYER_DELIMITER, cards));
    }

    public void outputDealerDrawCard(final String name) {
        System.out.println(CHANGE_LINE + name + NOTICE_TOTAL_SCORE_UNDER_SIXTEEN);
    }

    public void outputScore(final int score) {
        System.out.println(RESULT_DELIMITER + score);
    }

    public void outputResult() {
        System.out.println(FINAL_RESULT_MASSAGE);
    }

    public void outputDealerResult(final String name, final int win, final int tie, final int lose) {
        System.out.println(name + DEALER_DELIMITER
                + win + WIN_COUNT_MASSAGE
                + tie + TIE_COUNT_MASSAGE
                + lose + LOSE_COUNT_MASSAGE);
    }

    public void outputPlayerResult(final String name, final String result) {
        System.out.println(name + PLAYER_SCORE_DELIMITER + result);
    }

    public void changeLine() {
        System.out.println();
    }
}
