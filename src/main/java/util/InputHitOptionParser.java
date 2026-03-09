package util;

public class InputHitOptionParser {

    private InputHitOptionParser() {
    }

    public static HitOption parseHitOption(String inputHitOption) {
        return HitOption.of(inputHitOption);
    }
}
