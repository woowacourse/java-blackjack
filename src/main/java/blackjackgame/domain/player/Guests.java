package blackjackgame.domain.player;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Guests {
    private static final int MIN_GUESTS_NUMBER = 1;
    private static final int MAX_GUESTS_NUMBER = 10;

    private final List<Guest> guests;

    public Guests(final List<Guest> playerNames) {
        validateGuestNumbers(playerNames);
        validateDuplicate(playerNames);
        this.guests = playerNames;
    }

    private void validateGuestNumbers(final List<Guest> playerNames) {
        if (playerNames.size() < MIN_GUESTS_NUMBER || playerNames.size() > MAX_GUESTS_NUMBER) {
            throw new IllegalArgumentException(
                "참여자는 " + MIN_GUESTS_NUMBER + "명 이상 " + MAX_GUESTS_NUMBER + "명 이하여야 합니다.");
        }
    }

    private void validateDuplicate(final List<Guest> inputGuests) {
        Set<Guest> guest = new HashSet<>(inputGuests);
        if (inputGuests.size() != guest.size()) {
            throw new IllegalArgumentException("참여자의 이름은 중복될 수 없습니다.");
        }
    }

    public List<Guest> getGuests() {
        return Collections.unmodifiableList(guests);
    }
}
