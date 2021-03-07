package blackjack.domain.player;


import blackjack.domain.card.Card;
import java.util.List;

public interface CardOpenStrategy {
    List<Card> getCards(List<Card> cards);
}
