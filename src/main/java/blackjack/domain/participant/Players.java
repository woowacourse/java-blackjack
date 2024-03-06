package blackjack.domain.participant;

import blackjack.domain.HandGenerator;
import blackjack.domain.Name;
import blackjack.exception.InvalidPlayerCountException;
import java.util.List;

public class Players {
    public Players(List<Name> playerNames, HandGenerator handGenerator) {
        if (playerNames.isEmpty()) {
            throw new InvalidPlayerCountException();
        }
    }
}
