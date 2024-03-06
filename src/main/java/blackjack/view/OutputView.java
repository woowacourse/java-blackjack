package blackjack.view;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Players;

import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String HAND_OUT_MESSAGE = "%s와 %s에게 2장을 나누었습니다";

    public void printInitialCards(Participants participants) {
        Dealer dealer = participants.getDealer();
        Players players = participants.getPlayers();
        printHandOutMessage(dealer, players);
    }

    public void printException(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }

    private void printHandOutMessage(Dealer dealer, Players players) {
        String playersName = formatPlayersName(players);
        String handOutMessage = String.format(HAND_OUT_MESSAGE, dealer.getName(), playersName);
        System.out.println(handOutMessage);
    }

    private String formatPlayersName(Players players) {
        return players.getValues()
                .stream()
                .map(Participant::getName)
                .collect(Collectors.joining(DELIMITER));
    }
}
