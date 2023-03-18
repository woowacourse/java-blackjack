package blackjack.domain.card;

import java.util.List;

@FunctionalInterface
public interface Shuffler {

    List<Card> createDeck();
}
