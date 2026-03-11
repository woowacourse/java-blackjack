package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.PlayerResults;
import blackjack.model.participant.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printPlayerNamesInputPrompt() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printCardDistributionCompleted(List<Player> players) {
        List<String> names = players.stream()
                .map(Player::getName)
                .toList();

        String joinedName = String.join(", ", names);
        System.out.println("딜러와 " + joinedName + "에게 2장을 나누었습니다.");
    }

    public void printParticipantHands(String name, List<CardDto> openedCards) {
        String joinedCardNames = getJoinedCardNames(openedCards);
        System.out.println(name + "카드: " + joinedCardNames);
    }

    public void printParticipantCardsWithScore(String name, List<CardDto> cards, int score) {
        String joinedCardNames = getJoinedCardNames(cards);
        System.out.println(name + "카드: " + joinedCardNames + " - 결과: " + score);
    }

    public void printMoreCardInputPrompt(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public void printDealerPicksCard() {
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    public void printDealerDoesNotPickCard() {
        System.out.println("딜러는 16초과라 더 이상 카드를 받지 않습니다.");
    }

    public void printResult(PlayerResults playerResults) {
        System.out.println("## 최종 승패");
        printDealerResult(playerResults.getDealerResult());
        printAllPlayerResult(playerResults);
    }

    private void printDealerResult(String dealerResult) {
        System.out.println("딜러: " + dealerResult);
    }

    private void printAllPlayerResult(PlayerResults playerResults) {
        String results = playerResults.results().entrySet().stream()
                .map(result -> result.getKey() + ": " + result.getValue().getDisplayName())
                .collect(Collectors.joining("\n"));
        System.out.println(results);
    }

    private String getJoinedCardNames(List<CardDto> cards) {
        List<String> cardNames = cards.stream()
                .map(card -> card.rank().getDisplayName() + card.suit().getDisplayName())
                .toList();

        return String.join(", ", cardNames);
    }
}
