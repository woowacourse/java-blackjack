package util;

public class InputHitOptionParser {
    public static HitOption parseHitOption(String inputHitOption) {
        try {
            return HitOption.of(inputHitOption);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException();
        }
    }
}
