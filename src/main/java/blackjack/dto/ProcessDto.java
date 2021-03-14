package blackjack.dto;

import blackjack.domain.card.Cards;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProcessDto {
    private final Players players;
    private final Participant dealer;

    public ProcessDto(Players players, Participant dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public List<String> names() {
        return players.names();
    }

    public Map<String, Cards> cards() {
        final Map<String, Cards> results = new LinkedHashMap<>();

        results.put(Dealer.DEALER_NAME, dealer.getCards());
        for (Player player : players.getUnmodifiableList()) {
            results.put(player.getName(), player.getCards());
        }

        return results;
    }


}
