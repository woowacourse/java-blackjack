package view;

import domain.Card;
import domain.Dealer;
import domain.GameSummary;
import domain.User;
import java.util.List;
import java.util.stream.Collectors;

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

    public void printDealerFirstCard(Card card) {
        printMessage(String.format(Message.DEALER_CARDS_MESSAGE, formatCard(card)));
    }

    public void printDealerReceivedCard() {
        printMessage(Message.DEALER_CARD_RECEIVE_ANNOUNCE);
    }

    public void printUserCards(User user) {
        printMessage(user.getName() + "카드: " + formatCards(user.getCards()));
    }

    public void printDealerFinalHand(Dealer dealer) {
        String cards = String.format(Message.DEALER_FINAL_CARDS_MESSAGE, formatCards(dealer.getCards()));
        printMessage(cards + " - 결과: " + dealer.getScore());
    }

    public void printUserFinalHand(User user) {
        printMessage(user.getName() + "카드: " + formatCards(user.getCards()) + " - 결과: " + user.getScore());
    }

    public void printWinningResults(GameSummary summary) {
        printMessage("딜러: " + summary.getDealerProfit());
        summary.getUserResults().keySet()
                .forEach(name -> printMessage(name + ": " + summary.getUserProfit(name)));
    }

    private String formatCard(Card card) {
        return card.getRankName() + card.getSuitName();
    }

    private String formatCards(List<Card> cards) {
        return cards.stream()
                .map(this::formatCard)
                .collect(Collectors.joining(", "));
    }

    public void printAskBetAmount(String name) {
        printMessage(String.format(Message.INPUT_BET_AMOUNT_MESSAGE, name));

    }
}
