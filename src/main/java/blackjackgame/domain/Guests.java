package blackjackgame.domain;

import java.util.ArrayList;
import java.util.List;

public class Guests {
    private static final int MIN_GUESTS_NUMBER = 1;
    private static final int MAX_GUESTS_NUMBER = 10;
    private static final String ERROR_GUESTS_NUMBER_RANGE_MSG = "참여자는 " + MIN_GUESTS_NUMBER + "명 이상 " + MAX_GUESTS_NUMBER + "명 이하여야 합니다.";
    private static final String ERROR_DUPLICATE_GUEST_NAMES_MSG = "참여자의 이름은 중복될 수 없습니다.";
    List<Guest> guests;

    public Guests(final List<String> playerNames) {
        this.guests = new ArrayList<>();
        validateGuestNumbers(playerNames);

        for (final String playerName : playerNames) {
            Guest guest = new Guest(new Name(playerName));
            validateDuplicate(guest);
            guests.add(guest);
        }
    }

    private void validateGuestNumbers(final List<String> playerNames) {
        if (playerNames.size() < 1 || playerNames.size() > 10) {
            throw new IllegalArgumentException(ERROR_GUESTS_NUMBER_RANGE_MSG);
        }
    }

    private void validateDuplicate(final Guest guest) {
        if (guests.contains(guest)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_GUEST_NAMES_MSG);
        }
    }
}
