package dto;

public record CardInfoDto(int number, String shapeKoreanName) {

    public static CardInfoDto from(int number, String shapeKoreanName) {
        return new CardInfoDto(number, shapeKoreanName);
    }
}
