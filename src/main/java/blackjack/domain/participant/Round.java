package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.result.BlackjackResult;
import blackjack.domain.result.HandResult;
import blackjack.domain.result.Pot;
import blackjack.domain.result.Referee;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Round {
    private final Dealer dealer;
    private final Players players;

    public Round(List<Name> playersName, Deck deck) {
        this.players = new Players(playersName, deck);
        this.dealer = new Dealer(deck);
    }

    public Pot generatePot(List<BetAmount> betAmounts) {
        List<Player> players = this.players.getPlayers();
        Map<Player, BetAmount> pot = IntStream.range(0, betAmounts.size())
                .boxed()
                .collect(Collectors.toMap(
                        players::get,
                        betAmounts::get
                ));
        return new Pot(pot);
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
