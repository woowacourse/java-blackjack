package dto;


public class DealerResultDto {
    private final String dealerName;
    private final double proceeds;

    public DealerResultDto(String dealerName, double proceeds) {
        this.dealerName = dealerName;
        this.proceeds = proceeds;
    }

    public String getDealerName() {
        return dealerName;
    }

    public double getProceeds() {
        return proceeds;
    }

}
