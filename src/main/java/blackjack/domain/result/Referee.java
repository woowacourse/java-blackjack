package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.List;
import java.util.NoSuchElementException;

public class Referee {
    private static final Referee INSTANCE = new Referee();
    private final List<HandResult> handResults;

    private Referee() {
        this.handResults = List.of(HandResult.values());
    }

    public static Referee getInstance() {
        return INSTANCE;
    }

    public HandResult getPlayerResult(Player player, Dealer dealer) {
        return handResults.stream()
                .filter(handResult -> handResult.match(player, dealer))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
