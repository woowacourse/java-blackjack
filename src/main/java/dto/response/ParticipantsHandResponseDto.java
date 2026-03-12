package dto.response;

import java.util.List;
import java.util.Map;

public record ParticipantsHandResponseDto(Map<String, List<String>> gamblersInfo) {
}
