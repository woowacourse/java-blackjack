package blackjack.view.input.reader;

import java.util.Iterator;
import java.util.List;

import blackjack.view.input.reader.Reader;

public class CustomReader implements Reader {

    private Iterator<String> inputLines;

    public void initTest(final String inputLine) {
        this.inputLines = List.of(inputLine).iterator();
    }

    @Override
    public String readLine() {
        return inputLines.next();
    }

}
