package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.*;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import blackjack.domain.card.Card;

public final class Cards {

    private static final int ACE_BIG = 11;
    private static final int MAX_HIT_SCORE = 21;
    private static final int INIT_NUMBER_OF_CARDS = 2;

    private final Set<Card> value;

    public Cards(Set<Card> value) {
        this.value = new LinkedHashSet<>(value);
    }

    public Cards add(Card card) {
        Set<Card> newCards = new LinkedHashSet<>(value);
        newCards.add(card);
        return new Cards(newCards);
    }

    public boolean isBlackjack() {
        return isReadyToStop() && isEndInit();
    }

    public boolean isReadyToStop() {
        return sum() == MAX_HIT_SCORE;
    }

    public boolean isEndInit() {
        return size() == INIT_NUMBER_OF_CARDS;
    }

    public boolean isBust() {
        return sum() > MAX_HIT_SCORE;
    }

    public int sum() {
        return sumWithAce(value.stream()
            .mapToInt(Card::getNumberValue)
            .sum());
    }

    private int sumWithAce(int sum) {
        if (sum <= ACE_BIG && hasAce()) {
            sum += (ACE_BIG - ACE.getValue());
        }
        return sum;
    }

    private boolean hasAce() {
        return value.stream()
            .anyMatch(Card::isAce);
    }

    public Card findFirst() {
        return value.stream()
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("카드가 한 장도 없습니다."));
    }

    public int size() {
        return value.size();
    }

    public Set<Card> getValue() {
        return new LinkedHashSet<>(value);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
