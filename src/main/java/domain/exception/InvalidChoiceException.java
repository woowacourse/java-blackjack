package domain.exception;

/**
 *   YesOrNo class에 대한 커스텀 예외입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class InvalidChoiceException extends IllegalArgumentException {
	public InvalidChoiceException(String choice) {
		super(String.format("Y또는 N중 하나를 입력해야합니다. %s를 입력하셨습니다.", choice));
	}
}
