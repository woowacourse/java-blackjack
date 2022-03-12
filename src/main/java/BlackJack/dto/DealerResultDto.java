package BlackJack.dto;

import BlackJack.domain.User.Dealer;

public class DealerResultDto {

    private final String name;
    private final int dealerLoseCount;
    private final int dealerDrawCount;
    private final int dealerWinCount;

    public DealerResultDto(String name, int dealerLoseCount, int dealerDrawCount, int dealerWinCount) {
        this.name = name;
        this.dealerDrawCount = dealerDrawCount;
        this.dealerLoseCount = dealerLoseCount;
        this.dealerWinCount = dealerWinCount;
    }

    public static DealerResultDto from(Dealer dealer, int totalGameCount) {
        int dealerLoseCount = dealer.getDealerLoseCount();
        int dealerDrawCount = dealer.getDealerDrawCount();
        int dealerWinCount = totalGameCount - (dealerLoseCount + dealerDrawCount);
        return new DealerResultDto(dealer.getName(), dealerLoseCount, dealerDrawCount, dealerWinCount);
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
