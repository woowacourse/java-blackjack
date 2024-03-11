package domain.game;

import controller.dto.HandStatus;
import java.util.function.Consumer;

public interface ActionAfterPick extends Consumer<HandStatus> {
}
