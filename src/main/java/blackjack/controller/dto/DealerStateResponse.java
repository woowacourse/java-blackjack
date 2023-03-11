package blackjack.controller.dto;

public class DealerStateResponse {

    private final boolean isDrawn;
    private final int limit;

    public DealerStateResponse(final boolean isDrawn, final int limit) {
        this.isDrawn = isDrawn;
        this.limit = limit;
    }

    public boolean isDrawn() {
        return isDrawn;
    }

    public int getLimit() {
        return limit;
    }
}
