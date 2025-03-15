package blackjack.domain.participant;

import blackjack.domain.card.Hand;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(final List<Player> players) {
        validate(players);
        this.players = new ArrayList<>(players);
    }

    public void receiveCards(final Hand hand, final int count) {
        for (int i = 0; i < players.size(); i++) {
            final Gamer player = players.get(i);
            player.receiveCards(hand.getPartialCards(i * count, (i + 1) * count));
        }
    }

    public Map<String, Hand> showTotalInitialCards() {
        return players.stream()
                .collect(Collectors.toMap(Gamer::getNickname,
                        Gamer::showInitialCards, (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    public Map<String, Hand> showTotalCards() {
        return players.stream()
                .collect(Collectors.toMap(Gamer::getNickname, Gamer::showInitialCards, (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    public Players findHitAvailablePlayers() {
        return new Players(players.stream()
                .filter(Gamer::canHit)
                .toList());
    }

    public Map<String, Integer> calculateScores() {
        return players.stream()
                .collect(Collectors.toMap(Gamer::getNickname, Gamer::calculateScore));
    }

    private void validate(List<? extends Gamer> players) {
        if (isDuplicate(players)) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름을 입력했습니다.");
        }
    }

    private boolean isDuplicate(List<? extends Gamer> players) {
        return players.size() != players.stream()
                .distinct()
                .count();
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

    public List<String> getNames() {
        return players.stream()
                .map(Gamer::getNickname)
                .toList();
    }

    public int getSize() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Player getPlayer(final int index) {
        return players.get(index);
    }
}
