package dto;

import domain.User;

public class UserCardsDTO {
    private final String userName;
    private final String cardsDisplay;

    private UserCardsDTO(String userName, String cardsDisplay) {
        this.userName = userName;
        this.cardsDisplay = cardsDisplay;
    }

    public static UserCardsDTO fromUser(User user) {
        return new UserCardsDTO(user.getUserName(), user.getCardsDisplay());
    }

    public String getUserName() {
        return userName;
    }

    public String getCardsDisplay() {
        return cardsDisplay;
    }
}
