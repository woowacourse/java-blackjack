package domain.card.exception;

import domain.common.exception.ExceptionInformation;

public enum ErrorMessage implements ExceptionInformation {
    CARD_DECK_IS_EMPTY_ERROR("카드덱이 비어있습니다."),
    ;

    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
