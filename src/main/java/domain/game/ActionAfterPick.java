package domain.game;

import controller.dto.response.ParticipantHandStatus;
import java.util.function.Consumer;

public interface ActionAfterPick extends Consumer<ParticipantHandStatus> {
}
