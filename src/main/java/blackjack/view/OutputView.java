package blackjack.view;

import static java.util.stream.Collectors.joining;

import blackjack.domain.BlackJackResult;
import blackjack.domain.Dealer;
import blackjack.domain.PlayerDto;
import blackjack.domain.PlayingCard;
import java.util.List;
import java.util.Map;

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

    public void printDealerAddCard(Dealer dealer) {
        System.out.println();
        System.out.println(dealer.getName() + "는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printCardAndScore(PlayerDto playerDto) {
        System.out.println(playerDto.getName() + "카드: " + getCardNames(playerDto.getPlayingCards()) + " - 결과: " + playerDto.getScore());
    }

    public void printBurst(final PlayerDto playerDto) {
        System.out.println(playerDto.getName() + "님 버스트로 패배하였습니다.");
    }

    public void printResult(final BlackJackResult blackJackResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        final Map<Boolean, Integer> dealerResult = blackJackResult.getDealerResult();
        System.out.println("딜러: " + dealerResult.get(true) + "승 " + dealerResult.get(false) + "패");

        final Map<String, Boolean> playerResult = blackJackResult.getPlayerResult();

        playerResult.entrySet()
            .stream()
            .forEachOrdered(entry -> System.out.println(entry.getKey() + ": " + (entry.getValue() ? "승" : "패")));
    }
}
