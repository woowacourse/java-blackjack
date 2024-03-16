package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.state.Ready;
import blackjack.domain.card.state.State;

import java.util.List;
import java.util.Objects;

public abstract class Participant implements CardReceivable {

    protected final PlayerInfo playerInfo;
    protected State state;
    private static final BettingMoney DEFAULT_BETTING_MONEY = new BettingMoney(1000);

    protected Participant(final PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
        this.state = new Ready();
    }

    protected Participant(final Name name, final Cards cards) {
        this.playerInfo = new PlayerInfo(name, DEFAULT_BETTING_MONEY);
        this.state = initializeState(cards.toList());
    }

    private State initializeState(final List<Card> cards) {
        State initializeState = new Ready();
        for (final Card card : cards) {
            initializeState = initializeState.draw(card);
        }
        return initializeState;
    }

    public void stand() {
        this.state = this.state.stand();
    }

    public List<Card> getCards() {
        return state.getCards();
    }

    public int calculateScore() {
        return state.calculate();
    }

    public void drawCard(final Card card) {
        this.state = state.draw(card);
    }

    public boolean isBlackjack() {
        return this.state.isBlackjack();
    }

    public boolean isBust() {
        return this.state.isBust();
    }

    public boolean isStand() {
        return this.state.isStand();
    }

    public String getNameAsString() {
        return this.playerInfo.name()
                              .asString();
    }

    public int getBettingMoney() {
        return this.playerInfo.bettingMoney()
                              .value();
    }

    public Name getName() {
        return this.playerInfo.name();
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (!(object instanceof final Participant that)) return false;
        return Objects.equals(this.playerInfo.name(), that.playerInfo.name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.playerInfo.name());
    }
}
