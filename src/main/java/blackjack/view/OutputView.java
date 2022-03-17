package blackjack.view;

import static java.util.stream.Collectors.toList;

import blackjack.dto.CardDto;
import blackjack.dto.UserDto;
import blackjack.dto.UsersDto;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private static final String ERROR = "[ERROR] ";

    public void printErrorMessage(String errorMessage) {
        System.out.println(ERROR + errorMessage);
    }

    public void printInitCards(UsersDto usersDto) {
        List<String> playerNames = usersDto.getPlayerNames();

        printInitNames(usersDto.getDealerName(), String.join(", ", playerNames));

        usersDto.getAllUserDto().forEach(this::printInitCardsInfo);
        System.out.println();
    }

    private void printInitNames(String dealerName, String playerNames) {
        System.out.printf("\n%s와 %s에게 2장을 나누었습니다.\n", dealerName, playerNames);
    }

    private void printInitCardsInfo(UserDto userDto) {
        List<CardDto> initCards = userDto.getInitCards();

        List<String> cardInfo = getCardInfo(initCards);

        System.out.printf("%s카드: %s\n", userDto.getUserName(), String.join(", ", cardInfo));
    }

    public void printCardsInfoWithName(UserDto userDto) {
        List<CardDto> cards = userDto.getCards();

        List<String> cardInfo = getCardInfo(cards);

        System.out.printf("%s카드: %s\n", userDto.getUserName(), String.join(", ", cardInfo));
    }

    private List<String> getCardInfo(List<CardDto> cards) {
        return cards.stream()
                .map(card -> card.getSymbol() + card.getSuitName())
                .collect(toList());
    }

    public void printDealerMessage() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printWithScore(Map<UserDto, Integer> result) {
        result.entrySet()
                .stream()
                .forEach(
                        entry -> System.out.printf("\n%s카드: %s - 결과: %d",
                                getCardInfo(entry.getKey().getCards()),
                                String.join(", "),
                                entry.getValue())
                );
    }

    public void printRevenue(Map<String, Integer> revenue) {
        System.out.println("\n\n## 최종 수익");

        for (Entry<String, Integer> entry : revenue.entrySet()) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }

    }
}
