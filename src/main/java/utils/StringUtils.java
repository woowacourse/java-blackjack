package utils;

import exception.IllegalStringInputException;

import java.util.Objects;

public class StringUtils {

    public static void validateString(String input){
        validateNull(input);
        validateEmpty(input);
    }

    public static void validateEmpty(String input) {
        if (input.trim().isEmpty()) {
            throw new IllegalStringInputException("입력은 한 글자 이상이어야 합니다.");
        }
    }

    public static void validateNull(String input) {
        if (Objects.isNull(input)) {
            throw new IllegalStringInputException("Null 값은 입력하실 수 없습니다.");
        }
    }

    public static String trimString(String input) {
        return input.replaceAll(" ", "");
    }
}
