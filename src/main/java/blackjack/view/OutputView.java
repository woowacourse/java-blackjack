package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Players;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String HAND_OUT_MESSAGE = System.lineSeparator() + "%s와 %s에게 2장을 나누었습니다";
    private static final String PARTICIPANT_HAND = "%s카드: %s";

    public void printInitialCards(Participants participants) {
        Dealer dealer = participants.getDealer();
        Players players = participants.getPlayers();
        printHandOutMessage(dealer, players);
        printParticipantsHand(dealer, players);
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

    private String getCardSignatures(List<Card> cards) {
        return cards.stream()
                .map(Card::getSignature)
                .collect(Collectors.joining(DELIMITER));
    }

    private String getParticipantHand(Participant participant) {
        String cardSignatures = getCardSignatures(participant.getCards());
        String participantName = participant.getName();
        return String.format(PARTICIPANT_HAND, participantName, cardSignatures);
    }

    private void printParticipantsHand(Dealer dealer, Players players) {
        System.out.println(getParticipantHand(dealer));
        players.getValues()
                .stream()
                .map(this::getParticipantHand)
                .forEach(System.out::println);
    }
}
