package domain;

public class DomainException extends RuntimeException{

    private final ExceptionCode exceptionCode;

    public DomainException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
