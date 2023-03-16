package blackjack.domain.participant.player;

import static blackjack.domain.participant.dealer.Dealer.DEALER_NAME;
import static blackjack.domain.participant.player.AddCardOrNot.NO;

import blackjack.domain.Money;
import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Hand;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;

public class Player extends Participant {

    private final BetAmount betAmount;

    public Player(Name name, BetAmount betAmount) {
        super(name, new Hand());
        if (name.getValue().equals(DEALER_NAME)) {
            throw new IllegalArgumentException("플레이어의 이름은 '딜러'일 수 없습니다.");
        }
        this.betAmount = betAmount;
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

    public Money getBetAmount() {
        return betAmount.getAmount();
    }
}
