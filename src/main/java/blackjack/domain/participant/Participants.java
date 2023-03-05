package blackjack.domain.participant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private static final String EMPTY_ERROR_MESSAGE = "참가자들이 존재하지 않습니다.";

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(final Dealer dealer, List<String> players) {
        validateEmptyNames(players);

        this.dealer = dealer;
        this.players = makePlayer(players);
    }

    private void validateEmptyNames(final List<String> players) {
        players.forEach(player->{
            checkNameIsEmpty(player);
        });
    }

    private static void checkNameIsEmpty(String player) {
        if(player.length()==0){
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
    }

    private List<Player> makePlayer(List<String> players){
        return players.stream().map(name->new Player(new Name(name))).collect(Collectors.toList());
    }
    public Dealer getDealer() {
        return this.dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }
}
