package blackjack.domain;

import java.util.List;

public class BlackjackDto {
    private static final String NAME_DELIMITER = ": ";
    private static final String CARD_DELIMITER = ", ";
    private static final int FIRST_CARD = 0;

    private final Participant dealer;
    private final List<Participant> players;

    public BlackjackDto(Participant dealer, List<Participant> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackjackDto from(Blackjack blackjack) {
        return new BlackjackDto(blackjack.getDealer(), blackjack.getPlayers());
    }

    public String getDealerOpenCard() {

        return dealer.getName() + NAME_DELIMITER + dealer.getCards().get(FIRST_CARD).getName();
    }

    public String getPlayerCardStatus(Participant participant) {
        String[] playerCardStatus = participant.getCards().stream()
            .map(card -> card.getName())
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
