package dto;

import domain.Dealer;

public class DealerResultDTO {
    private final String cardsDisplay;
    private final int totalScore;

    private DealerResultDTO(String cardsDisplay, int totalScore) {
        this.cardsDisplay = cardsDisplay;
        this.totalScore = totalScore;
    }

    public static DealerResultDTO fromDealer(Dealer dealer) {
        return new DealerResultDTO(dealer.getCardsDisplay(), dealer.getTotalScore());
    }

    public String getCardsDisplay() {
        return cardsDisplay;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
