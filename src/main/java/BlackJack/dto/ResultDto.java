package BlackJack.dto;

public class ResultDto {
    private UserDto userDto;
    private int score;

    public ResultDto(UserDto userDto, int score) {
        this.userDto = userDto;
        this.score = score;
    }

    public static ResultDto from(UserDto userDto, int score){
        return new ResultDto(userDto, score);
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return userDto.getName();
    }
}
