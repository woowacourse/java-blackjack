package blackjack.domain.participant;

import blackjack.domain.gameresult.Batting;
import blackjack.domain.game.Deck;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Players {

    private static final int MIN_PLAYER_NUMBER = 1;
    private static final int MAX_PLAYER_NUMBER = 8;

    private final List<Player> players;

    public Players(Map<Name, Batting> playerNameAndBattings) {
        validatePlayersNumber(playerNameAndBattings.keySet());
        this.players = playerNameAndBattings.entrySet()
                .stream()
                .map(player -> new Player(player.getKey(), player.getValue()))
                .toList();
    }

    public void handOutInitialCards(Deck deck) {
        for (Player player : players) {
            player.addCard(deck.draw());
            player.addCard(deck.draw());
        }
    }


    private void validatePlayersNumber(Set<Name> players) {
        if (players.size() < MIN_PLAYER_NUMBER || players.size() > MAX_PLAYER_NUMBER) {
            throw new IllegalArgumentException("게임 참여자는 최소 " + MIN_PLAYER_NUMBER
                    + "명에서 최대 " + MAX_PLAYER_NUMBER + "명까지 가능합니다");
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
