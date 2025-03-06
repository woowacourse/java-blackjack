package view;

import domain.dto.OpenCardsResponse;
import domain.dto.PlayerResponse;
import java.util.List;

public class OutputView {
    public static void printAnnounceInitialCardsDistribution(OpenCardsResponse openCardsResponse) {
        List<String> names = openCardsResponse.participants().stream()
                .map(PlayerResponse::name)
                .toList();

        System.out.printf("%s와 %s에게 2장을 나누었습니다.", openCardsResponse.dealer().name(), String.join(", ", names));
    }
}
