package domain;

import domain.user.Dealer;
import domain.user.Playable;
import domain.user.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Participants implements Iterable<Playable> {
    
    private final Dealer dealer;
    private final List<Player> players;
    
    private Participants(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }
    
    public static Participants of(final String participantNames) {
        String[] names = participantNames.split(",");
        List<Player> players = Arrays.stream(names)
                .map(Player::new)
                .collect(Collectors.toList());
        return new Participants(new Dealer(), players);
    }
    
    public Dealer getDealer() {
        return this.dealer;
    }
    
    public List<Player> getPlayers() {
        return new ArrayList<Player>(this.players);
    }
    
    @Override
    public Iterator<Playable> iterator() {
        return this.getParticipants().iterator();
    }
    
    private List<Playable> getParticipants() {
        ArrayList<Playable> participants = new ArrayList<>();
        participants.add(this.dealer);
        participants.addAll(this.players);
        return participants;
    }
    
    public Stream<Playable> stream() {
        return this.getParticipants().stream();
    }
}
