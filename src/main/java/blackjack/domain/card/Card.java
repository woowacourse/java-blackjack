package blackjack.domain.card;

import java.util.List;

public interface Card {

    List<Integer> getScore();

    Rank getRank();

    Suit getSuit();
}
