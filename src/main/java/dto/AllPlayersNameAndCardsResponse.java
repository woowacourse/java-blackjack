package dto;

import domain.vo.NameAndCardInfos;
import java.util.List;

public record AllPlayersNameAndCardsResponse(List<NameAndCardsResponse> allInfos) {

    public static AllPlayersNameAndCardsResponse from(List<NameAndCardInfos> players) {
        return new AllPlayersNameAndCardsResponse(responses(players));
    }

    private static List<NameAndCardsResponse> responses(List<NameAndCardInfos> players) {
        return players.stream()
                .map(NameAndCardsResponse::from)
                .toList();
    }
}
