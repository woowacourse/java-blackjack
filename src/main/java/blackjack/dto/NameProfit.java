package blackjack.dto;

import blackjack.model.betting.Money;
import blackjack.model.participant.Name;
import blackjack.model.participant.Player;

public record NameProfit(Name name, Money profit) {
    public static NameProfit of(final Player player, final Money money) {
        return new NameProfit(player.getName(), money);
    }
}
