package blackjack.domain.participant;

import blackjack.domain.card.HandGenerator;
import blackjack.domain.result.BlackjackResult;
import blackjack.domain.result.HandResult;
import blackjack.domain.result.Referee;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Participants {
    private final Dealer dealer;
    private final Players players;

    public Participants(List<Name> playersName, HandGenerator handGenerator) {
        this.players = new Players(playersName, handGenerator);
        this.dealer = new Dealer(handGenerator);
    }

    public BlackjackResult generateResult(Referee referee) {
        Map<Player, HandResult> playerResults = new LinkedHashMap<>();
        for (Player player : players.getValues()) {
            HandResult playerResult = referee.getPlayerResult(player, dealer);
            playerResults.put(player, playerResult);
        }
        return new BlackjackResult(playerResults);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
