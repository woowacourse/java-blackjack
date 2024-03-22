package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;
import blackjack.dto.gamer.GamerCardState;
import blackjack.dto.gamer.PlayerState;
import blackjack.dto.gamer.PlayersState;
import blackjack.dto.profit.GamerProfitState;
import blackjack.dto.profit.GamerProfitStates;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {
    private static final OutputView INSTANCE = new OutputView();

    private static final String CARD_INFO_DELIMITER = ", ";
    private static final String PLAYER_NAME_DELIMITER = ", ";
    private static final String CARD_INFO_MESSAGE = "%s%s";
    private static final String PLAYER_CARD_INFO_MESSAGE = "%s카드: %s";

    public static OutputView getInstance() {
        return INSTANCE;
    }

    public void printInitCardStatus(final Card dealerFirstCard, final PlayersState playersState) {
        printDealMessage(playersState);

        printDealerInitCardsStatusMessage(dealerFirstCard);
        printPlayersInitCardsStatusMessage(playersState);
    }

    private void printDealMessage(final PlayersState playersState) {
        System.out.println(String.format("%n딜러와 %s에게 %d장을 나누었습니다.",
                createPlayerNamesText(playersState), Dealer.INIT_CARD_COUNT));
    }

    private void printDealerInitCardsStatusMessage(final Card dealerFirstCard) {
        System.out.println(String.format("딜러: %s", createCardInfoText(dealerFirstCard)));
    }

    private void printPlayersInitCardsStatusMessage(final PlayersState playersState) {
        for (PlayerState playerState : playersState.infos()) {
            printCardsStatus(playerState);
        }
        printLine();
    }

    private String createPlayerNamesText(final PlayersState playersState) {
        StringJoiner playerNameJoiner = new StringJoiner(PLAYER_NAME_DELIMITER);
        for (PlayerState playerState : playersState.infos()) {
            playerNameJoiner.add(playerState.name());
        }

        return playerNameJoiner.toString();
    }

    private String createCardInfoText(final Card card) {
        return String.format(CARD_INFO_MESSAGE, card.getRankName(), card.getSuitName());
    }

    public void printCardsStatus(final PlayerState playerState) {
        System.out.println(
                String.format(PLAYER_CARD_INFO_MESSAGE, playerState.name(), createCardsInfoText(playerState.cardState().cards())));
    }

    private String createCardsInfoText(final List<Card> cards) {
        StringJoiner cardInfoJoiner = new StringJoiner(CARD_INFO_DELIMITER);
        for (Card card : cards) {
            cardInfoJoiner.add(createCardInfoText(card));
        }

        return cardInfoJoiner.toString();
    }

    public void printDealerHitMessage() {
        System.out.println(String.format("%n딜러는 %d이하라 한장의 카드를 더 받았습니다.", Dealer.MAX_HIT_SCORE));
    }

    //TODO: DTO 받도록 변경
    public void printTotalCardsStatus(final GamerCardState dealerCardState, final PlayersState playersState) {
        printDealerTotalCardsStatus(dealerCardState);
        printPlayersTotalCardsStatus(playersState);
    }

    private void printDealerTotalCardsStatus(final GamerCardState dealerCardState) {
        printLine();
        System.out.println(String.format("딜러 카드: %s - 결과: %d",
                createCardsInfoText(dealerCardState.cards()), dealerCardState.score()));
    }

    private void printPlayersTotalCardsStatus(final PlayersState playersState) {
        for (PlayerState playerState : playersState.infos()) {
            List<Card> cards = playerState.cardState().cards();
            int score = playerState.cardState().score();
            System.out.println(String.format("%s카드: %s - 결과: %d",
                    playerState.name(), createCardsInfoText(cards), score));
        }
    }

    public void printTotalProfit(final GamerProfitStates gamerProfitStates) {
        System.out.println(String.format("%n## 최종 수익"));
        printDealerTotalProfit(gamerProfitStates.dealerProfit());
        printPlayersTotalProfit(gamerProfitStates.gamerProfitStates());
    }

    private void printDealerTotalProfit(final int dealerProfit) {
        System.out.println(String.format("딜러: %d", dealerProfit));
    }

    private void printPlayersTotalProfit(final List<GamerProfitState> gamerProfitStates) {
        for (GamerProfitState playerState : gamerProfitStates) {
            System.out.println(String.format("%s: %d", playerState.name(), playerState.profit()));
        }
    }

    private void printLine() {
        System.out.println();
    }
}
