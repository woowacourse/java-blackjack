package blackjack.dto.request;

public record SelectionRequestDto(
        boolean selection
) {

    private static final String POSITIVE = "Y";
    private static final String NEGATIVE = "N";

    public static SelectionRequestDto from(String input) {
        if (input.equalsIgnoreCase(POSITIVE)) {
            return new SelectionRequestDto(true);
        }
        if (input.equalsIgnoreCase(NEGATIVE)) {
            return new SelectionRequestDto(false);
        }
        throw new IllegalArgumentException(String.format("[ERROR] %s 또는 %s을 입력해야 합니다.", POSITIVE, NEGATIVE));
    }
}
