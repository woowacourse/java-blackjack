package model;

import exception.IllegalYesOrNoInputException;
import utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class YesOrNo {
    private static Map<String, YesOrNo> cache = new HashMap<>();

    private String yesOrNo;

    static {
        cache.put("y", new YesOrNo("Y"));
        cache.put("n", new YesOrNo("N"));
    }

    private YesOrNo(String input) {
        this.yesOrNo = StringUtils.trimString(input);
    }

    public static YesOrNo of(String input) {
        validate(input);
        return cache.get(input.toLowerCase());
    }

    private static void validate(String input) {
        StringUtils.validateNull(input);
        StringUtils.validateEmpty(input);
        validateYesOrNo(input);
    }

    private static void validateYesOrNo(String input) {
        if (!(isYes(input) || isNo(input))) {
            throw new IllegalYesOrNoInputException("Y 또는 N를 입력해 주세요.");
        }
    }

    private static boolean isNo(String input) {
        return input.equalsIgnoreCase("N");
    }

    private static boolean isYes(String input) {
        return input.equalsIgnoreCase("Y");
    }

    public boolean getYesOrNo() {
        return isYes(yesOrNo);
    }
}

