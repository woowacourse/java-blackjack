package domain.dto;

import java.util.List;

public record OpenCardsResponse(
        PlayerResponse dealer,
        List<PlayerResponse> participants
) {
    
}
