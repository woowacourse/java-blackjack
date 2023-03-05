package blackjack.domain.participant.player;

import static blackjack.controller.AddCardOrNot.NO;

import blackjack.controller.AddCardOrNot;
import blackjack.domain.deck.Deck;
import blackjack.domain.game.Result;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Hand;
import blackjack.domain.participant.Participant;
import java.util.function.Consumer;
import java.util.function.Function;

public class Player extends Participant {
    private Result result;

    public Player(Name name) {
        super(name, new Hand());
    }

    public void win() {
        result = Result.WIN;
    }

    public void lose() {
        result = Result.LOSE;
    }

    public void tie() {
        result = Result.TIE;
    }

    public Result getResult() {
        return result;
    }

    public void hitAdditionalCardFrom(Deck deck, Function<Name, AddCardOrNot> decideAddCardOrNot, Consumer<Player> showPlayerCards) {
        while(!isBust()) {
            AddCardOrNot addCardOrNot = decideAddCardOrNot.apply(name);
            if (addCardOrNot.equals(NO)) {
                break;
            }
            hit(deck.drawCard());
            showPlayerCards.accept(this);
        }
    }
}
