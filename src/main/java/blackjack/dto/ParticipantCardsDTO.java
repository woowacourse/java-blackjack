package blackjack.dto;

import blackjack.domain.Dealer;
import blackjack.domain.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParticipantCardsDTO {
    private final List<ParticipantStatusDTO> participantCards;

    private ParticipantCardsDTO(final List<ParticipantStatusDTO> participantCards) {
        this.participantCards = participantCards;
    }

    public static ParticipantCardsDTO ofStart(final Dealer dealer, final List<Player> players) {
        List<ParticipantStatusDTO> participantInitialCards = new ArrayList<>();
        participantInitialCards.add(ParticipantStatusDTO.ofStart(dealer));
        players.forEach(player ->
                participantInitialCards.add(ParticipantStatusDTO.ofStart(player)));
        return new ParticipantCardsDTO(participantInitialCards);
    }

    public static ParticipantCardsDTO of(final Dealer dealer, final List<Player> players) {
        List<ParticipantStatusDTO> participantCards = new ArrayList<>();
        participantCards.add(ParticipantStatusDTO.of(dealer));
        players.forEach(player ->
                participantCards.add(ParticipantStatusDTO.of(player)));
        return new ParticipantCardsDTO(participantCards);
    }

    public List<ParticipantStatusDTO> getParticipantCards() {
        return new ArrayList<>(participantCards);
    }

    public List<String> getPlayerNames() {
        return participantCards.subList(1, participantCards.size()).stream()
                .map(ParticipantStatusDTO::getName)
                .collect(Collectors.toUnmodifiableList());
    }
}
