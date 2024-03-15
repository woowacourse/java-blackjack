package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;
import java.util.Objects;

public abstract class Participant implements CardReceivable {

    protected final Cards cards;
    protected final PlayerInfo playerInfo;
    private static final BettingMoney DEFAULT_BETTING_MONEY = new BettingMoney(1000);

    protected Participant(final Name name, final Cards cards) {
        this.playerInfo = new PlayerInfo(name, DEFAULT_BETTING_MONEY);
        this.cards = cards;
    }

    protected Participant(final PlayerInfo playerInfo, final Cards cards) {
        this.playerInfo = playerInfo;
        this.cards = cards;
    }


    public int calculateScore() {
        return cards.calculate();
    }

    public Cards drawCard(final Card card) {
        return cards.draw(card);
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public String getNameAsString() {
        return this.playerInfo.name()
                              .asString();
    }

    public Name getName() {
        return this.playerInfo.name();
    }

    public List<Card> getCards() {
        return cards.toList();
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
