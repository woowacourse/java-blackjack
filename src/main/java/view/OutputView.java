package view;

import domain.dto.PrizeResultDto;
import domain.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static final String INPUT_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public static final String ASK_ONE_MORE_CARD_MESSAGE = "\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    public static final String DEALER_DRAW_RESULT_MESSAGE = "\n딜러는 16이하라 %d장의 카드를 더 받았습니다.\n\n";
    public static final String USER_CARDS_WITH_SCORE_FORMAT = "%s카드 : %s - 결과: %d\n";
    public static final String SETUP_COMPLETE_MESSAGE = "\n딜러와 %s에게 2장을 나누었습니다.\n";
    public static final String USER_CARD_FORMAT = "%s카드 : %s\n";
    public static final String INPUT_PLAYER_BETTING_MESSAGE = "\n%s의 배팅 금액은?\n";
    public static final String FINAL_RESULT_HEADER_MESSAGE = "\n## 최종 수익";

    public void printInputPlayerNameMessage() {
        System.out.println(INPUT_PLAYER_NAME_MESSAGE);
    }

    public void printSetUpResult(UserDto dealerSetUpDataDto, List<UserDto> playerGameDataDtos) {
        printSetUpCompleteMessage(playerGameDataDtos);
        printUserCards(dealerSetUpDataDto);
        printAllUserCards(playerGameDataDtos);
    }

    public void printAskOneMoreCardMessage(UserDto userDto) {
        System.out.printf(ASK_ONE_MORE_CARD_MESSAGE, userDto.getName());
    }

    public void printPlayerDrawResult(UserDto userDto) {
        printUserCards(userDto);
    }

    public void printDealerDrawResult(int dealerDrawCount) {
        System.out.printf(DEALER_DRAW_RESULT_MESSAGE, dealerDrawCount);
    }

    public void printUserCardsWithScore(UserDto userDto) {
        String name = userDto.getName();
        String cards = String.join(", ", userDto.getCards());
        int score = userDto.getScore();

        System.out.printf(USER_CARDS_WITH_SCORE_FORMAT, name, cards, score);
    }

    private void printSetUpCompleteMessage(List<UserDto> playerGameDataDtos) {
        List<String> playerNames = playerGameDataDtos.stream()
                .map(UserDto::getName)
                .collect(Collectors.toList());

        System.out.printf(SETUP_COMPLETE_MESSAGE, String.join(", ", playerNames));
    }

    private void printAllUserCards(List<UserDto> userDtos) {
        for (UserDto userDto : userDtos) {
            printUserCards(userDto);
        }
    }

    private void printUserCards(UserDto userDto) {
        String name = userDto.getName();
        String cards = String.join(", ", userDto.getCards());

        System.out.printf(USER_CARD_FORMAT, name, cards);
    }

    public void printInputPlayerBettingMessage(UserDto userDto) {
        System.out.printf(INPUT_PLAYER_BETTING_MESSAGE, userDto.getName());
    }

    public void printFinalResultHeaderMessage() {
        System.out.println(FINAL_RESULT_HEADER_MESSAGE);
    }

    public void printPlayerPrizeResult(List<PrizeResultDto> prizeResultDtos) {
        prizeResultDtos.forEach(prizeResultDto ->
                System.out.printf("%s: %d\n", prizeResultDto.getName(), prizeResultDto.getPrize()));
    }

    public void printAllUserCardsWithScore(List<UserDto> allPlayerDtos) {
        allPlayerDtos.forEach(this::printUserCardsWithScore);
    }
}
