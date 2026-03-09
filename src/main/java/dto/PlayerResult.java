package dto;

import java.util.List;

public record PlayerResult(PlayerName name, List<Card> hand, Integer score) {}
