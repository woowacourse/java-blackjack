package controller.dto;

import java.util.List;

public record CardsStatus(List<CardStatus> status) {
    public List<String> getPlayerNames() {
        return status.subList(1, status.size()).stream()
                .map(CardStatus::name)
                .toList();
    }
}
