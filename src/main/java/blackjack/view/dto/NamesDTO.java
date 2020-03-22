package blackjack.view.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NamesDTO {
    private static final String DELIMITER = ",";

    private final List<String> names;

    public NamesDTO(String inputNames) {
        validate(inputNames);
        this.names = Arrays.stream(inputNames.split(DELIMITER))
                .collect(Collectors.toList());
        validateSize();
    }

    private void validateSize() {
        if (this.names.isEmpty()) {
            throw new IllegalArgumentException("입력한 이름이 없습니다.");
        }
    }

    private void validate(String inputNames) {
        if (inputNames == null || inputNames.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format("%s 비어있는 값을 입력했습니다.", inputNames));
        }
    }

    public List<String> getNames() {
        return names;
    }

}
