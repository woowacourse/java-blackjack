package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = new ArrayList<>(players);
    }

    private void validate(List<Player> players) {
        if (isDuplicate(players)) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름을 입력했습니다.");
        }
    }

    private static boolean isDuplicate(List<Player> players) {
        return players.size() != players.stream()
                .distinct()
                .count();
    }

    public void receiveCards(final Cards cards, final int count) {
        for (int i = 0; i < players.size(); i++) {
            final Player player = players.get(i);
            player.receiveCards(cards.getPartialCards(i * count, (i + 1) * count));
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Players players1)) {
            return false;
        }
        return Objects.equals(players, players1.players);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(players);
    }

    public Player getPlayer(final int index) {
        return players.get(index);
    }

    public int getSize() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
