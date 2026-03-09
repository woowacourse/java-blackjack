package dto;

import java.util.List;

public record PlayerResult(String name, List<Card> hand, Integer score) {}
