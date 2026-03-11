package domain;

public record DealerResultDto(
        int win,
        int draw,
        int lose
) {
    public static DealerResultDto of(int win, int draw, int lose) {
        return new DealerResultDto(win, draw, lose);
    }
}
