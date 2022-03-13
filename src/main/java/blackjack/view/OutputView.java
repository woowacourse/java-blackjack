package blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.dto.CardDto;
import blackjack.domain.dto.HitResultDto;
import blackjack.domain.dto.WinDrawLoseDto;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

public class OutputView {

    public static final String DRAW_CARD_MESSAGE = "%s와 %s에게 2장을 나누었습니다.\n";
    private static final String CARD_INFORMATION_MESSAGE = "%s: %s\n";
    private static final String PLAYER_BUST_MESSAGE = "%s은(는) %d점으로 버스트 됐습니다.\n";
    private static final String PLAYER_BLACKJACK_MESSAGE = "%s은(는) 블랙잭입니다. 히트를 마무리합니다.\n";
    private static final String TOTAL_RESULT_MESSAGE = "## 최종 승패";
    private static final String WIN_DRAW_LOSE_STATUS_MESSAGE = "%s: %s";
    private static final String HIT_RESULT_MESSAGE = "%s: %s - 결과: %d";

    public static void printInitCard(Dealer dealer, Players players) {
        System.out.printf(DRAW_CARD_MESSAGE, dealer.getName(), printNames(players));
        printDealerCard(dealer);
        printPlayersCard(players);
    }

    private static String printNames(Players players) {
        return players.getPlayers().stream()
            .map(Player::getName)
            .collect(Collectors.joining(","));
    }

    private static void printDealerCard(Dealer dealer) {
        System.out.printf(CARD_INFORMATION_MESSAGE, dealer.getName(), cardToString(dealer.getCards().getFirstCard()));
    }

    private static void printPlayersCard(Players players) {
        players.getPlayers()
            .forEach(player -> System.out.printf(CARD_INFORMATION_MESSAGE, player.getName(),
                cardsToString(player.getCards())));
    }

    private static String cardsToString(Cards cards) {
        return cards.getCards().stream()
            .map(OutputView::cardToString)
            .collect(Collectors.joining(", "));
    }

    private static String cardToString(Card card) {
        return card.getDenomination().getName() + card.getSuit().getName();
    }

    public static void printPresentStatus(String name, List<CardDto> cardDtos) {
        System.out.println(name + ": " + printCard(cardDtos));
    }

    private static String printCard(List<CardDto> cardDtos) {
        return cardDtos.stream()
            .map(card -> card.getDenomination() + card.getSuit())
            .collect(Collectors.joining(", "));
    }

    public static void printBustPlayer(String name, int calculateScore) {
        System.out.printf(PLAYER_BUST_MESSAGE, name, calculateScore);
    }

    public static void printBlackjackPlayer(String name) {
        System.out.printf(PLAYER_BLACKJACK_MESSAGE, name);
    }

    public static void printHitResult(List<HitResultDto> hitResultDtos) {
        hitResultDtos.forEach(hitResultDto -> System.out.printf(HIT_RESULT_MESSAGE, hitResultDto.getName(),
            printCard(hitResultDto.getCards()), hitResultDto.getScore()));
    }

    public static void printResult(List<WinDrawLoseDto> winDrawLoseDtos) {
        System.out.println(TOTAL_RESULT_MESSAGE);
        winDrawLoseDtos.forEach(
            winDrawLoseDto -> System.out.printf(WIN_DRAW_LOSE_STATUS_MESSAGE, winDrawLoseDto.getName(),
                winDrawLoseDto.getWinDrawLoseString()));
    }
}
