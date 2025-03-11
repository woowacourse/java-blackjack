package blackjack.test_util;

import java.util.ArrayList;
import java.util.List;

import blackjack.view.writer.Writer;

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
