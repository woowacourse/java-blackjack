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

    public void updatePlayerMoney(Dealer dealer, Player player) {
        GameResult gameResult = GameResult.judgePlayerResult(dealer, player);
        player.updateMoney(gameResult.getRate());
    }

    public Map<Player, Profit> calculatePlayersGameResults(Players players) {
        return players.getPlayers().stream()
                .collect(Collectors.toMap(
                        player -> player,
                        Player::calculateProfit,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    public Profit calculateDealerGameResults(Players players) {
        return players.sumProfits().negate();
    }
}
