package BlackJack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static String input(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static List<String> inputPlayerNames(){
        String[] input = input().split(",");
        return Arrays.stream(input)
                .collect(Collectors.toList());
    }
}
