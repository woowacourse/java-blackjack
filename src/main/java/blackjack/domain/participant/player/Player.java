package blackjack.domain.participant.player;

import static blackjack.controller.AddCardOrNot.NO;
import static blackjack.domain.game.WinningResult.LOSE;
import static blackjack.domain.game.WinningResult.TIE;
import static blackjack.domain.game.WinningResult.WIN;
import static blackjack.domain.participant.dealer.Dealer.DEALER_NAME;

import blackjack.controller.AddCardOrNot;
import blackjack.domain.deck.Deck;
import blackjack.domain.game.WinningResult;
import blackjack.domain.participant.Hand;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Score;

public class Player extends Participant {
    private WinningResult winningResult;

    public Player(Name name) {
        super(name, new Hand());
        if (name.getValue().equals(DEALER_NAME)){
            throw new IllegalArgumentException("플레이어의 이름은 '딜러'일 수 없습니다.");
        }
    }

    public void combat(Participant dealer) {
        WinningResult winningResult = decideResultByComparingTo(dealer);
        winningResult.applyTo(this, dealer);
    }

    private WinningResult decideResultByComparingTo(Participant dealer) {
        Score score = calculateScore();
        Score otherScore = dealer.calculateScore();
        if (isBust()) {
            return LOSE;
        }
        if (score.isHigherThan(otherScore) || dealer.isBust()) {
            return WIN;
        }
        if (score.equals(otherScore)) {
            return TIE;
        }
        return LOSE;
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
        while (!isBust()) {
            AddCardOrNot addCardOrNot = cardDecisionStrategy.decide(this);
            if (addCardOrNot.equals(NO)) {
                break;
            }
            hit(deck.drawCard());
            cardDisplayStrategy.display(this);
        }
    }
}
