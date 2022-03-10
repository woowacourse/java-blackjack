package blackjack.domain.player;

import blackjack.domain.GameOutcome;
import blackjack.domain.card.Card;
import java.util.List;

public interface Player {

    List<Card> firstDrawCard();

    void draw(final Card card);

    boolean canDraw();

    void endTurn();

    int calculateResultScore();

    GameOutcome fightResult(final Player player);

    List<Card> cards();

    String getName();
}
