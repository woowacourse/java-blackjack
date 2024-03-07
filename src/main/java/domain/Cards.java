package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Cards {

    private static final int INIT_CARD_SIZE = 2;

    protected final Participant participant;
    protected final List<Card> cards;

    Cards(Participant participant, List<Card> cards) {
        this.participant = participant;
        validate(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validate(List<Card> cards) {
        if (cards.size() != INIT_CARD_SIZE) {
            throw new IllegalArgumentException("처음 지급받는 카드는 두 장이어야 합니다.");
        }
    }

    public abstract boolean canDraw();

    public List<Card> add(Card card) {
        cards.add(card);
        return List.copyOf(cards);
    }

    public int sum() {
        return cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }

    public void draw() {
        cards.add(new Card(new Random().nextInt(10) + 1, Shape.CLUB));
    }
}
