package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String COMMA = ",";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String WITH_BLANK = "\\s+";
    private static final String NO_BLANK = "";

    private InputView(){
    }

    public static List<String> inputNames(){
         String input = deleteWhiteSpaces(SCANNER.nextLine());
         if(input.contains(COMMA)){
            return Arrays.asList(input.split(COMMA));
         }
         throw new IllegalArgumentException("구분자는 콤마로 입력해주세요.");
    }

    public static boolean inputHitYes() {
        String input = deleteWhiteSpaces(SCANNER.nextLine());
        if (YES.equals(input) || NO.equals(input)) {
            return YES.equals(input);
        }
        throw new IllegalArgumentException("y 또는 n으로 입력해주세요.");
    }

    private static String deleteWhiteSpaces(String input){
        return input.replaceAll(WITH_BLANK, NO_BLANK);
    }
}
