package blackjack;

import blackjack.domain.DrawDecision;
import blackjack.domain.bet.Bet;
import blackjack.domain.bet.Money;
import blackjack.domain.player.PlayerName;
import java.util.Arrays;
import java.util.List;

public class InputMapper {

    private static final String DELIMITER = ",";

    public List<PlayerName> mapToPlayerNames(String target) {
        String[] split = target.split(DELIMITER);
        return Arrays.stream(split)
                .map(PlayerName::new)
                .toList();
    }

    public DrawDecision mapToDrawDecision(String target) {
        return DrawDecision.from(target);
    }

    public Bet mapToBet(String name, int betAmount) {
        return new Bet(new PlayerName(name), new Money(betAmount));
    }
}
