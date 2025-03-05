package blackjack.dto;

public record SelectionRequestDto(
    boolean selection
) {

    public static SelectionRequestDto from(String input) {
        if (input.equalsIgnoreCase("Y")) {
            return new SelectionRequestDto(true);
        }
        if (input.equalsIgnoreCase("N")) {
            return new SelectionRequestDto(false);
        }
        throw new IllegalArgumentException("[ERROR] Y 또는 N을 입력해야 합니다.");
    }
}
