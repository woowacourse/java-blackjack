package blackjack.view.reader;

import java.util.Scanner;

public class SystemReader implements Reader {
    
    private static final Scanner scanner = new Scanner(System.in);
    
    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
