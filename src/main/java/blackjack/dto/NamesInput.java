package blackjack.dto;

import blackjack.domain.participant.Name;
import java.util.List;
import java.util.Set;

public record NamesInput(List<Name> names) {

    private static final String NAME_DUPLICATED_EXCEPTION = "플레이어의 이름들이 중복되었습니다. 다시 입력해주세요.";

    public static NamesInput of(final List<String> names) {
        validate(names);

        return new NamesInput(names.stream()
                .map(Name::new)
                .toList());
    }

    private static void validate(final List<String> names) {
        if (names.size() != Set.copyOf(names).size()) {
            throw new IllegalArgumentException(NAME_DUPLICATED_EXCEPTION);
        }
    }

    public int size() {
        return names.size();
    }
}
