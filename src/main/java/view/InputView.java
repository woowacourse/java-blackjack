package view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import model.player.Player;

public class InputView {

    private static final String SPLIT_DELIMITER = ",";
    private static final String ASK_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String SUBJECT_FOR_VOWEL = "%s는 ";
    private static final String SUBJECT_FOR_CONSONANT = "%s은 ";
    private static final String ASK_HIT_ANSWER = "한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String REGEXP_VOWEL_ENGLISH = "^[aeiouAEIOU]$";
    private static final char KOREAN_START_LETTER = '가';
    private static final int KOREAN_LETTER_COUNT = 28;
    private static final String NEWLINE = System.lineSeparator();

    private InputView() {
    }

    public static List<String> askPlayerNames() {
        System.out.println(NEWLINE + ASK_PLAYER_NAMES);
        String playerNames = Console.readLine();
        return splitInputByDelimiter(playerNames);
    }

    private static List<String> splitInputByDelimiter(String input) {
        return Arrays.stream(input.split(SPLIT_DELIMITER))
            .map(String::strip)
            .toList();
    }

    public static String askHitAnswer(Player player) {
        System.out.printf(NEWLINE + determineHitAnswerPrompt(player) + NEWLINE, player.getName());
        return Console.readLine().strip();
    }

    private static String determineHitAnswerPrompt(Player player) {
        String name = player.getName();
        char lastLetter = name.charAt(name.length() - 1);
        if (isVowelEnglish(lastLetter) || isVowelKorean(lastLetter)) {
            return SUBJECT_FOR_VOWEL + ASK_HIT_ANSWER;
        }
        return SUBJECT_FOR_CONSONANT + ASK_HIT_ANSWER;
    }

    private static boolean isVowelEnglish(Character lastLetter) {
        return lastLetter.toString().matches(REGEXP_VOWEL_ENGLISH);
    }

    private static boolean isVowelKorean(char lastLetter) {
        int letterOffset = lastLetter - KOREAN_START_LETTER;
        return letterOffset % KOREAN_LETTER_COUNT == 0;
    }
}
