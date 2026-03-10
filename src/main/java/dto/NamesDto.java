package dto;

import domain.state.State;
import java.util.List;

public record NamesDto(String dealerName, List<String> playerNames) {
    public static NamesDto fromEntity(State dealerName, List<State> playersState) {
        return new NamesDto(
                dealerName.getParticipantName(),
                playersState.stream().map(State::getParticipantName).toList());
    }
}
