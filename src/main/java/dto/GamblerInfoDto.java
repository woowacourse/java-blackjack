package dto;

import domain.player.Gambler;

public record GamblerInfoDto(
        String name,
        int amount
) {
    public Gambler toGambler() {
        return new Gambler(name, amount);
    }
}
