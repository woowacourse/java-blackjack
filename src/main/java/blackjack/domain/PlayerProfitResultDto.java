package blackjack.domain;

public record PlayerProfitResultDto(String playerName, double profit) {

    public static PlayerProfitResultDto from(Player player, Double profit) {
        return new PlayerProfitResultDto(player.getName(), profit);
    }
}
