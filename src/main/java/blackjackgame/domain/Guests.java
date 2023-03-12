package blackjackgame.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Guests {
    private static final int MIN_GUESTS_NUMBER = 1;
    private static final int MAX_GUESTS_NUMBER = 10;

    private final List<Guest> guests;

    public static void validateGuestNames(final List<String> guestNames) {
        validateGuestNumbers(guestNames);
        validateDuplicate(guestNames);
    }

    private static void validateGuestNumbers(final List<String> playerNames) {
        if (playerNames.size() < MIN_GUESTS_NUMBER || playerNames.size() > MAX_GUESTS_NUMBER) {
            throw new IllegalArgumentException("참여자는 " + MIN_GUESTS_NUMBER + "명 이상 " + MAX_GUESTS_NUMBER + "명 이하여야 합니다.");
        }
    }

    private static void validateDuplicate(final List<String> playerNames) {
        if (playerNames.size() != playerNames.stream().distinct().count()) {
            throw new IllegalArgumentException("참여자의 이름은 중복될 수 없습니다.");
        }
    }

    public Guests(final List<Name> playerNames, final List<BettingMoney> bettingMonies, final Deck deck) {
        this.guests = generateGuests(playerNames, bettingMonies, deck);
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
