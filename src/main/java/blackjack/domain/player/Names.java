package blackjack.domain.player;

import java.util.HashSet;
import java.util.List;

public final class Names {

    public static final String DEALER_NAME = "딜러";

    private final List<String> names;

    private Names(final List<String> names) {
        validateDuplicate(names);
        validateName(names);
        this.names = names;
    }

    public static Names from(final List<String> names) {
        return new Names(names);
    }

    private void validateDuplicate(final List<String> names) {
        if (isDuplicate(names)) {
            throw new IllegalArgumentException("중복되지 않은 이름만 입력해주세요");
        }
    }

    private boolean isDuplicate(final List<String> participants) {
        int uniqueNameCount = new HashSet<>(participants).size();
        return uniqueNameCount < participants.size();
    }

    private void validateName(final List<String> names) {
        if (isParticipantsNameDealer(names)) {
            throw new IllegalArgumentException("플레이어의 이름은 딜러가 될 수 없습니다.");
        }
    }

    private boolean isParticipantsNameDealer(final List<String> names) {
        return names.stream()
                .anyMatch(name -> name.equals(DEALER_NAME));
    }

    public List<String> getNames() {
        return List.copyOf(names);
    }
}
