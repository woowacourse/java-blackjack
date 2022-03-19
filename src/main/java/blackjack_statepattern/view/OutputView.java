package blackjack_statepattern.view;

import blackjack_statepattern.card.Card;
import blackjack_statepattern.dto.CardDto;
import blackjack_statepattern.participant.Participant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {

    private static final String COMMA = ", ";

    public static void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printDistributedCards(CardDto cardDto) {
        Map<Participant, List<Card>> participantCards = cardDto.getParticipantCards();
        System.out.println(showParticipantsNames(participantCards.keySet()) + "에게 2장을 나누었습니다.");

        for (Participant participant : participantCards.keySet()) {
            System.out.println(participant.getName() + "카드: " + showCards(participantCards.get(participant)));
        }
    }

    private static String showParticipantsNames(Set<Participant> participants) {
        return participants.stream()
                .map(Participant::getName)
                .collect(Collectors.joining(COMMA));
    }

    private static String showCards(List<Card> cards) {
        return cards.stream()
                .map(OutputView::showCard)
                .collect(Collectors.joining(COMMA));
    }

    private static String showCard(Card card) {
        return card.getDenominationName() + card.getSuitName();
    }
}
