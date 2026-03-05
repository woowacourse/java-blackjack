package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Hands {

    private final List<Card> cards;

    public Hands(List<Card> cards) {
        this.cards = cards;
    }

    public static Hands empty() {
        return new Hands(new ArrayList<>());
    }

    //카드를 한 장 추가한다.
    public void addCards(List<Card> other) {
        if (other == null || other.isEmpty()) {
            throw new IllegalArgumentException("card가 null입니다.");
        }

        this.cards.addAll(other);
    }

    // 핸즈가 가진 카드들의 점수를 계산한다.
    // 에이스 개수가 1개 이상이면서 베이스 스코어가 10점 이하이면 10을 추가로 더해준다
    public int calculateTotalScore() {
        int baseScore = this.cards.stream()
                .mapToInt(card -> card.rank().getDefaultScore())
                .sum();

        boolean hasAce = cards.stream()
                .anyMatch(card -> card.rank() == Rank.ACE);

        if (hasAce && baseScore <= 11) {
            baseScore += 10;
        }

        return baseScore;
    }

    //총 점수가 인자로 넘겨받은 점수보다 큰지 여부를 반환한다.
    public boolean isTotalScoreOver(final int score) {
        return calculateTotalScore() > score;
    }

    // 첫 번째 카드 반환
    public Card getFirstCard() {
        return this.cards.getFirst();
    }
}
