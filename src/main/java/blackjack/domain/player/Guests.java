package blackjack.domain.player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Guests implements Iterable<Guest> {

    private static final int MIN_GUESTS_SIZE = 2;
    private static final int MAX_GUESTS_SIZE = 8;

    private final List<Guest> guests;

    private Guests(final List<Guest> guests) {
        checkSize(guests);
        checkDuplicatedNames(guests);

        this.guests = new ArrayList<>(guests);
    }

    public static Guests of(final Guest... guests) {
        return new Guests(List.of(guests));
    }

    public static Guests namesOf(final List<String> names) {
        return new Guests(toGuests(names));
    }

    private static void checkSize(final List<Guest> guests) {
        final int size = guests.size();
        if (size < MIN_GUESTS_SIZE || size > MAX_GUESTS_SIZE) {
            throw new IllegalArgumentException("[ERROR] 참자가 인원은 2명~8명 사이여야합니다.");
        }
    }

    private static void checkDuplicatedNames(final List<Guest> guests) {
        int count = (int) guests.stream()
                .map(Player::getName)
                .distinct()
                .count();

        if (count != guests.size()) {
            throw new IllegalArgumentException("[ERROR] 참가자의 이름은 중복될 수 없습니다.");
        }
    }

    private static List<Guest> toGuests(final List<String> names) {
        return names.stream()
                .map(Guest::new)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Guest> iterator() {
        return guests.iterator();
    }
}
