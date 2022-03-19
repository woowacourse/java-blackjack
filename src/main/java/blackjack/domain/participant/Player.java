package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.state.State;
import blackjack.domain.participant.validation.PlayerNameValidator;

public final class Player extends Participant {

    private final String name;
    private BettingAmount bettingAmount;

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
        if (bettingAmount != null) {
            throw new IllegalStateException("이미 베팅을 했습니다.");
        }
        this.bettingAmount = new BettingAmount(amount);
    }

    public void drawCard(final Card card, final boolean needToDrawCard) {
        this.state = considerState(card, needToDrawCard);
    }

    private State considerState(final Card card, final boolean needToDrawCard) {
        if (needToDrawCard) {
            return state.drawCard(card);
        }
        return state.stay();
    }

    public boolean equalsName(final String name) {
        return name.equals(this.name);
    }

    public String getName() {
        return name;
    }

    public int getBettingAmount() {
        return bettingAmount.getAmount();
    }

}
