package blackjack.domain.dto;

import blackjack.domain.BlackJack;
import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackDto {
    private static final String CARD_DELIMITER = ", ";
    private static final int FIRST_CARD = 0;

    private final Participants participants;

    public BlackJackDto(Participants participants) {
        this.participants = participants;
    }

    public static BlackJackDto from(BlackJack blackJack) {
        return new BlackJackDto(blackJack.getParticipants());
    }

    public String getDealerOpenCard() {
        Participant dealer = participants.getDealer();
        return String.format("%s: %s", dealer.getName(), dealer.getCards().get(FIRST_CARD).getName());
    }

    public String getPlayerCardStatus(Participant participant) {
        String[] playerCardStatus = participant.getCards().stream()
            .map(Card::getName)
            .toArray(String[]::new);

        return String.format("%s: %s", participant.getName(), String.join(CARD_DELIMITER, playerCardStatus));
    }

    public String getDealerRevenue(Result result) {
        return String.format("%s: %d", participants.getDealer().getName(), result.getDealerRevenue());
    }

    public List<String> getPlayersRevenue(Result result) {
        return participants.getPlayers().stream()
            .map(player -> String.format("%s: %d", player.getName(), result.getPlayerRevenue(player)))
            .collect(Collectors.toList());
    }

    public Participants getParticipants() {
        return participants;
    }

    public List<Participant> getPlayers() {
        return participants.getPlayers();
    }

    public Participant getDealer() {
        return participants.getDealer();
    }
}
