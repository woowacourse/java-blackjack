package model.dto;

import java.util.List;
import model.PlayerName;

public record PlayerResult(PlayerName name, List<Card> deck, Integer score) {}
