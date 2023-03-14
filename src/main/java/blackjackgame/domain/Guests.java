package blackjackgame.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Guests {
    private final List<Guest> guests;

    public Guests(final List<Name> playerNames, final List<BettingMoney> bettingMonies, final Deck deck) {
        validate(playerNames, bettingMonies);
        this.guests = generateGuests(playerNames, bettingMonies, deck);
    }

    private void validate(final List<Name> playerNames, final List<BettingMoney> bettingMonies) {
        if (playerNames.size() != bettingMonies.size()) {
            throw new IllegalArgumentException("플레이어의 수만큼 베팅 금액이 입력되어야 합니다.");
        }
    }

    private List<Guest> generateGuests(final List<Name> playerNames, final List<BettingMoney> bettingMonies, final Deck deck) {
        return IntStream.range(0, playerNames.size())
                .mapToObj(i -> new Guest(playerNames.get(i), new Hand(deck.pickOne(), deck.pickOne()), bettingMonies.get(i)))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Guest> getGuests() {
        return List.copyOf(guests);
    }
}
