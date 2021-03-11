package blackjack.domain.player.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import java.util.List;

public interface State {
    boolean drawable();

    State draw(Card card);

    Score score();

    int winningMoney(int batMoney);

    List<Card> cards();
}
