package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.*;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import blackjack.domain.card.Card;

public final class Cards {

    private static final int ACE_BIG = 11;

    private final Set<Card> value;

    public Cards(Set<Card> value) {
        this.value = new LinkedHashSet<>(value);
    }

    public Cards add(Card card) {
        Set<Card> newCards = new LinkedHashSet<>(value);
        newCards.add(card);
        return new Cards(newCards);
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

    public Set<Card> getValue() {
        return new LinkedHashSet<>(value);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }

    public boolean isBlackjack() {
        return sum() == 21 && value.size() == 2;
    }

    public int size() {
        return value.size();
    }

    public boolean isBust() {
        return sum() > 21;
    }
}
