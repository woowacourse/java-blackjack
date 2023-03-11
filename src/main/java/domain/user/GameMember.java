package domain.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameMember implements Iterable<Playable> {
    
    private final Dealer dealer;
    private final List<Player> players;
    
    private GameMember(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }
    
    public static GameMember of(final String participantNames) {
        String[] names = participantNames.split(",");
        List<Player> players = Arrays.stream(names)
                .map(String::trim)
                .map(Player::new)
                .collect(Collectors.toList());
        return new GameMember(new Dealer(), players);
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
    
    public Stream<Playable> stream() {
        return this.getGameMember().stream();
    }
}
