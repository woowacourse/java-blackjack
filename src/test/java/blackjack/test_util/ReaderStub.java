package blackjack.test_util;

import blackjack.view.reader.Reader;

public class ReaderStub implements Reader {

    private final String message;

    public ReaderStub(String message) {
        this.message = message;
    }

    @Override
    public String readLine() {
        return message;
    }
}
