package controller.dto.response;

import java.util.List;

public record InitialCardStatus(
        int initialCardSize,
        List<ParticipantHandStatus> statuses
) {
}
