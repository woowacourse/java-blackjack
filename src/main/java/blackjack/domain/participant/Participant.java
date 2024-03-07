package blackjack.domain.participant;

import blackjack.domain.Card;
import blackjack.domain.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    protected static final int BLACKJACK_SCORE = 21;
    protected static final int START_CARDS_SIZE = 2;

    private final List<Card> cards;

    protected Participant(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public final int calculateScore() {
        int score = getMaxScore();
        int cardIndex = 0;
        while (score > BLACKJACK_SCORE && cardIndex < cards.size()) {
            Card card = cards.get(cardIndex);
            score = score + card.getMinScore() - card.getMaxScore();
            cardIndex++;
        }
        return score;
    }

    private int getMaxScore() {
        return cards.stream()
                .mapToInt(Card::getMaxScore)
                .sum();
    }

    public final boolean isDrawable() {
        return calculateScore() <= getMaxDrawableScore();
    }

    public final boolean isBusted() {
        return calculateScore() > BLACKJACK_SCORE;
    }

    public void drawStartCards(Deck deck) {
        if (!cards.isEmpty()) {
            throw new IllegalStateException("이미 시작 카드를 뽑았습니다.");
        }
        for (int i = 0; i < START_CARDS_SIZE; i++) {
            add(deck.draw());
        }
    }

    //TODO : 시작 카드를 뽑은 이후에 호출 가능해야 한다.
    public final void add(Card card) {
        if (!isDrawable()) {
            throw new IllegalStateException("더 이상 카드를 추가할 수 없습니다.");
        }
        cards.add(card);
    }

    public final List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    protected abstract int getMaxDrawableScore();
}
