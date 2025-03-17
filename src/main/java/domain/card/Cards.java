package domain.card;

import domain.state.Bust;
import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Cards emptyCards() {
        return new Cards(new ArrayList<>());
    }

    public int computeOptimalSum() {
        List<Integer> candidates = new ArrayList<>();
        computeCandidates(0, 0, cards, candidates);

        return candidates.stream()
                .filter(sum -> sum <= Bust.BUST_THRESHOLD)
                .max(Integer::compareTo)
                .orElseGet(() -> candidates.stream()
                        .filter(sum -> sum > Bust.BUST_THRESHOLD)
                        .min(Integer::compareTo)
                        .orElseThrow(() -> new IllegalStateException("합을 계산할 카드가 없습니다.")));
    }

    private void computeCandidates(int cardIndex, int sum, List<Card> cards, List<Integer> candidates) {
        if (cardIndex == cards.size()) {
            candidates.add(sum);
            return;
        }

        for (int score : cards.get(cardIndex).scores()) {
            computeCandidates(cardIndex + 1, sum + score, cards, candidates);
        }
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void openedCards(int count) {
        while (count > 0) {
            Card willBeOpened = findNotOpenedCard();
            willBeOpened.open();
            count--;
        }
    }

    public Card findNotOpenedCard() {
        return cards.stream()
                .filter(card -> !card.isOpened())
                .findAny()
                .orElseThrow(() -> new IllegalStateException("오픈할 카드가 없습니다."));
    }

    public Cards openedCards() {
        return new Cards(
                cards.stream()
                        .filter(Card::isOpened)
                        .toList()
        );
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
