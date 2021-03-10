package blackjack.domain.participant;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.CardDeck;
import blackjack.domain.participant.state.Init;
import blackjack.domain.participant.state.Score;
import blackjack.domain.participant.state.State;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    protected State state;

    protected Participant() {
    }

    public void initDraw(final CardDeck cardDeck) {
        this.state = Init.draw(cardDeck.draw(), cardDeck.draw());
    }

    public void draw(final Card card) {
        this.state = this.state.draw(card);
    }

    public void stay() {
        this.state = this.state.stay();
    }

    public boolean isFinished() {
        return this.state.isFinished();
    }

    public Score score() {
        return this.state.score();
    }

    public int getScoreToInt(){
        return score().getValue();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.state.cards());
    }

    public boolean isBlackjack() {
        return score().isBlackjack();
    }

    public boolean isBust(){
        return score().isBust();
    }
}
