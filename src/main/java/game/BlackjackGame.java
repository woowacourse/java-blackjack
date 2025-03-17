package game;

import deck.Deck;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import participant.Dealer;
import participant.Player;
import participant.Players;
import participant.Profit;

public class BlackjackGame {

    private static final int INITIAL_COUNT = 2;

    public void distributeTwoCardsToParticipants(Dealer dealer, Players players, Deck deck) {
        for (int count = 0; count < INITIAL_COUNT; count++) {
            dealer.receiveCard(deck.draw());
            players.getPlayers().forEach(player -> player.receiveCard(deck.draw()));
        }
    }

    public void runParticipantTurn(Playable participant, Deck deck) {
        if (!participant.canReceiveCard()) {
            return;
        }
        participant.receiveCard(deck.draw());
    }

    public void updatePlayerMoney(Dealer dealer, Players players) {
        Map<Player, GameResult> gameResults = dealer.decideGameResults(players);
        players.updateMoney(gameResults);
    }

    public Map<Playable, Profit> calculateParticipantGameResults(Dealer dealer, Players players) {
        Map<Playable, Profit> gameResults = new LinkedHashMap<>();
        gameResults.putAll(calculatePlayersGameResults(players));
        gameResults.put(dealer, calculateDealerGameResults(players));
        return gameResults;
    }

    private Map<Playable, Profit> calculatePlayersGameResults(Players players) {
        return players.getPlayers().stream()
                .collect(Collectors.toMap(
                        player -> player,
                        Player::calculateProfit,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    private Profit calculateDealerGameResults(Players players) {
        return players.sumProfits().negate();
    }
}
