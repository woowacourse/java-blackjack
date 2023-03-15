package blackjack.domain.player;

public class Gambler extends Player {

    public Gambler(final String name) {
        super(Name.createPlayerName(name), new Hand());
    }

    @Override
    public boolean isDrawable() {
        return hand.isPlayable();
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public Result compareHandTo(final Hand hand) {
        return this.hand.compare(hand);
    }
}
