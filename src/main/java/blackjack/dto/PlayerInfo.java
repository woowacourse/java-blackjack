package blackjack.dto;

import blackjack.domain.participant.Betting;

public record PlayerInfo(String name, Betting betting) {

    public static PlayerInfo of(String name, String amount) {
        return new PlayerInfo(name, new Betting(amount));
    }
}
