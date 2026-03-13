package dto;

public record CardInfoDto(String numberDisplayName, String shape) {

    public static CardInfoDto from(String numberDisplayName, String shape) {
        return new CardInfoDto(numberDisplayName, shape);
    }
}
