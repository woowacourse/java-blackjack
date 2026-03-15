package dto;

import domain.User;

public class UserResultDTO {
    private final String userName;
    private final String cardsDisplay;
    private final int totalScore;

    private UserResultDTO(String userName, String cardsDisplay, int totalScore) {
        this.userName = userName;
        this.cardsDisplay = cardsDisplay;
        this.totalScore = totalScore;
    }

    public static UserResultDTO fromUser(User user) {
        return new UserResultDTO(user.getName(), user.getCardsDisplay(), user.getTotalScore());
    }

    public String getUserName() {
        return userName;
    }

    public String getCardsDisplay() {
        return cardsDisplay;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
