package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.dto.ParticipantCards;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PROVIDE_PARTICIPANTS_FIRST_CARD_MESSAGE = "%s와 %s에게 2장을 나누었습니다.%n";
    private static final String PROVIDED_CARD_TO_DEALER_CARD_MESSAGE = "%s: %s%n";
    private static final String PROVIDED_CARD_TO_PLAYER_CARD_MESSAGE = "%s 카드: %s%n";

    private static final String PLAYER_NAME_DELIMITER = ", ";
    private static final String CARD_DELIMITER = ", ";

    public OutputView() {
        throw new AssertionError();
    }

    public static void printParticipantsFirstCards(final ParticipantCards dealerCards,
                                                   final List<ParticipantCards> playerCards) {
        System.out.printf(PROVIDE_PARTICIPANTS_FIRST_CARD_MESSAGE, dealerCards.getName(), joinPlayerNames(playerCards));
        System.out.printf(PROVIDED_CARD_TO_DEALER_CARD_MESSAGE,
                dealerCards.getName(), jointParticipantCards(dealerCards.getCards()));
        playerCards.forEach(OutputView::printPlayerCards);
    }

    private static String joinPlayerNames(final List<ParticipantCards> playerCards) {
        return playerCards.stream()
                .map(ParticipantCards::getName)
                .collect(Collectors.joining(PLAYER_NAME_DELIMITER));
    }

    private static String jointParticipantCards(final List<Card> cards) {
        return cards.stream()
                .map(card -> joinCard(card.getSuit(), card.getDenomination()))
                .collect(Collectors.joining(CARD_DELIMITER));
    }

    private static String joinCard(final Suit suit, final Denomination denomination) {
        return denomination.getPrintValue() + suit.getName();
    }

    public static void printPlayerCards(final ParticipantCards playerCards) {
        System.out.printf(PROVIDED_CARD_TO_PLAYER_CARD_MESSAGE,
                playerCards.getName(), jointParticipantCards(playerCards.getCards()));
    }
}
