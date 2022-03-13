package blackjack.dto;

public class DealerTurnResultDto {

    private final int count;

    public DealerTurnResultDto(final int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
