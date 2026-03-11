package dto;

import domain.MatchResult;
import java.util.List;

public record GameResult(
        String name,
        List<MatchResult> result
) {
}
