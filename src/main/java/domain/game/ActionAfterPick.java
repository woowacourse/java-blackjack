package domain.game;

import controller.dto.ParticipantHandStatus;
import java.util.function.Consumer;

public interface ActionAfterPick extends Consumer<ParticipantHandStatus> {
}
