package domain.player;

import dto.CardResponse;
import java.util.List;
import java.util.function.BiConsumer;

public interface ActionAfterPlayerHit extends BiConsumer<String, List<CardResponse>> {
}
