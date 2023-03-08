package domain.player;

import domain.card.Cards;

public final class Participant extends Player {

    private final BetAmount betAmount;

    private Participant(final String name, final BetAmount betAmount, final Cards cards) {
        super(name, cards);
        this.betAmount = betAmount;
    }

    public static Participant from(final String name, final BetAmount betAmount) {
        return new Participant(name, betAmount, new Cards());
    }

    public int getBetValue() {
        return betAmount.getValue();
    }

    @Override
    public boolean isInPlaying() {
        return HandsState.from(super.getScore()) == HandsState.IN_PLAY;
    }
}
