package domain.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GameMember implements Iterable<Playable> {
    
    public static final String DUPLICATED_NAME_ERROR_ESSAGE = "중복된 플레이어 이름이 있습니다.";
    private final Dealer dealer;
    private final List<Player> players;
    
    private GameMember(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }
    
    public static GameMember of(final String playerNames) {
        List<Player> players = Arrays.stream(playerNames.split(","))
                .map(String::trim)
                .map(Player::new)
                .collect(Collectors.toList());
        validateUniqueness(players);
        return new GameMember(new Dealer(), players);
    }
    
    private static void validateUniqueness(final List<Player> players) {
        Set<Player> playerSet = new HashSet<>(players);
        if (playerSet.size() != players.size()) {
            throw new IllegalArgumentException(DUPLICATED_NAME_ERROR_ESSAGE);
        }
    }
    
    public Dealer getDealer() {
        return this.dealer;
    }
    
    public List<Player> getPlayers() {
        return new ArrayList<>(this.players);
    }
    
    @Override
    public Iterator<Playable> iterator() {
        return this.getGameMember().iterator();
    }
    
    private List<Playable> getGameMember() {
        ArrayList<Playable> gameMember = new ArrayList<>();
        gameMember.add(this.dealer);
        gameMember.addAll(this.players);
        return gameMember;
    }
    
}
