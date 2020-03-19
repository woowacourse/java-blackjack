package domain.exception;

/**
 *   YesOrNo class에 대한 커스텀 예외입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class InvalidChoiceException extends IllegalArgumentException {
	public InvalidChoiceException(String message) {
		super(message);
	}
}
