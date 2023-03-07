package blackjack.domain.participant.player;

import static blackjack.controller.AddCardOrNot.NO;

import blackjack.controller.AddCardOrNot;
import blackjack.domain.deck.Deck;
import blackjack.domain.game.WinningResult;
import blackjack.domain.participant.Hand;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;

public class Player extends Participant {
    private WinningResult winningResult;

    public Player(Name name) {
        super(name, new Hand());
    }

    public void win() {
        winningResult = WinningResult.WIN;
    }

    public void lose() {
        winningResult = WinningResult.LOSE;
    }

    public void tie() {
        winningResult = WinningResult.TIE;
    }

    public WinningResult getResult() {
        return winningResult;
    }

    public void hitAdditionalCardFrom(Deck deck, CardDecisionStrategy cardDecisionStrategy,
                                      CardDisplayStrategy cardDisplayStrategy) {
        while (!isBlackJack() && !isBust()) {
            AddCardOrNot addCardOrNot = cardDecisionStrategy.decide(this);
            if (addCardOrNot.equals(NO)) {
                break;
            }
            hit(deck.drawCard());
            cardDisplayStrategy.display(this);
        }
    }
}
