package domain.status;

import domain.card.Card;
import domain.card.Cards;
import java.math.BigDecimal;

public interface Status {
    Status initialDraw(Cards cards);

    Status draw(Card card);

    Status selectStand();

    BigDecimal profitWeight();
}
