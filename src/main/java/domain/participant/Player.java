package domain.participant;

import domain.card.Hand;

public class Player extends Participant {
    public Player(String name) {
        super(name);
    }

    public Player(Player player) {
        super(player.name, new Hand(player.hand));
    }

    public Player(String name, int betMoney) {
        super(name, betMoney);
    }
}
