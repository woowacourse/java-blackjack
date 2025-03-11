package blackjack.domain.io;

import blackjack.domain.user.Player;

@FunctionalInterface
public interface PlayerFunction {

    void execute(Player player);
}
