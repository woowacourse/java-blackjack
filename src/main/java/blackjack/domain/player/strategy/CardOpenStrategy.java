package blackjack.domain.player.strategy;


import blackjack.domain.card.Card;
import java.util.List;

public interface CardOpenStrategy {
    List<Card> getCardsWithStrategy(List<Card> cards);
}
