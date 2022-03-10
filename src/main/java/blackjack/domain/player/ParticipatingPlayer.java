package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class ParticipatingPlayer extends AbstractPlayer {

    private static final int FIRST_DRAW_CARD_SIZE = 2;

    private ParticipatingPlayer(final String name, final Cards cards, final boolean turnState) {
        super(name, cards, turnState);
    }

    public static ParticipatingPlayer createNewPlayer(final String name, final List<Card> cards) {
        return new ParticipatingPlayer(name, new Cards(cards), true);
    }

    @Override
    public List<Card> firstDrawCard() {
        return List.copyOf(cards().subList(0, FIRST_DRAW_CARD_SIZE));
    }

    @Override
    boolean isEnd() {
        return super.calculateScore() > Cards.BLACK_JACK_TARGET_NUMBER;
    }
}
