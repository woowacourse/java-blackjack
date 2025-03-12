package blackjack.test_util;

import blackjack.view.writer.Writer;

import java.util.ArrayList;
import java.util.List;

public class WriterStub implements Writer {

    private final List<String> outputs = new ArrayList<>();

    @Override
    public void write(final String message) {
        outputs.add(message);
    }

    public List<String> getOutputs() {
        return outputs;
    }
}
