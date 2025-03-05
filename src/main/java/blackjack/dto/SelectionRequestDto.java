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
        // TODO 예외 메시지 묶어서 관리
        // TODO 예외처리 해야함
        throw new IllegalArgumentException("[ERROR] Y 또는 N을 입력해야 합니다.");
    }
}
