package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private static final int MAX_PLAYERS_SIZE = 5;
    private static final String PLAYERS_SIZE_ERROR_GUIDE_MESSAGE = "[ERROR] 플레어이는 5명까지 참가 가능합니다.";
    //인원제한 적당히... 5명!
    private final List<Player> players;

    public Players(List<String> playerNames) {
        validatePlayersSize(playerNames);
        this.players = createPlayers(playerNames);
    }

    private void validatePlayersSize(List<String> playerNames) {
        if(playerNames.size()> MAX_PLAYERS_SIZE){
            throw new IllegalArgumentException(PLAYERS_SIZE_ERROR_GUIDE_MESSAGE);
        }
    }

    private List<Player> createPlayers(List<String> players) {
        return players.stream()
                .map(name -> new Player(new PlayerName(name), new Cards()))
                .collect(Collectors.toList());
    }
}
