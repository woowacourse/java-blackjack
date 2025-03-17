package blackjack.domain.participant;

import blackjack.domain.card.Hand;
import blackjack.domain.participant.participant.Participant;
import blackjack.domain.participant.participant.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Players {

    private final List<Player> players;

    public Players(final List<Player> players) {
        validate(players);
        this.players = new ArrayList<>(players);
    }

    public static Players from(final List<String> names, final List<Integer> bettingAmounts) {
        return new Players(IntStream.range(0, names.size())
                .mapToObj(index -> new Player(names.get(index), bettingAmounts.get(index)))
                .toList());
    }

    public void receiveCardsByCount(final Hand hand, final int count) {
        for (int i = 0; i < players.size(); i++) {
            final Player player = players.get(i);
            player.receiveCards(hand.subHand(i * count, (i + 1) * count));
        }
    }

    public Map<String, Hand> showTotalInitialCards() {
        return players.stream()
                .collect(Collectors.toMap(Participant::getNickname,
                        Participant::showInitialCards, (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    public Map<String, Hand> showTotalCards() {
        return players.stream()
                .collect(Collectors.toMap(Participant::getNickname, Participant::showInitialCards, (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    public Players findHitAvailablePlayers() {
        return new Players(players.stream()
                .filter(Participant::canHit)
                .toList());
    }

    public Map<Player, Hand> showAllCards() {
        return players.stream()
                .collect(Collectors.toMap(player -> player, Participant::showAllCards, (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    private void validate(List<Player> players) {
        if (isDuplicate(players)) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름을 입력했습니다.");
        }
    }

    private boolean isDuplicate(final List<Player> players) {
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
                .map(Participant::getNickname)
                .toList();
    }

    public int getSize() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
