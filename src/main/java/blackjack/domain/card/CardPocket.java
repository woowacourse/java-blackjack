package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardPocket {

    private static final int BUST_SCORE = 21;
    private static final int BLACKJACK_SCORE = 21;
    private static final int VALUE_ACE = 10;
    private final List<Card> cards;
    private int cachedScore;

    private CardPocket(final List<Card> cards) {
        validateCardPocket(cards);
        this.cards = new ArrayList<>(cards);
    }

    public static CardPocket empty() {
        return new CardPocket(new ArrayList<>());
    }

    private void validateCardPocket(final List<Card> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("카드에 null 값이 들어갈 수 없습니다");
        }
    }

    public void addCard(final Card card) {
        cards.add(card);
        calculateCurrentScore();
    }

    private void calculateCurrentScore() {
        final int countOfAce = countAce();
        int scoreOfCards = calculateMinimumScore();
        for (int i = 0; i < countOfAce; i++) {
            scoreOfCards = calculateAceScore(scoreOfCards);
        }
        cachedScore = scoreOfCards;
    }

    public int calculateScore() {
        // 캐싱된 값이 없는 경우에 계산 같은 작업이 없이, 그냥 반환하고 끝 처럼 보이는데요 메서드가 괜찮아보이시나요?
        //아니면 계산하는 메서드를 호출하는 시늉이라도 주는게 좋을까요?
        return cachedScore;
        // return calculateCurrentScore(); 같은 느낌으로 주석을 남겨두는 느낌은 어떤가요?
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int calculateMinimumScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int calculateAceScore(final int score) {
        if (score + VALUE_ACE > BUST_SCORE) {
            return score;
        }
        return score + VALUE_ACE;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && calculateScore() == BLACKJACK_SCORE;
    }
}
