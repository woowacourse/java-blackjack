import java.util.List;

public class BlackjackHand {
    private static final int BLACKJACK_MAX_SCORE = 21;
    private static final int ACE_PROFIT_VALUE = 10;
    private static final int MIN_SIZE = 2;

    private List<Card> hands;

    public BlackjackHand(List<Card> cards) {
        validateCardsSize(cards);
        hands = cards;
    }

    private void validateCardsSize(List<Card> cards) { //테스트 작성
        if (cards.size() != MIN_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.HANDS_CARDS_SIZE.getMessage());
        }
    }

    public int getTotalScore() {    // 테스트
        int totalScore = hands.stream()
                .map(Card::getScore)
                .reduce(Integer::sum)
                .get();

        if (totalScore <= BLACKJACK_MAX_SCORE - ACE_PROFIT_VALUE) {
            return totalScore + ACE_PROFIT_VALUE;
        }

        return totalScore;
    }


    /* Todo:
        카드의 숫자를 반환한다.(J/Q/K 는 10) 나머지는 숫자 그대로(A제외)
        카드의 합을 반환한다.(A는 A의 값을 요청한다)
        버스트인지 확인한다(카드의 합을 요청해서 버스트인지 체크한다)
     */

    // 버스트 확인
    public boolean isBurst(int totalScore) {
        if (totalScore < 0) {
            throw new IllegalArgumentException("점수 총합이 잘못 됨");
        }
        return totalScore > BLACKJACK_MAX_SCORE;
    }
}
