package blackjackgame.domain.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import blackjackgame.domain.card.Card;

public class Players {
    private static final int MIN_GUESTS_NUMBER = 1;
    private static final int MAX_GUESTS_NUMBER = 10;

    private final List<Player> players;

    public Players(final List<Player> playerNames) {
        validateGuestNumbers(playerNames);
        validateDuplicate(playerNames);
        this.players = playerNames;
    }

    private void validateGuestNumbers(final List<Player> playerNames) {
        if (playerNames.size() < MIN_GUESTS_NUMBER || playerNames.size() > MAX_GUESTS_NUMBER) {
            throw new IllegalArgumentException(
                "참여자는 " + MIN_GUESTS_NUMBER + "명 이상 " + MAX_GUESTS_NUMBER + "명 이하여야 합니다.");
        }
    }

    private void validateDuplicate(final List<Player> inputGuests) {
        Set<Player> guest = new HashSet<>(inputGuests);
        if (inputGuests.size() != guest.size()) {
            throw new IllegalArgumentException("참여자의 이름은 중복될 수 없습니다.");
        }
    }

    public void add(Dealer dealer) {
        players.add(0, dealer);
    }

    public List<Player> getAllPlayers() {
        return Collections.unmodifiableList(players);
    }

    public List<Guest> guests() {
        List<Guest> guests = new ArrayList<>();
        for (Player player : players) {
            if (player instanceof Guest) {
                guests.add((Guest)player);
            }
        }
        return Collections.unmodifiableList(guests);
    }

    public Map<String, List<Card>> startingCards() {
        Map<String, List<Card>> startingCards = new LinkedHashMap<>();
        for (Player player : players) {
            startingCards.put(player.getName(), player.startingCards());
        }
        return startingCards;
    }

    public Dealer dealer() {
        for (Player player : players) {
            if (player instanceof Dealer) {
                return (Dealer)player;
            }
        }
        throw new IllegalStateException();
    }
}
