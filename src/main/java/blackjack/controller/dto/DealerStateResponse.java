package blackjack.controller.dto;

public class DealerStateResponse {

    private final boolean isDrawn;
    private final int limit;

    private DealerStateResponse(final boolean isDrawn, final int limit) {
        this.isDrawn = isDrawn;
        this.limit = limit;
    }

    public static DealerStateResponse of(final boolean isDrawn, final int limit) {
        return new DealerStateResponse(isDrawn, limit);
    }

    public boolean isDrawn() {
        return isDrawn;
    }

    public int getLimit() {
        return limit;
    }
}
