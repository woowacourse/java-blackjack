package dto;

import java.util.List;

public record GameResult(List<PlayerResult> playerResults, PlayerResult dealerResult) {
}
