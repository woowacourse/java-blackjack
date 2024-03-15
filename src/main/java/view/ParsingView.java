package view;

import view.dto.card.CardsDto;
import view.dto.participant.ParticipantDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static view.ResultView.*;

public class ParsingView {
    public String playerNames(final List<ParticipantDto> players) {
        return players.stream()
                      .map(participantDto -> participantDto.nameDto().name())
                      .collect(Collectors.joining(DELIMITER + SPACING));
    }

    public String playerResult(final Map<String, Integer> playerResults) {
        return playerResults.keySet()
                            .stream()
                            .map(player -> player + CONNECT + playerResults.get(player))
                            .collect(Collectors.joining(System.lineSeparator()));
    }

    public String cards(final CardsDto cardsDto) {
        return cardsDto.cardsDto()
                       .stream()
                       .map(card -> card.cardNumber() + card.cardShape())
                       .collect(Collectors.joining(SPACING));
    }
}
