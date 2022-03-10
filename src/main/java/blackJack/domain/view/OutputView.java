package blackJack.domain.view;

import java.util.List;
import java.util.stream.Collectors;

import blackJack.domain.card.Card;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participant;
import blackJack.domain.participant.Player;

public class OutputView {

    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String JOINING_DELIMITER = ", ";
    private static final String OUTPUT_MESSAGE_INIT_CARD_RESULT =
        NEWLINE.concat("%s와 %s에게 2장의 카드를 나누었습니다.").concat(NEWLINE);
    private static final String OUTPUT_MESSAGE_PARTICIPANT_HOLD_CARD =
        "%s 카드: %s".concat(NEWLINE);

    public static void printInitCardResult(Dealer dealer, List<Player> players) {
        printInitCardMessage(dealer, players);
        printHoldCardMessage(dealer, players);
        System.out.println();
    }

    private static void printInitCardMessage(Dealer dealer, List<Player> players) {
        String playerNames = players.stream()
            .map(Participant::getName)
            .collect(Collectors.joining(JOINING_DELIMITER));
        System.out.printf(OUTPUT_MESSAGE_INIT_CARD_RESULT, dealer.getName(), playerNames);
    }

    private static void printHoldCardMessage(Dealer dealer, List<Player> players) {
        Card firstDealerCard = dealer.getCards().get(0);
        String dealerCardInfo = firstDealerCard.getDenominationName() + firstDealerCard.getSymbolName();
        System.out.printf(OUTPUT_MESSAGE_PARTICIPANT_HOLD_CARD, dealer.getName(), dealerCardInfo);

        for (Player player : players) {
            String playerCardsInfo = player.getCards().stream()
                .map(card -> card.getDenominationName() + card.getSymbolName())
                .collect(Collectors.joining(JOINING_DELIMITER));
            System.out.printf(OUTPUT_MESSAGE_PARTICIPANT_HOLD_CARD, player.getName(), playerCardsInfo);
        }
    }
}
