package view;

import domain.Card;
import domain.Dealer;
import domain.Participants;
import domain.Player;

import java.util.stream.Collectors;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String INIT_FORMAT = LINE_SEPARATOR
            + "딜러와 %s에게 2장을 나누었습니다." + LINE_SEPARATOR;
    private static final String INIT_CARDS_FORMAT = "%s: %s" + LINE_SEPARATOR;

    public void printInitCards(Participants participants) {
        System.out.printf(INIT_FORMAT, String.join(", ", participants.getPlayerNames()));
        printDealerInitCard(participants.getDealer());
        for (Player player : participants.getPlayers()) {
            printPlayerInitCard(player);
        }
        System.out.println();
    }

    private void printDealerInitCard(Dealer dealer) {
        System.out.printf(INIT_CARDS_FORMAT, dealer.getName(),
                convertCard(dealer.getCards()
                                  .get(0)));
    }

    private String convertCard(Card card) {
        return card.getNumber()
                   .getValue() + card.getSuit()
                                     .getValue();
    }

    private void printPlayerInitCard(Player player) {
        String cardNames = player.getCards()
                                 .stream()
                                 .map(this::convertCard)
                                 .collect(Collectors.joining(", "));

        System.out.printf(INIT_CARDS_FORMAT, player.getName(), cardNames);
    }
}
