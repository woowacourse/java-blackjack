package blackjack.model.state;

import blackjack.model.cards.Card;
import blackjack.model.cards.Cards;
import blackjack.vo.Money;
import java.util.List;

public interface State {
    public State draw(Card card);

    public State drawCards(List<Card> cardsToAdd);

    public State stand();

    Money calculateProfit(Money betMoney);

    public Cards cards();
}
