import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEAL_MESSAGE = "딜러와 {0}에게 {1}장을 나누었습니다.";
    private static final String SHOW_HAND_MESSAGE = "{0}카드: {1}";

    public void printInitialInfo(List<GameInitialInfoDto> initialInfo) {
        String playerNames = initialInfo.stream()
                .skip(1) // 0번은 딜러
                .map(GameInitialInfoDto::getPlayerName)
                .collect(Collectors.joining(", "));

        System.out.println(MessageFormat.format(
                DEAL_MESSAGE,
                playerNames,
                initialInfo.getFirst().getInitialHandSize()
        ));

        for (GameInitialInfoDto info : initialInfo) {
            System.out.println(MessageFormat.format(
                    SHOW_HAND_MESSAGE,
                    info.getPlayerName(),
                    String.join(", ", info.getHand())
            ));
        }
        System.out.println();
    }


}
