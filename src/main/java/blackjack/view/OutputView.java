package blackjack.view;

import blackjack.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PRINT_FORMAT = "%s: %s";
    private static final String DELIMITER = ", ";
    private static final String ERROR_HEAD = "[ERROR] ";

    public void printInitialStatus(Dealer dealer, Users users) {
        System.out.printf("%s와 %s에게 2장을 나누었습니다.",
                getNameString(dealer.getName()),
                makeUsersNameList(users));
        System.out.print(System.lineSeparator());
        printCardsOf(dealer.getName(), dealer.getFirstCard());
        printUsersCards(users);
    }

    private void printUsersCards(final Users users) {
        for (User user : users.getUsers()) {
            printCardsOf(user.getName(), user.openCards());
        }
    }

    public void printCardsOf(final Name name, final List<Card> cards) {
        System.out.printf(PRINT_FORMAT,
                getNameString(name),
                getCardStringOf(cards));
        System.out.print(System.lineSeparator());
    }

    private String getCardStringOf(final List<Card> cards) {
        return cards.stream()
                .map(card -> makeCardString(card))
                .collect(Collectors.joining(DELIMITER));
    }

    private String makeCardString(final Card card) {
        return String.format("%s%s",
                translate(card.getCardNumberValue()),
                translate(card.getShape()));
    }

    private String translate(final int cardNumberValue) {
        if (cardNumberValue == 1) {
            return "A";
        }
        if (cardNumberValue == 11) {
            return "J";
        }
        if (cardNumberValue == 12) {
            return "Q";
        }
        if (cardNumberValue == 13) {
            return "K";
        }
        return String.valueOf(cardNumberValue);
    }

    private String translate(final Shape shape) {
        if (shape == Shape.HEART) {
            return "하트";
        }
        if (shape == Shape.DIAMOND) {
            return "다이아몬드";
        }
        if (shape == Shape.CLOVER) {
            return "클로버";
        }
        if (shape == Shape.SPADE) {
            return "스페이드";
        }
        throw new AssertionError();
    }

    private String makeUsersNameList(Users users) {
        return users.getUsers()
                .stream()
                .map(user -> user.getName().getValue())
                .collect(Collectors.joining(DELIMITER));
    }

    private String getNameString(final Name name) {
        return name.getValue();
    }

    public void printException(final Exception exception) {
        System.out.println(ERROR_HEAD + exception.getMessage());
    }

    public void printAdditionalCardCountOfDealer(final int cardCount) {
        if (cardCount == 0) {
            System.out.println("딜러는 17 이상이라 카드를 받지 못했습니다.");
        }
        if (cardCount > 0) {
            System.out.printf("딜러는 16 이하라 %d장의 카드를 더 받았습니다.", cardCount);
        }
    }
}
