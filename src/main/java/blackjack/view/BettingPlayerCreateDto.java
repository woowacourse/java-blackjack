package blackjack.view;

public record BettingPlayerCreateDto(
        String name,
        int stake
) {
    public static BettingPlayerCreateDto of(String name, int stake) {
        return new BettingPlayerCreateDto(name, stake);
    }
}
