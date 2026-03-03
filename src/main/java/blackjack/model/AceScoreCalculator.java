package blackjack.model;

public class AceScoreCalculator {

    private static final int TARGET_SCORE = 21;
    private static final int ACE_SCORE_OPTION_1 = 1;
    private static final int ACE_SCORE_OPTION_2 = 11;

    /**
     * main 테스트
     * ACE 1 or 11 처리
     * <p>
     * 테스트 케이스: 1, 5, 10, 15, 20, 21
     * 예상 판단: 11, 11, 11, 1, 1, 1
     * 실제 결과: 11, 11, 11, 1, 1, 1
     */
    public int calculateAceScore(int currentScore) {
        int aceScore1 = currentScore + ACE_SCORE_OPTION_1;
        int aceScore2 = currentScore + ACE_SCORE_OPTION_2;

        if(Math.max(aceScore1, aceScore2) > TARGET_SCORE) {
            return Math.min(ACE_SCORE_OPTION_1, ACE_SCORE_OPTION_2);
        }
        if(aceScore1 > aceScore2) {
            return ACE_SCORE_OPTION_1;
        }
        return ACE_SCORE_OPTION_2;
    }
}
