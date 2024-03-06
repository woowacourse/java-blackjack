package blackjack.domain.dto;

import blackjack.domain.card.Card;
import java.util.List;

public class DealerDto {

    private final List<Card> values;

    public DealerDto(final List<Card> values) {
        this.values = values;
    }

    public List<Card> getValues() {
        return values;
    }
}
