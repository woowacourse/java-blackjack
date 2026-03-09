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
        List<String> playerNames = Arrays
                .stream(readLine().split(DELIMITER))
                .map(String::trim)
                .toList();
        
        validateNames(playerNames);
        return playerNames;
    }
    
    private void validateNames(List<String> playerNames) {
        validateName(playerNames);
        validateDuplicate(playerNames);
    }
    
    private void validateName(List<String> playerNames) {
        playerNames.forEach(name -> {
            if (name.isEmpty()) {
                throw new IllegalArgumentException("플레이어 이름은 공백이 될 수 없습니다.");
            }
        });
    }
    
    private void validateDuplicate(List<String> playerNames) {
        long distinct = playerNames.stream().distinct().count();
        if (distinct != playerNames.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }
}
