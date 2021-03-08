package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static blackjack.controller.BlackJackGame.BLACKJACK_NUMBER;

public class Cards {
    private static final String NO_REMAIN_CARD_ERROR_MESSAGE = "남은 카드가 없습니다.";
    private static final int REMAIN_ACE_COUNT = 10;
    public static final int TOP_CARD = 0;

    private final List<Card> cards;

    public Cards() {
        this(Collections.emptyList());
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card draw() {
        if (cards.size() == 0) {
            throw new IndexOutOfBoundsException(NO_REMAIN_CARD_ERROR_MESSAGE);
        }
        return cards.remove(TOP_CARD);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int calculateScore() {
        int score = cards.stream()
                .mapToInt(Card::score)
                .sum();
        long aceCount = cards.stream()
                .filter(Card::isAce)
                .count();
        for (int i = 0; i < aceCount; i++) {
            score = plusRemainAceScore(score);
        }
        return score;
    }

    private int plusRemainAceScore(int score) {
        if (score + REMAIN_ACE_COUNT <= BLACKJACK_NUMBER) {
            score += REMAIN_ACE_COUNT;
        }
        return score;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
