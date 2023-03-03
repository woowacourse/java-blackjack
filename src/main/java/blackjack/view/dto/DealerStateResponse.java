package blackjack.view.dto;

public class DealerStateResponse {

    private final boolean drawable;
    private final int limit;

    public DealerStateResponse(final boolean drawable, final int limit) {
        this.drawable = drawable;
        this.limit = limit;
    }

    public boolean isDrawable() {
        return drawable;
    }

    public int getLimit() {
        return limit;
    }
}
