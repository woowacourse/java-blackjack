package blackjack.controller;

import blackjack.view.writer.Writer;

public class ExceptionMessagePrintControllerProxy implements Controller {
    
    private final Controller target;
    private final Writer writer;
    
    public ExceptionMessagePrintControllerProxy(final Controller target, final Writer writer) {
        this.target = target;
        this.writer = writer;
    }
    
    @Override
    public void run() {
        try {
            target.run();
        } catch (Exception e) {
            writer.write("[ERROR] " + e.getMessage());
            throw new IllegalStateException(e);
        }
    }
}
