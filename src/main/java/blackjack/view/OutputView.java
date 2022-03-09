package blackjack.view;

import blackjack.domain.BlackJackGame;
import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Gamer;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PRINT_OPEN_CARD_PREFIX_MESSAGE = "\n%s와 ";
    private static final String PRINT_OPEN_CARD_SUFFIX_MESSAGE = "에게 2장의 카드를 나누었습니다.\n";
    private static final String PRINT_JOINING_DELIMITER = ", ";
    private static final String PRINT_OPEN_CARD_FORMAT_MESSAGE = "%s: %s\n";
    private static final String PRINT_SHOW_CARD_FORMAT_MESSAGE = "%s카드: %s\n";

    public static void printOpenCards(final BlackJackGame blackJackGame) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(PRINT_OPEN_CARD_PREFIX_MESSAGE, Dealer.DEALER_NAME))
                .append(joinNames(blackJackGame))
                .append(PRINT_OPEN_CARD_SUFFIX_MESSAGE);

        List<Card> cards = blackJackGame.getDealer().openCards();
        stringBuilder.append(String.format(PRINT_OPEN_CARD_FORMAT_MESSAGE, Dealer.DEALER_NAME, joinCards(cards)));
        appendGamerFormat(blackJackGame.getGamers(), stringBuilder);

        System.out.println(stringBuilder);
    }

    private static String joinNames(BlackJackGame blackJackGame) {
        return blackJackGame.getGamers().stream()
                .map(Gamer::getName)
                .collect(Collectors.joining(PRINT_JOINING_DELIMITER));
    }

    private static String joinCards(final List<Card> cards) {
        return cards.stream()
                .map(OutputView::printCard)
                .collect(Collectors.joining(PRINT_JOINING_DELIMITER));
    }

    private static void appendGamerFormat(final List<Gamer> gamers, final StringBuilder stringBuilder) {
        for (Gamer gamer : gamers) {
            stringBuilder.append(String.format(PRINT_SHOW_CARD_FORMAT_MESSAGE,
                    gamer.getName(),
                    joinCards(gamer.openCards())));
        }
    }

    private static String printCard(final Card card) {
        return card.getDenomination().getName() + card.getSuit().getName();
    }

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }

    public static void printCards(Gamer gamer) {
        System.out.printf(PRINT_SHOW_CARD_FORMAT_MESSAGE,
                gamer.getName(),
                joinCards(gamer.showCards()));
    }
}
