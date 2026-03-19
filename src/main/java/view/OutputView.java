package view;

import domain.Dealer;
import dto.DealerCardDTO;
import dto.DealerResultDTO;
import dto.ProfitResultDTO;
import dto.UserCardsDTO;
import dto.UserResultDTO;
import java.util.List;
import util.DisplayFormatter;

public class OutputView {
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printSingleUserCards(UserCardsDTO dto) {
        System.out.println(DisplayFormatter.formatUserCardsDisplay(dto));
    }

    public void printInitMessages(List<String> names) {
        String formattedNames = String.join(", ", names);
        System.out.printf(Message.DEAL_CARDS_MESSAGE + "%n", formattedNames);
    }

    public void printDealerFirstCard(DealerCardDTO dealerCardDTO) {
        System.out.printf((Message.DEALER_CARDS_MESSAGE) + "%n", dealerCardDTO.getCardDisplay());
    }

    public void printExtraCardRequest(String name) {
        System.out.println(DisplayFormatter.formatExtraCardRequest(name));
    }

    public void printUserCards(List<UserCardsDTO> userCardsDTOS) {
        userCardsDTOS.forEach(dto -> {
            System.out.println(DisplayFormatter.formatUserCardsDisplay(dto));
        });
    }

    public void printProfitResult(ProfitResultDTO profitResultDTO) {
        System.out.println(Message.FINAL_PROFIT_ANNOUNCE);
        DisplayFormatter.formatProfitResult(profitResultDTO).forEach(System.out::println);
    }

    public void printDealerDrawMessage() {
        System.out.println(Message.DEALER_CARD_RECEIVE_ANNOUNCE);
    }

    public void printFinalResult(DealerResultDTO dealerResultDTO, List<UserResultDTO> userResultDTOS) {
        System.out.println(DisplayFormatter.formatDealerResultDisplay(dealerResultDTO));
        userResultDTOS.forEach(dto ->
                System.out.println(DisplayFormatter.formatUserResultDisplay(dto))
        );
    }
}
