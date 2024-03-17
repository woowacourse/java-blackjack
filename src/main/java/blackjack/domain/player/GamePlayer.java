package blackjack.domain.player;


import blackjack.domain.card.Cards;
import blackjack.domain.player.info.BettingMoney;
import blackjack.domain.player.info.PlayerInfo;
import blackjack.domain.player.info.Name;

import java.util.Objects;

public class GamePlayer extends Participant {
    private static final BettingMoney DEFAULT_BETTING_MONEY = new BettingMoney(1000);

    private final PlayerInfo playerInfo;

    public GamePlayer(final Name name, final Cards cards) {
        super(cards);
        this.playerInfo = new PlayerInfo(name, DEFAULT_BETTING_MONEY);
    }

    public GamePlayer(final PlayerInfo playerInfo) {
        super();
        this.playerInfo = playerInfo;
    }

    @Override
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
    public boolean isReceivable() {
        return this.state.isHit();
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (!(object instanceof final GamePlayer that)) return false;
        return Objects.equals(this.playerInfo.name(), that.playerInfo.name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.playerInfo.name());
    }
}
