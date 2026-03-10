package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    
    
    private static final String DELIMITER = ",";
    
    private final Scanner scanner = new Scanner(System.in);
    
    public String readLine() {
        return scanner.nextLine();
    }
    
    public List<String> parsePlayerNames() {
        return Arrays.stream(readLine().split(DELIMITER))
                .map(String::trim)
                .toList();
    }
    
    public boolean getUserCommand() {
        return UserCommand.from(readLine()) == UserCommand.YES;
    }
}
