package DTO;

public class FinalResultDTO {
    private final String name;
    private final int profit;

    public FinalResultDTO(final String name, final int profit) {
        this.name = name;
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }
}
