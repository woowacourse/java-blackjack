package blackjackgame.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Guests {
    private static final int MIN_GUESTS_NUMBER = 1;
    private static final int MAX_GUESTS_NUMBER = 10;
    private static final String ERROR_GUESTS_NUMBER_RANGE_MSG =
        "참여자는 " + MIN_GUESTS_NUMBER + "명 이상 " + MAX_GUESTS_NUMBER + "명 이하여야 합니다.";
    private static final String ERROR_DUPLICATE_GUEST_NAMES_MSG = "참여자의 이름은 중복될 수 없습니다.";

    private final List<Guest> guests;

    public Guests(final List<String> playerNames) {
        validateGuestNumbers(playerNames);

        this.guests = generateGuests(playerNames);
    }

    private List<Guest> generateGuests(final List<String> playerNames) {
        List<Guest> inputGuests = new ArrayList<>();
        for (final String playerName : playerNames) {
            Guest guest = new Guest(new Name(playerName));
            validateDuplicate(inputGuests, guest);
            inputGuests.add(guest);
        }
        return inputGuests;
    }

    private void validateGuestNumbers(final List<String> playerNames) {
        if (playerNames.size() < MIN_GUESTS_NUMBER || playerNames.size() > MAX_GUESTS_NUMBER) {
            throw new IllegalArgumentException(ERROR_GUESTS_NUMBER_RANGE_MSG);
        }
    }

    private void validateDuplicate(final List<Guest> inputGuests, final Guest guest) {
        if (inputGuests.contains(guest)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_GUEST_NAMES_MSG);
        }
    }

    public List<Guest> getGuests() {
        return Collections.unmodifiableList(guests);
    }
}
