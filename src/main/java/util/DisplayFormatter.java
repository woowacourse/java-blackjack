package util;

import dto.DealerResultDTO;
import dto.ProfitResultDTO;
import dto.UserCardsDTO;
import dto.UserResultDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import view.Message;
import vo.Money;

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

    public static List<String> formatProfitResult(ProfitResultDTO dto) {
        List<String> profitDisplays = new ArrayList<>();

        Money dealerProfit = dto.getDealerProfit();
        profitDisplays.add(String.format(Message.DEALER_PROFIT_FORMAT_MESSAGE, dealerProfit.getValue()));

        Map<String, Money> participantsProfit = dto.getParticipantsProfit();
        for (Map.Entry<String, Money> entry : participantsProfit.entrySet()) {
            profitDisplays.add(String.format(Message.USER_PROFIT_FORMAT_MESSAGE, entry.getKey(), entry.getValue().getValue()));
        }

        return profitDisplays;
    }

    public static String formatExtraCardRequest(String userName) {
        return String.format(Message.REQUEST_GET_EXTRA_CARD, userName);
    }
}
