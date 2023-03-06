package blackjack.view;

import blackjack.domain.Result;
import blackjack.domain.dto.CardDto;
import blackjack.domain.dto.FinalStatusDto;
import blackjack.domain.dto.GameResultDto;
import blackjack.domain.dto.InitialStatusDto;
import blackjack.domain.user.Dealer;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PRINT_FORMAT = "%s 카드: %s";
    private static final String DELIMITER = ", ";
    private static final String ERROR_HEAD = "[ERROR] ";

    public void printInitialStatus(InitialStatusDto initialStatusDto) {
        System.out.print(System.lineSeparator());
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.",
                makeUsersNameList(initialStatusDto.getUsersData().keySet()));
        System.out.print(System.lineSeparator());
        printDealerFirstCard(initialStatusDto.getDealerCard());
        printUsersCards(initialStatusDto.getUsersData());
    }

    private String makeUsersNameList(Set<String> userNames) {
        return userNames
                .stream()
                .collect(Collectors.joining(DELIMITER));
    }

    private void printDealerFirstCard(CardDto card) {
        System.out.println(String.format("딜러: %s", makeCardDtoString(card)));
    }

    private void printUsersCards(final Map<String, List<CardDto>> usersData) {
        for (String userName : usersData.keySet()) {
            printCardsOf(userName, usersData.get(userName));
        }
        System.out.print(System.lineSeparator());
    }

    public void printCardsOf(final String name, final List<CardDto> cards) {
        System.out.printf(getPlayerCards(name, cards));
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
        printPlayFinalStatus("딜러", finalStatusDto.getDealerCards(), finalStatusDto.getDealerPoint());
        final Map<String, List<CardDto>> userCardsData = finalStatusDto.getUserCardsData();
        final Map<String, Integer> userScores = finalStatusDto.getUserScores();
        for (String userName : finalStatusDto.getUsersNames()) {
            printPlayFinalStatus(userName, userCardsData.get(userName), userScores.get(userName));
        }
    }

    private void printPlayFinalStatus(final String playerName, final List<CardDto> cards, final int score) {
        System.out.println(String.format("%s 카드 : %s- 결과: %s", playerName, getCardStringOf(cards), getScoreString(score)));
    }


    private String getScoreString(final int score) {
        if (score == 0) {
            return "버스트";
        }
        return String.valueOf(score);
    }


    public void printResult(GameResultDto gameResultDto) {
        System.out.print(System.lineSeparator());
        System.out.println("## 최종 승패");
        printDealerResult(gameResultDto);
        printUsersResult(gameResultDto);
    }

    private void printDealerResult(final GameResultDto gameResultDto) {
        printDealer(gameResultDto.getDealerResult(), Dealer.DEALER_NAME);
    }

    private void printDealer(final Map<Result, Integer> dealerResult, final String dealerName) {
        System.out.println(String.format("딜러: %s", makeResultStringOf(dealerResult)));
    }

    private String makeResultStringOf(final Map<Result, Integer> dealerResult) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (Result result : dealerResult.keySet()) {
            stringBuilder.append(makeStringOf(result, dealerResult.get(result)));
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    private String makeStringOf(final Result result, final Integer count) {
        return String.format("%d%s", count, getResultString(result));
    }

    private String getResultString(final Result result) {
        if (result == Result.LOSE) {
            return "패";
        }
        if (result == Result.DRAW) {
            return "무";
        }
        if (result == Result.WIN) {
            return "승";
        }
        throw new AssertionError();
    }

    private void printUsersResult(final GameResultDto gameResultDto) {
        for (Map.Entry<String, Result> userData : gameResultDto.getUserResult().entrySet()) {
            printUser(userData.getKey(), userData.getValue());
        }
    }

    private void printUser(final String name, final Result result) {
        System.out.printf("%s: %s", name, getResultString(result));
        System.out.print(System.lineSeparator());
    }

    public void printException(final Exception exception) {
        System.out.println(ERROR_HEAD + exception.getMessage());
    }

}
