package domains.user;

public class InvalidPlayersException extends IllegalArgumentException {
	public static final String DUPLICATION = "플레어이간에 중복된 이름이 존재합니다.";
	static final String NULL = "null은 입력할 수 없습니다.";

	InvalidPlayersException(String s) {
		super(s);
	}
}
