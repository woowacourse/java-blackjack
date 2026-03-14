package domain.player;

import java.util.List;

public record ParticipantGameInfo(
        String name,
        List<String> cards,
        int score) {

}
