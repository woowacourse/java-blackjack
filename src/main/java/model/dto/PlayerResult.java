package model.dto;

import java.util.List;

public record PlayerResult(String name, List<Card> deck, Integer score) {}
