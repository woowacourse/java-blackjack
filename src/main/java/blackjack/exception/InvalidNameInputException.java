package blackjack.exception;

import java.io.IOException;

public class InvalidNameInputException extends IOException {
    public InvalidNameInputException(String message) {
        super(message);
    }
}
