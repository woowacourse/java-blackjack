package dto;

public class DealerCardDTO {
    private final String name = "딜러";
    private final String cardDisplay;

    public DealerCardDTO(String cardDisplay) {
        this.cardDisplay = cardDisplay;
    }

    public String getCardDisplay() {
        return cardDisplay;
    }
}
