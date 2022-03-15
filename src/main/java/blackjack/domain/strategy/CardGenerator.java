package blackjack.domain.strategy;

import blackjack.domain.Card;
import java.util.List;

public interface CardGenerator {
    List<Card> generate();
}
