package blackjack.view;

import blackjack.domain.dto.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PRINT_FORMAT = "%s 카드: %s";
    private static final String DELIMITER = ", ";
    private static final String ERROR_HEAD = "[ERROR] ";

    public void printInitialStatus(InitialStatusDto initialStatusDto) {
        System.out.print(System.lineSeparator());
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.",
                makeUsersNameList(initialStatusDto.getUsersData()));
        System.out.print(System.lineSeparator());
        printDealerFirstCard(initialStatusDto.getDealerCard());
        printUsersCards(initialStatusDto.getUsersData());
    }

    private String makeUsersNameList(List<UserDto> userNames) {
        return userNames.stream()
                .map(UserDto::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    private void printDealerFirstCard(CardDto card) {
        System.out.println(String.format("딜러: %s", makeCardDtoString(card)));
    }

    private void printUsersCards(final List<UserDto> userDtos) {
        for (UserDto user : userDtos) {
            printCardsOf(user);
        }
        System.out.print(System.lineSeparator());
    }

    public void printCardsOf(UserDto user) {
        System.out.printf(getPlayerCards(user.getName(), user.getCards()));
        System.out.print(System.lineSeparator());
    }

    private String getPlayerCards(final String name, final List<CardDto> cards) {
        return String.format(PRINT_FORMAT,
                name,
                getCardStringOf(cards));
    }

    private String getCardStringOf(final List<CardDto> cards) {
        return cards.stream()
                .map(card -> makeCardDtoString(card))
                .collect(Collectors.joining(DELIMITER));
    }

    private String makeCardDtoString(final CardDto card) {
        return String.format("%s%s",
                card.getValue(),
                card.getShape());
    }

    public void printAdditionalCardCountOfDealer(final int cardCount) {
        System.out.print(System.lineSeparator());
        if (cardCount == 0) {
            System.out.println("딜러는 17 이상이라 카드를 받지 못했습니다.");
        }
        if (cardCount > 0) {
            System.out.printf("딜러는 16 이하라 %d장의 카드를 더 받았습니다.", cardCount);
            System.out.print(System.lineSeparator());
        }
        System.out.print(System.lineSeparator());

    }

    public void printFinalPlayersStatus(FinalStatusDto finalStatusDto) {
        printPlayFinalStatus(finalStatusDto.getDealer(), finalStatusDto.getDealerScore());
        final Map<UserDto, Integer> userScores = finalStatusDto.getUserScores();
        for (UserDto userDto : userScores.keySet()) {
            printPlayFinalStatus(userDto, userScores.get(userDto));
        }
    }

    private void printPlayFinalStatus(final UserDto user, final int score) {
        System.out.println(String.format("%s 카드 : %s- 결과: %s", user.getName(), getCardStringOf(user.getCards()), getScoreString(score)));
    }


    private String getScoreString(final int score) {
        if (score == 0) {
            return "버스트";
        }
        return String.valueOf(score);
    }


    public void printResult(GameResultDto gameResultDto) {
        System.out.print(System.lineSeparator());
        System.out.println("## 최종 수익");
        printProfit(gameResultDto.getUserAndProfit());
    }

    private void printProfit(final Map<String, Integer> userAndProfit) {
        for (String name : userAndProfit.keySet()) {
            System.out.println(String.format("%s: %d", name, userAndProfit.get(name)));
        }
    }

    public void printException(final Exception exception) {
        System.out.println(ERROR_HEAD + exception.getMessage());
    }

}
