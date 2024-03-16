package dto;

import java.util.List;

public record ParticipantsResponse(DealerResponse dealerResponse, List<PlayerResponse> playerResponse) {
}
