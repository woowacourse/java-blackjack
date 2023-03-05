package view;

import domain.card.Card;
import domain.card.CardShape;
import domain.participant.*;
import utils.TranslationUtil;

import java.util.List;
import java.util.stream.Collectors;

public final class OutputView {

    private static final String DELIMITER = ", ";
    private static final String PRINT_FORMAT = "%s 카드: %s%n";
    private static final String ERROR_HEAD = "[ERROR] ";

    public void printInitialStatus(final Dealer dealer, final Players players) {
        System.out.print(System.lineSeparator());
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n",
                dealer.getName(),
                getPlayerNames(players));

        System.out.printf(PRINT_FORMAT, dealer.getName(), getCardStringOf(dealer.getFirstCard()));

        for (Player player : players.getPlayers()) {
            System.out.printf(PRINT_FORMAT,
                    player.getName(),
                    getCardStringOf(player.getCards().getCards()));
        }
    }

    private String getPlayerNames(final Players players) {
        return players.getPlayers()
                .stream()
                .map(user -> user.getName().getValue())
                .collect(Collectors.joining(DELIMITER));
    }

    private String getCardStringOf(final List<Card> cards) {
        return cards.stream()
                .map(this::makeCardString)
                .collect(Collectors.joining(DELIMITER));
    }

    private String makeCardString(final Card card) {
        return String.format("%s%s",
                TranslationUtil.translate(card.getCardNumberValue()),
                TranslationUtil.translate(card.getShape()));
    }


   /*


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

    public void printStatus(final Dealer dealer, final List<Player> players) {
        printPlayerResultForDealer(dealer, dealer.getCards());
        for (Player player : players) {
            printPlayerResult(player, player.getCards());
        }
    }

    private void printPlayerResult(final Player player, final Cards cards) {
        System.out.printf("%s - 결과: %s",
                getPlayerCards(player, cards.getCards())
                , printGamePoint(cards.getGamePoint()));
        System.out.print(System.lineSeparator());
    }

    private void printPlayerResultForDealer(final Dealer dealer, final Cards cards) {
        System.out.printf("%s - 결과: %s",
                getPlayerCardsForDealer(dealer, cards.getCards())
                , printGamePoint(cards.getGamePoint()));
        System.out.print(System.lineSeparator());
    }

    private String printGamePoint(final GamePoint gamePoint) {
        final int point = gamePoint.getPoint();
        if (point == 0) {
            return "버스트";
        }
        return String.valueOf(point);
    }

    public void printFinalResult(GameResult gameResult) {
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
    }*/

}
