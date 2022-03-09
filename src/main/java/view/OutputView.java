package view;

import domain.card.Card;
import domain.participant.Participant;
import domain.participant.Participants;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String COMMA = ", ";
    private static final String PARTICIPANTS_INITIAL_HAND_OUT_MESSAGE =
        "%s에게 2장의 카드를 나누었습니다." + System.lineSeparator();
    private static final String DELIMITER = ": ";

    private static final OutputView OUTPUT_VIEW = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return OUTPUT_VIEW;
    }

    public void showInitialTurnStatus(Participants participants) {
        List<String> participantNames = participants.toNames();
        System.out.printf(PARTICIPANTS_INITIAL_HAND_OUT_MESSAGE,
            String.join(COMMA, participantNames));

        List<Participant> gameParticipants = participants.getParticipants();

        showInitialDealerCardStatus( gameParticipants.get(0));

        for (Participant participant : gameParticipants.subList(1, gameParticipants.size())) {
            showParticipantCardStatus(participant);
        }
    }

    public void showInitialDealerCardStatus(Participant participant) {
        String dealerResult =
            participant.getName() + DELIMITER + getDeck(participant.getCards().subList(0, 1));
        System.out.println(dealerResult);

    }

    public void showParticipantCardStatus(Participant participant) {
        String cardStatus = participant.getName()
            + DELIMITER
            + getDeck(participant.getCards());

        System.out.println(cardStatus);
    }

    private String getDeck(List<Card> cards) {
        List<String> cardRepresentation = cards.stream()
            .map(card -> card.getDenomination() + card.getSymbol())
            .collect(Collectors.toList());

        return String.join(COMMA, cardRepresentation);
    }
}
