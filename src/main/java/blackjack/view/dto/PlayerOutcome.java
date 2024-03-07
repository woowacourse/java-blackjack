package blackjack.view.dto;

import blackjack.model.referee.Outcome;

public record PlayerOutcome(String name, Outcome outcome) {
}
