package blackjack.view.reader;

import java.util.Iterator;
import java.util.List;

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
