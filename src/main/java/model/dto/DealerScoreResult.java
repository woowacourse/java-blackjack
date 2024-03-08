package model.dto;

import java.util.EnumMap;

public record DealerScoreResult(EnumMap<Victory, Integer> scoreStorage) {
}
