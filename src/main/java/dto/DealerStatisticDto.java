package dto;

public record DealerStatisticDto(
        int win,
        int draw,
        int lose
) {

    public static DealerStatisticDto of(int win, int draw, int lose) {
        return new DealerStatisticDto(win, draw, lose);
    }
}
