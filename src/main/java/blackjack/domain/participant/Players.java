package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Players {

    public static final int MAX_PLAYER = 7;

    private final List<Player> players;

    public Players(final List<String> names) {
        this.players = convertToPlayers(new ArrayList<>(names));
        validatePlayerCount(names);
        validateDuplicate(names);
    }

    private List<Player> convertToPlayers(final List<String> names) {
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(new Name(name)));
        }
        return players;
    }

    private void validatePlayerCount(final List<String> names) {
        if (names.size() > MAX_PLAYER) {
            throw new IllegalArgumentException("최대 참여 플레이어는 " + MAX_PLAYER + "명입니다.");
        }
    }

    private void validateDuplicate(final List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    public void initHandByDealer(final Dealer dealer) {
        for (Player player : this.players) {
            player.receiveFirstHand(dealer.drawCards());
        }
    }

    public List<Player> toList() {
        return Collections.unmodifiableList(this.players);
    }
}
