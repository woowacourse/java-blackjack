package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.PlayerResultsDto;
import blackjack.model.participant.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printPlayerNamesInputPrompt() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printCardDistributionCompleted(final List<Player> players) {
        List<String> names = players.stream()
                .map(Player::getName)
                .toList();

        String joinedName = String.join(", ", names);
        System.out.println("딜러와 " + joinedName + "에게 2장을 나누었습니다.");
    }

    public void printParticipantHands(final String name, final List<CardDto> openedCards) {
        String joinedCardNames = getJoinedCardNames(openedCards);
        System.out.println(name + "카드: " + joinedCardNames);
    }

    public void printParticipantCardsWithScore(final String name, final List<CardDto> cards, final int score) {
        String joinedCardNames = getJoinedCardNames(cards);
        System.out.println(name + "카드: " + joinedCardNames + " - 결과: " + score);
    }

    public void printMoreCardInputPrompt(final String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public void printDealerPicksCard() {
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    public void printDealerDoesNotPickCard() {
        System.out.println("딜러는 16초과라 더 이상 카드를 받지 않습니다.");
    }

    public void printResult(final PlayerResultsDto playerResults) {
        System.out.println("## 최종 수익");
        printDealerResult(playerResults);
        printAllPlayerResult(playerResults);
    }

    private void printDealerResult(final PlayerResultsDto dealerResult) {
        System.out.printf("딜러: %s\n", dealerResult.getDealerProfit());
    }

    private void printAllPlayerResult(final PlayerResultsDto playerResults) {
        String results = playerResults.results().stream()
                .map(result -> result.name() + ": " + result.profit())
                .collect(Collectors.joining("\n"));
        System.out.println(results);
    }

    private String getJoinedCardNames(final List<CardDto> cards) {
        List<String> cardNames = cards.stream()
                .map(card -> card.rank().getDisplayName() + card.suit().getDisplayName())
                .toList();

        return String.join(", ", cardNames);
    }

    public void printBettingAmountInputPlayer(final String name) {
        System.out.println(name + "의 베팅 금액은?");
    }
}
