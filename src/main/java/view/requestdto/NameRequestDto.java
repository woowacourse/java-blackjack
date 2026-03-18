package view.requestdto;

import java.util.List;
import view.Parser;

public record NameRequestDto(List<String> names) {

    private static final String COMMA_DELIMITER = ",";

    public static NameRequestDto from(String input) {
        validateNameIsNotNullAndIsNotBlank(input);
        List<String> parsedNames = Parser.parse(input, COMMA_DELIMITER);
        return new NameRequestDto(parsedNames);
    }

    private static void validateNameIsNotNullAndIsNotBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름 리스트는 null 또는 공백이면 안됩니다");
        }
    }
}
