package controller.dto;

import domain.Hand;

public record HandStatus(
        String name,
        Hand hand
) {
}
