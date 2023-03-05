package domain;

import java.util.List;

public final class Participant extends Player {

    public static final int CARD_RENEWAL_COUNT = 2;

    private Participant(final String name, final Cards cards) {
        super(name, cards);
    }

    public static Participant from(final String name) {
        return new Participant(name, new Cards());
    }

    @Override
    public boolean isInPlaying(boolean isHit){
        return isHit && HandsState.from(cards.calculateScore()) == HandsState.IN_PLAY;
    }

    @Override
    public List<Card> revealCards() {
        return cards.getCards().subList(0, CARD_RENEWAL_COUNT);
    }
}
