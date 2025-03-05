package blackjack.dto;

import java.util.List;

public record NamesRequestDto(
    List<String> names
) {

    // TODO 상수화
    public static NamesRequestDto from(String input) {
        return new NamesRequestDto(List.of(input.split(",")));
    }
}
