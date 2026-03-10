package view;

import java.util.List;

public class OutputView {
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printAskExtraCard(String name) {
        System.out.printf((Message.REQUEST_GET_EXTRA_CARD) + "%n", name);
    }

    public void printDealComplete(List<String> userNames) {
        printMessage(String.format(Message.DEAL_CARDS_MESSAGE, String.join(", ", userNames)));
    }

    public void printDealerFirstCard(String cardDisplay) {
        printMessage(String.format(Message.DEALER_CARDS_MESSAGE, cardDisplay));
    }

    public void printDealerReceivedCard() {
        printMessage(Message.DEALER_CARD_RECEIVE_ANNOUNCE);
    }

    public void printDealerFinalHand(String dealerHandDisplay) {
        printMessage(String.format(Message.DEALER_FINAL_CARDS_MESSAGE, dealerHandDisplay));
    }
}