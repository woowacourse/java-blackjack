package view;

import card.Card;
import card.Cards;
import controller.dto.WinningResult;
import dealer.dto.DealerGameResult;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import player.Name;
import player.Player;
import player.Players;
import player.dto.SinglePlayerStatusDto;

public class OutputView {

    private static final String DEALER = "딜러";
    private static final String DEALER_NAME_SYMBOL = DEALER + ": ";
    private static final String CARD_SYMBOL = "카드: ";
    private static final int INIT_CARD_COUNT = 2;
    private static final int MIN_DEALER_SCORE = 16;
    private static final String PARTICIPANT_RESULT_SYMBOL = " : ";
    private static final String NAME_FORMAT_SYMBOL = ", ";

    public void printInitCardStatus(Players players, Card card) {

        String playersNames = changeNameFormat(players.getPlayerNames());

        System.out.println("\n" + DEALER + "와 " + playersNames + "에게 " + INIT_CARD_COUNT + "장을 나누었습니다.");
        System.out.println(DEALER_NAME_SYMBOL + card.getCardHand());

        for (Player player : players.getPlayers()) {
            System.out.println(
                    player.getName().getValue() + CARD_SYMBOL + formatCardsStatus(player.getCards()));
        }

        System.out.println();
    }

    public void printPlayerCardStatus(Player player) {
        System.out.println(makeCardsStatus(player.getName(), player.getCards()));
    }

    public void printExtraCardInfo(SinglePlayerStatusDto result) {
        System.out.println();
        for (int i = 2; i <= result.cards().countCard(); i++) {
            System.out.println(result.name().getValue() + "가 " + MIN_DEALER_SCORE + "이하라 한장의 카드를 더 받았습니다.\n");
        }
    }

    public void printPlayStatus(List<SinglePlayerStatusDto> playResult) {
        Collections.reverse(playResult);

        for (SinglePlayerStatusDto singlePlayerResult : playResult) {
            System.out.println(makeCardsStatus(singlePlayerResult.name(), singlePlayerResult.cards()) + " - 결과: "
                    + singlePlayerResult.getCardsScore());
        }
    }

    public void printPlayersResult(List<WinningResult> playerResult) {
        Collections.reverse(playerResult);

        for (WinningResult result : playerResult) {
            printSinglePlayerResult(result);
        }
    }

    public void printDealerResult(DealerGameResult dealerGameResult) {
        System.out.println("\n## 최종 승패");
        System.out.println(
                DEALER_NAME_SYMBOL + dealerGameResult.winningCount() + GameResultSymbol.WINNING_SYMBOL.symbolName + " "
                        + dealerGameResult.failCount()
                        + GameResultSymbol.LOSE_SYMBOL.symbolName);
    }

    private void printSinglePlayerResult(WinningResult playerResult) {
        System.out.println(playerResult.name().getValue() + PARTICIPANT_RESULT_SYMBOL + GameResultSymbol.changeToSymbol(
                playerResult.isWinner()).symbolName);
    }

    public String formatCardsStatus(Cards cards) {
        return String.join(",", cards.getCardsStatus());
    }

    private String makeCardsStatus(Name name, Cards cards) {
        return name.getValue() + CARD_SYMBOL + cards.getCardsStatus();
    }

    private String changeNameFormat(List<Name> names) {
        return names.stream()
                .map(Name::getValue)
                .collect(Collectors.joining(NAME_FORMAT_SYMBOL));
    }
}
