package blackjack.view.writer;

public class SystemWriter implements Writer {
    
    @Override
    public void write(String message) {
        System.out.println(message);
        
    }
}
