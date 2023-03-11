package DTO;

import domain.card.Card;
import domain.player.Dealer;
import domain.player.Participant;

import java.util.List;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.joining;

public class DTOFactory {
    private static final String DELIMITER = ", ";

    public static DealerDTO createDealerDTO(Dealer dealer) {
        return new DealerDTO(convertCardsFormat(dealer.showCard()));
    }

    public static ParticipantDTO createParticipantDTO(Participant participant) {
        return new ParticipantDTO(participant.getName(), convertCardsFormat(participant.getCards()));
    }

    public static List<ParticipantDTO> createParticipantDTOs(List<Participant> participants) {
        return participants.stream()
                .map(DTOFactory::createParticipantDTO)
                .collect(toList());
    }


    private static String convertCardsFormat(final List<Card> cards) {
        return cards.stream().map(card -> String.format("%s%s", card.getRank().getSymbol(), card.getSuit())).collect(joining(DELIMITER));
    }
}
