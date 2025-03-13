package blackjack.object.gambler;

import blackjack.object.card.Card;
import java.util.List;

public class Dealer extends Gambler {
    public Dealer() {
        super(Name.getDealerName());
    }

    @Override
    public List<Card> getInitialCards() {
        List<Card> cards = getCards();
        Card firstCard = cards.getFirst();
        return List.of(firstCard);
    }
}
