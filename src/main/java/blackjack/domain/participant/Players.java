package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {

    private final List<Gamer> players;

    public Players(final List<? extends Gamer> players) {
        validate(players);
        this.players = new ArrayList<>(players);
    }

    public void receiveCards(final Cards cards, final int count) {
        for (int i = 0; i < players.size(); i++) {
            final Gamer player = players.get(i);
            player.receiveCards(cards.getPartialCards(i * count, (i + 1) * count));
        }
    }

    public Map<String, Cards> showTotalInitialCards() {
        return players.stream()
                .collect(Collectors.toMap(Gamer::getNickname,
                        Gamer::showInitialCards, (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    public Map<String, Cards> showTotalCards() {
        return players.stream()
                .collect(Collectors.toMap(Gamer::getNickname, Gamer::showInitialCards, (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    public Players findExtraCardsAvailablePlayers() {
        return new Players(players.stream()
                .filter(Gamer::canGetMoreCard)
                .toList());
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

    public List<Gamer> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Gamer getPlayer(final int index) {
        return players.get(index);
    }
}
