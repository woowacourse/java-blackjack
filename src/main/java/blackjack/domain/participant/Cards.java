package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import blackjack.domain.PlayStatus;
import blackjack.domain.card.Card;

class Cards {
    private static final int ACE_BIG = 11;

    private final Set<Card> value;

    Cards(Set<Card> value) {
        this.value = new LinkedHashSet<>(List.copyOf(value));
    }

    void add(Card card) {
        value.add(card);
    }

    PlayStatus getStatus() {
        return PlayStatus.hitOrBust(sum());
    }

    int sum() {
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

    Card findFirst() {
        return value.stream()
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("카드가 한 장도 없습니다."));
    }

    Set<Card> getValue() {
        return Set.copyOf(value);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
