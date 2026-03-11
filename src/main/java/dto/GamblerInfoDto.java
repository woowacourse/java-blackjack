package dto;

public record GamblerInfoDto(
        String name,
        int betting
) {
    public static GamblerInfoDto from(String name, int betting){
        return new GamblerInfoDto(name, betting);
    }
}
