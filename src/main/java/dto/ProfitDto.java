package dto;

public record ProfitDto(
        String name,
        int profit
) {
    public static ProfitDto fromPlayer(String name, int profit) {
        return new ProfitDto(name, profit);
    }

    public static ProfitDto fromDealer(int profit) {
        return new ProfitDto("딜러", profit);
    }
}
