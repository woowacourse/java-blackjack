package blackjack.model.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private static final int START_CARD_COUNT = 2;
    private static final int FINISH_SCORE = 21;

    private final List<Card> values;

    public Cards() {
        this.values = new ArrayList<>();
    }

    public Cards(final List<Card> cardGroup) {
        this.values = new ArrayList<>(cardGroup);
    }

    public Cards add(final Card card) {
        values.add(card);
        return new Cards(values);
    }

    public boolean isBlackjack() {
        return canHit() && isFinishScore();
    }

    public boolean canHit() {
        return values.size() == START_CARD_COUNT;
    }

    public boolean isFinishScore() {
        return sumScore() == FINISH_SCORE;
    }

    public int sumScore() {
        final List<CardNumber> cardNumbers = values.stream()
                .map(Card::getNumber)
                .collect(Collectors.toUnmodifiableList());
        return CardNumber.calculateScore(cardNumbers);
    }

    public boolean isBust() {
        return sumScore() > FINISH_SCORE;
    }

    public List<String> getCardNames() {
        return values.stream()
                .map(Card::getNumberAndSymbol)
                .collect(Collectors.toUnmodifiableList());
    }
}
