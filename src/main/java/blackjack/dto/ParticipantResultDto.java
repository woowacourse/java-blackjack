package blackjack.dto;

import blackjack.model.Card;
import blackjack.model.Participant;
import blackjack.model.Players;

import java.util.List;

public class ParticipantResultDto {
    private final String name;
    private final List<String> cardNames;
    private final int score;

    private ParticipantResultDto(String name, List<String> cardNames, int score) {
        this.name = name;
        this.cardNames = cardNames;
        this.score = score;
    }

    public static ParticipantResultDto from(String name, Participant participant) {
        List<String> cardNames = participant.getHandCards().stream()
                .map(Card::getCardName)
                .toList();

        return new ParticipantResultDto(name, cardNames, participant.totalScore());
    }

    public static List<ParticipantResultDto> from(Players players) {
        return players.getPlayers().stream()
                .map(player -> ParticipantResultDto.from(player.getName(), player))
                .toList();
    }

    public String getName() {
        return name;
    }

    public List<String> getCardNames() {
        return cardNames;
    }

    public int getScore() {
        return score;
    }
}
