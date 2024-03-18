package domain.user;

import java.util.List;
import java.util.stream.Collectors;

public record Names(List<Name> value) {
    public Names {
        validateDuplicate(value);
    }

    private void validateDuplicate(List<Name> value) {
        int distinctSize = value.stream()
                .map(Name::value)
                .collect(Collectors.toSet())
                .size();
        if (value.size() != distinctSize) {
            throw new IllegalArgumentException("중복된 이름은 허용하지 않습니다.");
        }
    }
}
