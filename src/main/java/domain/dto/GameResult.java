package domain.dto;

import java.util.List;

public record GameResult(
        String name,
        List<Boolean> result
) {
}
