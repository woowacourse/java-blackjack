package blackjack.dto;

import java.util.List;

public record NamesRequestDto(
    List<String> names
) {

    public static NamesRequestDto from(String input) {
        return new NamesRequestDto(List.of(input.split(",")));
    }
}
