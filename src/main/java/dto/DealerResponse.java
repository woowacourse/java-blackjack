package dto;

import java.util.List;

public record DealerResponse(List<CardResponse> cardsResponse, Integer score) {
}
