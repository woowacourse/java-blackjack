package blackjack.dto.request;

import java.util.List;

public record NamesRequestDto(
        List<String> names
) {

    private static final String DELIMITER = ",";

    public static NamesRequestDto from(String input) {
        return new NamesRequestDto(List.of(input.split(DELIMITER)));
    }
}
