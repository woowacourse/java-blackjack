package util;

import dto.UserCardsDTO;

public class DisplayFormatter {
    private DisplayFormatter() {}

    public static String formatUserCardsDisplay(UserCardsDTO userCardsDTO) {
        return userCardsDTO.getUserName() + "카드: " +  userCardsDTO.getCardsDisplay();
    }
}
