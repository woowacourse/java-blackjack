package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.result.BlackjackResult;
import blackjack.domain.result.HandResult;
import blackjack.domain.result.Referee;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Participants {
    private final Dealer dealer;
    private final Players players;

    public Participants(List<Name> playersName, Deck deck) {
        this.players = new Players(playersName, deck);
        this.dealer = new Dealer(deck);
    }

    public BlackjackResult generateResult(Referee referee) {
        Map<Player, HandResult> playerResults = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
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
