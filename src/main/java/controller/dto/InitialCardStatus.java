package controller.dto;

import java.util.List;

public record InitialCardStatus(
        int initialCardSize,
        List<ParticipantHandStatus> statuses
) {
}
