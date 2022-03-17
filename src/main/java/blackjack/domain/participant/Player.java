package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.state.PlayState;
import blackjack.domain.participant.state.finished.StandState;
import blackjack.domain.participant.validation.PlayerNameValidator;

public final class Player extends Participant {

    private final String name;

    private Player(final String name, final Deck deck) {
        super(deck);
        validatePlayerName(name);
        this.name = name;
    }

    private void validatePlayerName(final String name) {
        PlayerNameValidator.validateNameNotBlank(name);
    }

    public static Player readyToPlay(final String name, final Deck deck) {
        return new Player(name, deck);
    }

    public void betAmount(final int amount) {
        this.state = state.betAmount(amount);
    }

    public void drawCard(final Deck deck, final boolean needToDrawCard) {
        this.state = considerState(deck, needToDrawCard);
    }

    private PlayState considerState(final Deck deck, final boolean needToDrawCard) {
        if (needToDrawCard) {
            return state.drawCard(deck);
        }
        return new StandState(state);
    }

    public boolean equalsName(final String name) {
        return name.equals(this.name);
    }

    public String getName() {
        return name;
    }

    public int getBettingAmount() {
        return state.getBettingAmount();
    }

}
