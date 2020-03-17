package domains.user;

public class InvalidPlayersException extends IllegalArgumentException {
    public static final String DUPLICATION = "플레어이간에 중복된 이름이 존재합니다.";
    public static final String NULL = "null은 입력할 수 없습니다.";
    public static final String OVER_NUMBER_OF_PLAYERS = "플레이어는 5명을 넘을 수 없습니다.";

    public InvalidPlayersException(String s) {
        super(s);
    }
}
