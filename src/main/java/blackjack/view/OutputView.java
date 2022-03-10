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

        usersDto.getAllUserDto().forEach(this::printCards);
    }

    private void printInitNames(String dealerName, String playerNames) {
        System.out.printf("%s와 %s에게 2장을 나누었습니다.\n", dealerName, playerNames);
    }

    public void printCards(UserDto userDto) {
        System.out.printf("%s카드: %s\n", userDto.getUserName(), userDto.getInitCardNames());
    }

    public void printDealer() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printWithScore(UserDto userDto, int score) {
        System.out.printf("%s카드: %s - 결과: %d\n",userDto.getUserName(), userDto.getCardNames(), score);
    }

    public void printYield(Map<String, Result> map) {
        System.out.println("## 최종승패");
        printDealerYield(map);
        printPlayerYield(map);
    }

    private void printDealerYield(Map<String, Result> map) {
        String dealerYield = map.values().stream()
                .collect(Collectors.groupingBy(
                        Result::reverseResult,
                        TreeMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .map(entry -> entry.getValue() + entry.getKey().getName())
                .collect(Collectors.joining(" "));

        System.out.printf("딜러: %s\n", dealerYield);
    }

    private void printPlayerYield(Map<String, Result> map) {
        for (Entry<String, Result> entry : map.entrySet()) {
            System.out.printf("%s: %s\n", entry.getKey(), entry.getValue().getName());
        }
    }
}
