    package domain.common.exception;

    public class BlackjackGameException extends IllegalArgumentException {

        private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

        public BlackjackGameException(ExceptionInformation exceptionInformation) {
            super(ERROR_MESSAGE_PREFIX + exceptionInformation.getErrorMessage());
        }

    }
