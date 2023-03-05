package view;

import domain.GameResult;
import domain.Result;
import domain.Users;
import domain.card.Card;
import domain.card.Cards;
import domain.GamePoint;
import domain.card.CardShape;
import domain.user.Dealer;
import domain.user.Name;
import domain.user.User;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PRINT_FORMAT = "%s 카드: %s";
    private static final String DELIMITER = ", ";
    private static final String ERROR_HEAD = "[ERROR] ";

    public void printInitialStatus(Dealer dealer, Users users) {
        System.out.print(System.lineSeparator());
        System.out.printf("%s와 %s에게 2장을 나누었습니다.",
                getNameString(dealer.getName()),
                makeUsersNameList(users));
        System.out.print(System.lineSeparator());
        printCardsOf(dealer.getName(), dealer.getFirstCard());
        printUsersCards(users);
    }

    private void printUsersCards(final Users users) {
        for (User user : users.getUsers()) {
            printCardsOf(user.getName(), user.openCards());
        }
        System.out.print(System.lineSeparator());
    }

    public void printCardsOf(final Name name, final List<Card> cards) {
        System.out.printf(getPlayerCards(name, cards));
        System.out.print(System.lineSeparator());
    }

    private String getPlayerCards(final Name name, final List<Card> cards) {
        return String.format(PRINT_FORMAT,
                getNameString(name),
                getCardStringOf(cards));
    }

    private String getCardStringOf(final List<Card> cards) {
        return cards.stream()
                .map(card -> makeCardString(card))
                .collect(Collectors.joining(DELIMITER));
    }

    private String makeCardString(final Card card) {
        return String.format("%s%s",
                translate(card.getCardNumberValue()),
                translate(card.getShape()));
    }

    private String translate(final int cardNumberValue) {
        if (cardNumberValue == 1) {
            return "A";
        }
        if (cardNumberValue == 11) {
            return "J";
        }
        if (cardNumberValue == 12) {
            return "Q";
        }
        if (cardNumberValue == 13) {
            return "K";
        }
        return String.valueOf(cardNumberValue);
    }

    private String translate(final CardShape cardShape) {
        if (cardShape == CardShape.HEART) {
            return "하트";
        }
        if (cardShape == CardShape.DIAMOND) {
            return "다이아몬드";
        }
        if (cardShape == CardShape.CLOVER) {
            return "클로버";
        }
        if (cardShape == CardShape.SPADE) {
            return "스페이드";
        }
        throw new AssertionError();
    }

    private String makeUsersNameList(Users users) {
        return users.getUsers()
                .stream()
                .map(user -> user.getName().getValue())
                .collect(Collectors.joining(DELIMITER));
    }

    private String getNameString(final Name name) {
        return name.getValue();
    }

    public void printException(final Exception exception) {
        System.out.println(ERROR_HEAD + exception.getMessage());
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

    public void printTotalPlayersStatus(final Dealer dealer, final List<User> users) {
        printPlayerResult(dealer.getName(), dealer.getCards());
        for (User user : users) {
            printPlayerResult(user.getName(), user.getCards());
        }
    }

    private void printPlayerResult(final Name name, final Cards cards) {
        System.out.printf("%s - 결과: %s",
                getPlayerCards(name, cards.getCards())
                , printGamePoint(cards.getPoint()));
        System.out.print(System.lineSeparator());
    }

    private String printGamePoint(final GamePoint gamePoint) {
        final int point = gamePoint.getPoint();
        if (point == 0) {
            return "버스트";
        }
        return String.valueOf(point);
    }

    public void printResult(GameResult gameResult) {
        System.out.print(System.lineSeparator());
        System.out.println("## 최종 승패");
        printDealerResult(gameResult);
        printUsersResult(gameResult);
    }

    private void printDealerResult(final GameResult gameResult) {
        printPlayer(gameResult.getDealerResult(), Dealer.DEALER_NAME);
    }

    private void printUsersResult(final GameResult gameResult) {
//        final Map<Name, List<Result>> userResult = gameResult.getUserResult();
//        final Set<Name> names = userResult.keySet();
//        for (Name name : names) {
//            printPlayer(userResult.get(name), name.getValue());
//        }
        for (Map.Entry<Name, List<Result>> entry : gameResult.getUserResult().entrySet()) {
            printPlayer(entry.getValue(), entry.getKey().getValue());
        }
    }

    private void printPlayer(final List<Result> results, String name) {
        System.out.printf("%s: %s", name, makeTotalGameResult(results));
        System.out.print(System.lineSeparator());
    }

    private String makeTotalGameResult(final List<Result> results) {
        final int drawCount = getCount(results, m -> m == Result.DRAW);
        final int winCount = getCount(results, m -> m == Result.WIN);
        final int loseCount = getCount(results, m -> m == Result.LOSE);
        return makeResultOf(drawCount, winCount, loseCount);
    }

    private int getCount(final List<Result> results, Predicate<Result> predicate) {
        return (int) results.stream()
                .filter(predicate::test)
                .count();
    }

    private String makeResultOf(final int drawCount, final int winCount, final int loseCount) {
        if ((drawCount + winCount + loseCount) != 1) {
            return getMultipleResult(drawCount, winCount, loseCount);
        }
        if (winCount == 1) {
            return "승";
        }
        if (loseCount == 1) {
            return "패";
        }
        if (drawCount == 1) {
            return "무";
        }
        throw new AssertionError();
    }

    private String getMultipleResult(final int drawCount, final int winCount, final int loseCount) {
        StringBuilder stringBuilder = new StringBuilder();
        if (drawCount > 0) {
            stringBuilder.append(String.format("%d무 ", drawCount));
        }
        if (winCount > 0) {
            stringBuilder.append(String.format("%d승 ", winCount));
        }
        if (loseCount > 0) {
            stringBuilder.append(String.format("%d패 ", loseCount));
        }
        return stringBuilder.toString();
    }

}
