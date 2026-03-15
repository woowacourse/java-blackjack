package util;

import dto.DealerResultDTO;
import dto.UserCardsDTO;
import dto.UserResultDTO;
import view.Message;

public class DisplayFormatter {
    private DisplayFormatter() {}

    public static String formatUserCardsDisplay(UserCardsDTO dto) {
        return String.format(Message.USER_CARDS_FORMAT_MESSAGE, dto.getUserName(), dto.getCardsDisplay());
    }

    public static String formatUserResultDisplay(UserResultDTO dto) {
        return String.format(Message.USER_RESULT_FORMAT_MESSAGE, dto.getUserName(), dto.getCardsDisplay(), dto.getTotalScore());
    }

    public static String formatDealerResultDisplay(DealerResultDTO dto) {
        return String.format(Message.DEALER_RESULT_FORMAT_MESSAGE, dto.getCardsDisplay(), dto.getTotalScore());
    }
}
