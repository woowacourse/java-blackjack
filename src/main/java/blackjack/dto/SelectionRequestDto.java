package blackjack.dto;

public record SelectionRequestDto(
    boolean selection
) {

    // TODO 상수화
    public static SelectionRequestDto from(String input) {
        if (input.equalsIgnoreCase("Y")) {
            return new SelectionRequestDto(true);
        }
        if (input.equalsIgnoreCase("N")) {
            return new SelectionRequestDto(false);
        }
        // TODO 예외 메시지에서 상수 분리
        throw new IllegalArgumentException("[ERROR] Y 또는 N을 입력해야 합니다.");
    }
}
