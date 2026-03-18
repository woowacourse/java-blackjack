package blackjack.model.card;

import blackjack.model.gameresult.GameResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Hand {

    private static final int BLACKJACK_SCORE = 21;
    private static final int ACE_SCORE_ONE = 1;
    private static final int ACE_SCORE_ELEVEN = 11;

    private final List<Card> cards;
    private HandState handState;

    public Hand() {
        this.cards = new ArrayList<>();
        this.handState = new Hit();
    }

    public GameResult judge(Hand otherHand) {
        return handState.judge(this, otherHand);
    }

    public void setState(HandState handState) {
        this.handState = handState;
    }

    public int size() {
        return cards.size();
    }

    public void draw(Card card) {
        handState.draw(this, card);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackjack() {
        return handState.isBlackjack();
    }

    public boolean isBust() {
        return handState.isBust();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int calculateTotalScore() {
        return totalScore(cards);
    }

    public int totalScore(List<Card> cards) {
        Map<Boolean, List<Card>> partitioned = cards.stream()
                .collect(Collectors.partitioningBy(card -> card.getRank() == Rank.ACE));

        List<Card> aceCards = partitioned.get(true);      // Ace인 카드들
        List<Card> nonAceCards = partitioned.get(false);// Ace가 아닌 카드들
        int totalScoreNonAce = nonAceCards.stream()
                .mapToInt(Card::score)
                .sum();

        return addAceScore(aceCards, totalScoreNonAce);
    }

    private int addAceScore(List<Card> aceCards, int totalScore) {
        for (int i = 0; i < aceCards.size(); i++) {
            totalScore += calculateAceScore(totalScore);
        }
        return totalScore;
    }

    private static int calculateAceScore(int totalScore) {
        if (totalScore + ACE_SCORE_ELEVEN > BLACKJACK_SCORE) {
            return ACE_SCORE_ONE;
        }
        return ACE_SCORE_ELEVEN;
    }
}
