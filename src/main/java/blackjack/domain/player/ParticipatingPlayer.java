package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.List;

public class ParticipatingPlayer extends AbstractPlayer {

    private ParticipatingPlayer(final String name, final Cards cards, final boolean turnState) {
        super(name, cards, turnState);
    }

    public static ParticipatingPlayer init(final String name, final List<Card> cards) {
        return new ParticipatingPlayer(name, new Cards(new ArrayList<>(cards)), true);
    }

    @Override
    public List<Card> initCards() {
        return List.copyOf(cards().subList(0, 2));
    }

    @Override
    boolean isEnd() {
        return super.calculateScore() > Cards.BLACK_JACK_TARGET_NUMBER;
    }
}
