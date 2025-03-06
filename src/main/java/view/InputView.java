package view;

import domain.card.Card;
import domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final String PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private final String HIT_CARDS = "딜러와 %s에게 2장을 나누었습니다.";
    private final String PARTICIPANT_CARDS = "%s카드: ";
    private final String ASK_HIT_OR_STAND = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private final String HIT_DEALER_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private final String SCORE = " - 결과: %d";
    private final String RESULT_INTRO = "## 최종 승패";
    private final String DEALER_RESULT = "딜러: %d승 %d패";
    private final String WINNER_RESULT = "%s: 승";
    private final String LOSER_RESULT = "%s: 패";
    private Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readPlayersName() {
        System.out.println(PLAYER_NAME);
        String playerNames = scanner.nextLine();

        return Arrays.stream(playerNames.split(","))
                .toList();
    }

    public Boolean askPlayerForHitOrStand() {
        String s = scanner.nextLine();
        if(s.equals("y"))return true;
        if(s.equals("n"))return false;

        throw new IllegalArgumentException("[ERROR] y 또는 n으로 입력해주세요.");
    }

    public void printPlayerDeck(Player player) {
        for (Card card : player.getCardDeck().getCards()) {
            System.out.println(card);
        }
    }
}
