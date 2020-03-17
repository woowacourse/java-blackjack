package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        List<Type> types = this.cards.stream()
                .map(Card::getType)
                .sorted((Comparator.comparingInt(Type::getPoint)))
                .collect(Collectors.toList());

        int score = 0;
        for (Type type : types) {
            score += type.getPointUsingPreviousScore(score);
        }
        return score;
    }

    public int size() {
        return this.cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }
}
