package view;

import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import model.player.Dealer;
import model.player.Player;

public class OutputView {

    public void printStartBlackJack(List<Player> players, Dealer dealer) {
        System.out.print(System.lineSeparator() + "딜러와 ");

        String names = String.join(", ", players.stream().map(Player::getName).toList());
        System.out.print(names);

        System.out.println("에게 2장을 나누었습니다.");
        Card dealerCard = dealer.getCards().get(0);
        System.out.println(dealer.getName() + ": " + cardToString(dealerCard));

        for (Player player : players) {
            List<Card> cards = player.getCards();
            System.out.println(player.getName() + ": " + cardToString(cards.get(0)) + "," + cardToString(cards.get(1)));
        }
    }

    private String cardToString(Card card) {
        String cardNumber = cardNumberToString(card.getCardNumber());
        String cardShape = cardShapeToString(card.getCardShape());
        return cardNumber + cardShape;
    }

    private String cardNumberToString(CardNumber cardNumber) {//TODO : enumMap
        if (cardNumber == CardNumber.ACE) {
            return "A";
        }
        if (cardNumber == CardNumber.QUEEN) {
            return "Q";
        }
        if (cardNumber == CardNumber.KING) {
            return "K";
        }
        if (cardNumber == CardNumber.JACK) {
            return "J";
        }
        return String.valueOf(cardNumber.getNumber());
    }

    private String cardShapeToString(CardShape cardShape) {//TODO : enumMap
        if (cardShape == CardShape.SPACE) {
            return "스페이드";
        }
        if (cardShape == CardShape.CLOVER) {
            return "클로버";
        }
        if (cardShape == CardShape.HEART) {
            return "하트";
        }
        return "다이아몬드";
    }

}
