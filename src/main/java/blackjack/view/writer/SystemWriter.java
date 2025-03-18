package blackjack.view.writer;

public final class SystemWriter implements Writer {
    
    @Override
    public void write(String message) {
        System.out.println(message);
        
    }
}
