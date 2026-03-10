package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private final List<Card> cards;
    private int changeAvailableAceCount;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        changeAvailableAceCount = 0;
    }

    public static Cards createCards() {
        List<Card> cards = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            for (Number number : Number.values()) {
                cards.add(new Card(shape, number));
            }
        }
        return new Cards(cards);
    }

    public static void shuffleCards(Cards beforeShuffle) {
        Collections.shuffle(beforeShuffle.cards);
    }

    public void addCard(Card card) {
        if (card.getScore() == 11) {
            changeAvailableAceCount += 1;
        }
        cards.add(card);
    }

    public static Integer getCardsSize(Cards targetCards) {
        return targetCards.cards.size();
    }

    // TODO: 이게 거의 isBurst 처럼 동작 - canReceiveCard는 isBurst처럼
    public boolean canReceiveCard(int burstThreshold) {
        int sum = calculateScore();

        while (changeAvailableAceCount != 0 && isBurst(sum)) {
            sum -= 10;
            changeAvailableAceCount -= 1;
        }

        if (changeAvailableAceCount == 0 && isBurst(sum)) {
            return false;
        }

        if (isBurst(sum) || sum >= burstThreshold) {
            return false;
        }

        return true;
    }

    // TODO: 이건 그냥 21이 넘는지 판단
    private boolean isBurst(int score) {
        if (score > 21) {
            return true;
        }
        return false;
    }

    public int calculateScore() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getScore();
        }
        return sum;
    }

    public List<Card> getCards() {
        return cards.stream()
                .toList();
    }

    public List<String> getCardsInfo() {
        return cards.stream()
                .map(Card::getCardInfo)
                .toList();
    }

}
