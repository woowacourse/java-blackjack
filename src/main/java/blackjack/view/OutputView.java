package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.view.dto.RoundStatusDto;
import fuel.Car;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public static void showStatusWithScore(Map<String, List<Card>> initializeStatus) {
        initializeStatus.keySet()
                .stream()
                .map(key -> String.format("%s: %s", key, initializeStatus.get(key)
                        .stream()
                        .map(card -> card.getCardStatus())
                        .collect(Collectors.joining(", "))))
                .forEach(System.out::println);
    }

    public static void showInitialStatus(RoundStatusDto statusDto) {
        System.out.println(String.format("%s와 %s에게 2장을 나누었습니다.", statusDto.getDealerName(),
                statusDto.getPlayerNames()
                        .stream()
                        .collect(Collectors.joining(", "))));
        showStatusWithScore(statusDto.getStatus());
    }

    public static void showPlayCardStatus(String name, List<Card> cards) {
        String text = String.format("%s: %s", name, cards
                .stream()
                .map(card -> card.getCardStatus())
                .collect(Collectors.joining(", ")));
        System.out.println(text);
    }

    public static void showDealerAddCard(int turnOverCount) {
        System.out.println(String.format("딜러는 %d이하라 한장의 카드를 더 받았습니다.", turnOverCount));
    }

    public static void showFinalStatus(RoundStatusDto statusDto) {
        Dealer dealer = statusDto.getDealer();
        showDealerStatus(dealer);
        List<Player> players = statusDto.getPlayers();
        players.forEach( player ->
                showPlayerStatus(player)
        );

    }

    private static void showPlayerStatus(Player player) {
        System.out.println(String.format("%s카드 : %s - 결과: %d",player.getName(), makeCardNames(player.getCards()), player.calculateScore(21)));
    }

    private static void showDealerStatus(Dealer dealer) {
        System.out.println(String.format("%s카드 : %s - 결과: %d",dealer.getName(), makeCardNames(dealer.getCards()), dealer.calculateScore(21)));
    }

    private static String makeCardNames(List<Card> cards) {
        return cards.stream()
                .map(Card::getCardStatus)
                .collect(Collectors.joining(", "));
    }
}
