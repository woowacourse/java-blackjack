package view;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import model.player.Dealer;
import model.player.Player;

public class OutputView {

    private static final String DIVIDE_CARD_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String RECEIVED_CARD_MESSAGE = "%s : %s";

    public void printStartBlackJack(List<Player> players, Dealer dealer) {
        printPlayerNames(players);
        printPlayerCards(players, dealer);
    }

    private void printPlayerNames(List<Player> players) {
        String names = String.join(", ", players.stream().map(Player::getName).toList());
        System.out.println(System.lineSeparator() + DIVIDE_CARD_MESSAGE.formatted(names) + System.lineSeparator());
    }

    private void printPlayerCards(List<Player> players, Dealer dealer) {
        printPlayerCard(dealer, 1, "");
        for (Player player : players) {
            printPlayerCard(player, "카드");
        }
    }

    private void printPlayerCard(Player player, String nameSuffix) {
        printPlayerCard(player, player.getCards().size(), nameSuffix);
    }

    private void printPlayerCard(Player player, int printCardCounts, String nameSuffix) {
        List<Card> cards = player.getCards();
        int skipCount = cards.size() - printCardCounts;
        String cardNames = String.join(", ", cards.stream().skip(skipCount).map(this::cardToString).toList());
        System.out.println(RECEIVED_CARD_MESSAGE.formatted(player.getName() + nameSuffix, cardNames));
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
