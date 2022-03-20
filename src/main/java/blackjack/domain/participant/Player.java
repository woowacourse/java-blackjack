package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.state.State;
import java.util.List;

public class Player {

    private final Participant participant;
    private final Money bettingMoney;

    private Player(Participant participant, Money bettingMoney) {
        this.participant = participant;
        this.bettingMoney = bettingMoney;
    }

    public static Player from(String name, String money) {
        return new Player(new Participant(name), new Money(money));
    }

    public Participant getParticipant() {
        return participant;
    }

    public boolean isFinished() {
        return participant.isFinished();
    }

    public void hit(Card card) {
        participant.hit(card);
    }

    public void stay() {
        participant.stay();
    }

    public int calculateProfit(Dealer dealer) {
        State state = participant.getState();
        double earningRate = state.earningRate(dealer.getState());
        return bettingMoney.multiple(earningRate);
    }

    public boolean isReady() {
        return participant.isReady();
    }

    public String getName() {
        return participant.getName();
    }

    public List<Card> getCards() {
        return participant.getCards();
    }
}
