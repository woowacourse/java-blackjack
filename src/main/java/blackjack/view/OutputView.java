package blackjack.view;

import static java.util.stream.Collectors.joining;

import blackjack.domain.PlayerDto;
import blackjack.domain.PlayingCard;
import java.util.List;

public class OutputView {
    private static final String INITIAL_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.%n";

    public void print(List<String> playerNames) {
        final String joinNames = String.join(", ", playerNames);

        System.out.printf(INITIAL_MESSAGE, joinNames);
    }

    public void printCards(final PlayerDto playerDto) {
        System.out.println(playerDto.getName() + ": " + getCardNames(playerDto.getPlayingCards()));
    }

    public String getCardNames(List<PlayingCard> playingCards) {
        return playingCards.stream()
            .map(PlayingCard::getCardName)
            .collect(joining(", "));
    }

    public void printAfterSpread(String dealerName, String playerNames) {
        System.out.println();
        System.out.println(dealerName + "와 " + playerNames + "에게 2장을 나누었습니다.");
    }

    public void printSingleCardForDealer(PlayerDto dealer) {
        System.out.println(dealer.getName() + ": " + dealer.getPlayingCards().get(0).getCardName());
    }
}
