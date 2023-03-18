package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.participant.Hand;

import java.util.List;

/**
 *                                       -Player-                           -Dealer-
 *     +--------------+            +--------------------+            +--------------------+
 *     | InitialState |            | PlayerInitialState |----    ----| DealerInitialState |-----
 *     +------+-------+            +---------+----------+    |   |   +---------+----------+    |
 *            |                              |               |   |             |               |
 *            |                              |               |   |             |               |
 *            v                              v               |   |             v               |
 *     +------+-------+            +---------+----------+    |   |   +---------+----------+    |
 *     |  DrawState   |            |  PlayerDrawState   |    |   |   |  DealerDrawState   |    |
 *     +------+-------+            +---------+----------+    |   |   +---------+----------+    |
 *            |                              |               |   |             |               |
 *            |                              |               |   |             |               |
 *            v                              |               v   v             |               |
 *     +------+-------+                      |        +------+---+-------+     |               |
 *     |              |                      |        |  BlackjackState  |     |               |
 *     |              |                      v        +------------------+     v               |
 *     |              |                +----------------------------------------------+        |
 *     |  FinalState  |                |                                              |        |
 *     |              |                v                                              v        v
 *     |              |         +------+-------+                                   +--+--------+--+
 *     |              |         |  BustState   |                                   |  StandState  |
 *     +--------------+         +--------------+                                   +--------------+
 *
 */

public abstract class State {
    protected static final int BLACKJACK_NUMBER = 21;

    protected final Hand hand;

    public State(Hand hand) {
        this.hand = hand;
    }

    public abstract State draw(CardDeck cardDeck);

    public abstract boolean isFinished();

    public abstract boolean isStateOf(StateValue stateValue);

    public int getOptimizedScore() {
        return hand.getScore();
    }

    public List<Card> getHand() {
        return hand.getCards();
    }
}
