package view;

import common.ErrorMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final String DELIMITER = ",";
    private static final List<String> HIT_OR_STAND = List.of("y", "n");

    public List<String> readNames() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        return Arrays.stream(br.readLine().split(DELIMITER)).toList();
    }

    public String readHitOrStand() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().trim();

        validateHitOrStandValue(input);

        return br.readLine().trim();
    }

    private void validateHitOrStandValue(String input) {
        if (!HIT_OR_STAND.contains(input)) {
            throw new IllegalArgumentException(ErrorMessage.HIT_OR_STAND_VALUE_MIS_MATCH.getMessage());
        }
    }
}
