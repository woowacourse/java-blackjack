package blackjack.domain.player;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Guests implements Iterable<Guest> {

    private static final int MIN_GUESTS_SIZE = 2;
    private static final int MAX_GUESTS_SIZE = 8;

    private final List<Guest> guests;

    public Guests(final List<String> names) {
        checkSize(names);
        checkDuplicatedNames(names);

        guests = names.stream()
                .map(Guest::new)
                .collect(Collectors.toList());
    }

    private void checkSize(final List<String> names) {
        final int size = names.size();
        if (size < MIN_GUESTS_SIZE || size > MAX_GUESTS_SIZE) {
            throw new IllegalArgumentException("[ERROR] 참자가 인원은 2명~8명 사이여야합니다.");
        }
    }

    private void checkDuplicatedNames(final List<String> names) {
        int count = (int) names.stream()
                .distinct()
                .count();

        if (count != names.size()) {
            throw new IllegalArgumentException("[ERROR] 참가자의 이름은 중복될 수 없습니다.");
        }
    }

    @Override
    public Iterator<Guest> iterator() {
        return guests.iterator();
    }
}
