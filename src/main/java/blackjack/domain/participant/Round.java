package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.result.HandResult;
import blackjack.domain.result.PlayersPots;
import blackjack.domain.result.Referee;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Round {
    private final Dealer dealer;
    private final Players players;

    public Round(List<Name> playersName, Deck deck) {
        this.players = new Players(playersName, deck);
        this.dealer = new Dealer(deck);
    }

    public PlayersPots generatePlayersPots(List<BetAmount> betAmounts) {
        List<Player> players = this.players.getPlayers();
        Map<Player, BetAmount> playersPots = new LinkedHashMap<>();
        IntStream.range(0, players.size())
                .forEach(i -> playersPots.put(players.get(i), betAmounts.get(i)));
        return new PlayersPots(playersPots);
    }

    public Map<Player, HandResult> generateResult(Referee referee) {
        Map<Player, HandResult> playerResults = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            HandResult playerResult = referee.getPlayerResult(player, dealer);
            playerResults.put(player, playerResult);
        }
        return playerResults;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
