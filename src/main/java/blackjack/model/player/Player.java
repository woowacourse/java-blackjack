package blackjack.model.player;

public class Player extends Participant {

    public Player(final String name) {
        super(name);
    }

    @Override
    public boolean isImpossibleHit() {
        return cards.sumScore() >= Participant.MAX_SCORE;
    }
}
