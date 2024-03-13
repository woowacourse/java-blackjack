package dto;

import java.util.List;

public record PlayerResponse(String name, List<CardResponse> cardResponse, Integer score) {
}
