package BlackJack.dto;

public class DealerResultDto {

    private final String name;
    private int dealerLoseCount = 0;
    private int dealerDrawCount = 0;
    private int dealerWinCount = 0;

    public DealerResultDto(String name, int dealerLoseCount, int dealerDrawCount, int dealerWinCount) {
        this.name = name;
        this.dealerDrawCount = dealerDrawCount;
        this.dealerLoseCount = dealerLoseCount;
        this.dealerWinCount = dealerWinCount;
    }

    public static DealerResultDto from(String name, int dealerLoseCount, int dealerDrawCount, int dealerWinCount) {
        return new DealerResultDto(name, dealerLoseCount, dealerDrawCount, dealerWinCount);
    }

    public String getName() {
        return name;
    }

    public int getDealerDrawCount() {
        return dealerDrawCount;
    }

    public int getDealerLoseCount() {
        return dealerLoseCount;
    }

    public int getDealerWinCount() {
        return dealerWinCount;
    }

}
