package view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import model.blackjackgame.HitAnswer;
import model.player.Player;
import model.player.Players;

public class InputView {

    private static final String NEWLINE = System.lineSeparator();
    private static final String SPLIT_DELIMITER = ",";
    private static final String ASK_PLAYER_NAMES = NEWLINE + "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String SUBJECT_FOR_VOWEL = "%s는 ";
    private static final String SUBJECT_FOR_CONSONANT = "%s은 ";
    private static final String ASK_HIT_ANSWER = "한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + NEWLINE;
    private static final String REGEXP_VOWEL_ENGLISH = "^[aeiouAEIOU]$";
    private static final char KOREAN_START_LETTER = '가';
    private static final int KOREAN_LETTER_COUNT = 28;

    private InputView() {
    }

    public static Players preparePlayers() {
        return retryOnException(() -> {
            List<String> playerNames = askPlayerNames();
            return Players.from(playerNames);
        });
    }

    public static List<String> askPlayerNames() {
        System.out.println(ASK_PLAYER_NAMES);
        String playerNames = Console.readLine();
        return splitInputByDelimiter(playerNames);
    }

    private static List<String> splitInputByDelimiter(String input) {
        return Arrays.stream(input.split(SPLIT_DELIMITER))
            .map(String::strip)
            .toList();
    }

    public static HitAnswer prepareHitAnswer(Player player) {
        return retryOnException(() -> {
            String hitAnswer = askHitAnswer(player);
            return HitAnswer.of(hitAnswer);
        });
    }

    public static String askHitAnswer(Player player) {
        System.out.printf(determineHitAnswerPrompt(player), player.getName());
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

    private static <T> T retryOnException(Supplier<T> retryOperation) {
        try {
            return retryOperation.get();
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return retryOnException(retryOperation);
        }
    }
}
