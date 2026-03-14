package team.blackjack.service.dto;

public record PlayerRequest(
        String name,
        Integer stake
) {
}
