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

    // 카드를 한 장 추가한다.
    public void addCard(Card card) {
        cards.add(card);
    }

    // 핸즈가 가진 카드들의 점수를 계산한다.
    // 에이스 개수가 1개 이상이면서 베이스 스코어가 10점 이하이면 10을 추가로 더해준다
    public int calculateTotalScore() {
        int baseScore = this.cards.stream()
                .mapToInt(Card::getDefaultScore)
                .sum();

        boolean hasAce = cards.stream()
                .anyMatch(Card::isAce);

        if (hasAce && baseScore <= 11) {
            baseScore += 10;
        }

        return baseScore;
    }

    //총 점수가 인자로 넘겨받은 점수보다 큰지 여부를 반환한다.
    public boolean isTotalScoreOver(final int score) {
        return calculateTotalScore() > score;
    }

    // 모든 카드 반환
    public List<Card> getAllCard() {
        return List.copyOf(cards);
    }

    // 앞 면인 카드만 반환한다.
    public List<Card> getOpenedCards() {
        return List.copyOf(this.cards.stream()
                .filter(Card::isOpened)
                .toList());
    }
}
