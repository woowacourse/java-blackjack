package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Game {
    private static final int MAX_PLAYER_COUNT = 5;
    private final Map<Player, GameResult> players = new HashMap<>();
    private final Dealer dealer;

    public Game(List<String> playerNames) {
        dealer = new Dealer();
        validatePlayerCount(playerNames);
        validateDuplicateName(playerNames);
        playerNames.forEach(this::registerPlayer);
    }
    
    public List<Card> getDealerCards() {
        return dealer.getCards();
    }

    public void hit(Player player) {
        Card card = dealer.pickCard();
        player.hit(card);
    }

    public List<Player> getPlayers() {
        return List.copyOf(players.keySet());
    }

    private void registerPlayer(String playerName) {
        CardHand initialDeal = dealer.getInitialDeal();
        Player player = new Player(playerName, initialDeal);
        players.put(player, GameResult.NONE);
    }

    private void validatePlayerCount(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 참여자는 최대 5명입니다.");
        }
    }

    private void validateDuplicateName(List<String> playerNames) {
        if (playerNames.size() != Set.copyOf(playerNames).size()) {
            throw new IllegalArgumentException("[ERROR] 이름은 중복될 수 없습니다.");
        }
    }
}
