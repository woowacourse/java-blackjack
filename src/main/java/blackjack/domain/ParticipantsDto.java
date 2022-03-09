package blackjack.domain;

import java.util.List;

public class ParticipantsDto {
    private static final String NAME_DELIMITER = ": ";
    private static final String CARD_DELIMITER = ", ";
    private static final int FIRST_CARD = 0;

    private final Participant dealer;
    private final List<Participant> players;

    public ParticipantsDto(Participant dealer, List<Participant> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static ParticipantsDto from(Participants participants) {
        return new ParticipantsDto(participants.getDealer(), participants.getPlayers());
    }

    public String getDealerOpenCard() {

        return dealer.getName() + NAME_DELIMITER + dealer.getCards().get(FIRST_CARD).getName();
    }

    public String getPlayerCardStatus(Participant participant) {
        String[] playerCardStatus = participant.getCards().stream()
            .map(Card::getName)
            .toArray(String[]::new);
        return participant.getName() + NAME_DELIMITER + String.join(CARD_DELIMITER, playerCardStatus);
    }

    public Participant getDealer() {
        return dealer;
    }

    public List<Participant> getPlayers() {
        return players;
    }
}
