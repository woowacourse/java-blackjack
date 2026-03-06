package model.dto;

import java.util.List;

public record PlayerResult(PlayerName name, List<Card> deck, Integer score) {}
