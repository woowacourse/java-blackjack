package model.dto;

import java.util.List;

public record PlayerResult(String name, List<String> deck, Integer score, Integer bet) {}
