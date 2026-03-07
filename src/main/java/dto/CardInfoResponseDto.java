package dto;

import java.util.List;
import java.util.Map;

public record CardInfoResponseDto(Map<String, List<String>> gamblersInfo) {
}
