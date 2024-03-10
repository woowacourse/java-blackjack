package controller.dto;

import java.util.List;

public record GameResult(
        List<PlayerResult> results
) {
}
