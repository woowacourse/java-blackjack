package controller.dto;

import domain.participant.Hand;

public record HandStatus(
        String name,
        Hand hand
) {
}
