package dto;

public record PlayerProfitDto(
        String name,
        int profit
) {
    public static PlayerProfitDto of(String name, int profit) {
        return new PlayerProfitDto(name, profit);
    }
}
