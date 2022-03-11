package blackjack.view;

import blackjack.domain.Result;
import blackjack.dto.UserDto;
import blackjack.dto.UsersDto;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class OutputView {
    public void printInitCards(UsersDto usersDto) {
        printInitNames(usersDto.getDealerName(), usersDto.getPlayerNames());

        usersDto.getAllUserDto().forEach(this::printInitCardsInfo);
        System.out.println();
    }

    private void printInitNames(String dealerName, String playerNames) {
        System.out.printf("\n%s와 %s에게 2장을 나누었습니다.\n", dealerName, playerNames);
    }

    private void printInitCardsInfo(UserDto userDto) {
        System.out.printf("%s카드: %s\n", userDto.getUserName(), userDto.getInitCardsInfo());
    }

    public void printCards(UserDto userDto) {
        System.out.printf("%s카드: %s\n", userDto.getUserName(), userDto.getCardsInfo());
    }

    public void printDealer() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printWithScore(UserDto userDto, int score) {
        System.out.printf("%s카드: %s - 결과: %d\n", userDto.getUserName(), userDto.getCardsInfo(), score);
    }

    public void printYield(Map<String, Result> map) {
        System.out.println("## 최종승패");

        String dealerYield = calculateDealerYield(map);
        System.out.printf("딜러: %s\n", dealerYield);

        printPlayerYield(map);
    }

    private String calculateDealerYield(Map<String, Result> map) {
        return map.values().stream()
                .collect(Collectors.groupingBy(
                        Result::reverseResult, TreeMap::new, Collectors.counting()
                ))
                .entrySet()
                .stream()
                .map(entry -> entry.getValue() + entry.getKey().getName())
                .collect(Collectors.joining(" "));
    }

    private void printPlayerYield(Map<String, Result> map) {
        for (Entry<String, Result> entry : map.entrySet()) {
            System.out.printf("%s: %s\n", entry.getKey(), entry.getValue().getName());
        }
    }
}
