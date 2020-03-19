package second.domain.gamer;

public class InvalidMoneyException extends IllegalArgumentException {
    public InvalidMoneyException() {
        super();
    }

    public InvalidMoneyException(String s) {
        super(s);
    }
}
