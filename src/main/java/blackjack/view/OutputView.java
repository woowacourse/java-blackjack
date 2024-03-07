package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OutputView {
    private static final EnumMap<CardSymbol, String> symbolBoard = initializeSymbol();
    private static final EnumMap<CardValue, String> valueBoard = initializeValue();


    public static void printPlayers(Players players) {
        Dealer dealer = players.getDealer();
        List<GamePlayer> gamePlayers = players.getGamePlayers();

        String gamePlayerNames = gamePlayers.stream()
                                            .map(Player::getName)
                                            .collect(Collectors.joining(","));

        System.out.println(
                String.format("%s와 %s에게 2장을 나누었습니다.", dealer.getName(), gamePlayerNames));

        printDealer(players.getDealer());
        printGamePlayers(players.getGamePlayers());
    }

    private static void printDealer(Dealer dealer) {
        String result = joiningCard(dealer.getCards()
                                          .stream()
                                          .limit(1));

        System.out.println(String.format("%s: %s", dealer.getName(), result));
    }

    private static void printGamePlayers(List<GamePlayer> gamePlayers) {
        gamePlayers.forEach(OutputView::printGamePlayer);
    }

    private static void printGamePlayer(GamePlayer gamePlayer) {
        String result = joiningCard(gamePlayer.getCards()
                                              .stream());

        System.out.println(String.format("%s: %s", gamePlayer.getName(), result));
    }

    private static String joiningCard(Stream<Card> stream) {
        return stream.map(OutputView::printCard)
                     .collect(Collectors.joining(", "));
    }

    private static String printCard(Card card) {
        return valueBoard.get(card.getCardValue()) + symbolBoard.get(card.getCardSymbol());
    }

    private static EnumMap<CardSymbol, String> initializeSymbol() {
        EnumMap<CardSymbol, String> symbolBoard = new EnumMap<>(CardSymbol.class);
        symbolBoard.put(CardSymbol.HEART, "하트");
        symbolBoard.put(CardSymbol.SPADE, "스페이드");
        symbolBoard.put(CardSymbol.CLOVER, "클로버");
        symbolBoard.put(CardSymbol.DIAMOND, "다이아몬드");
        return symbolBoard;
    }

    private static EnumMap<CardValue, String> initializeValue() {
        EnumMap<CardValue, String> valueBoard = new EnumMap<>(CardValue.class);
        valueBoard.put(CardValue.ACE, "A");
        valueBoard.put(CardValue.TWO, "2");
        valueBoard.put(CardValue.THREE, "3");
        valueBoard.put(CardValue.FOUR, "4");
        valueBoard.put(CardValue.FIVE, "5");
        valueBoard.put(CardValue.SIX, "6");
        valueBoard.put(CardValue.SEVEN, "7");
        valueBoard.put(CardValue.EIGHT, "8");
        valueBoard.put(CardValue.NINE, "9");
        valueBoard.put(CardValue.TEN, "10");
        valueBoard.put(CardValue.JACK, "J");
        valueBoard.put(CardValue.QUEEN, "Q");
        valueBoard.put(CardValue.KING, "K");
        return valueBoard;
    }
}
