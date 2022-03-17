package blackjack.domain.betting;

import blackjack.domain.player.Participant;

public class Betting {

    private final Participant participant;
    private final Money money;

    public Betting(Participant participant, Money money) {
        this.participant = participant;
        this.money = money;
    }

}
