package blackjack.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NamesDto {
    private final List<String> names;

    public NamesDto(String names) {
        this.names = validate(names);
    }

    private List<String> validate(String names) {
            List<String> splittedNames = Arrays.stream(names.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());

        if (names == null || splittedNames.isEmpty())
            throw new NullPointerException("비어있는 값을 입력하셨습니다");

        return splittedNames;
    }

    public List<String> get() {
        return names;
    }
}
