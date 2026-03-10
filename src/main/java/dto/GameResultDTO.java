package dto;

import java.util.List;

public record GameResultDTO(List<PlayerResultDTO> playerResultDTOs, DealerResultDTO dealerResultDTO) {
}
