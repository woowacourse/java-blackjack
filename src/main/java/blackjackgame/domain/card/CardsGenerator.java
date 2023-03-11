package blackjackgame.domain.card;

import java.util.List;

public interface CardsGenerator {
    List<Card> generate(int deckCount);
}
