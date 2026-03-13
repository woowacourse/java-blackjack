
package dto;

import java.util.List;

public record GameResultDto(List<PlayerResultDto> playerResultDtos, DealerResultDto dealerResultDto) {
}
