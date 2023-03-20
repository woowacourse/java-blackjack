package view;

import domain.dto.UserDto;
import domain.user.Name;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {
    public void printInputPlayerNameMessage() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printSetUpResult(UserDto dealerSetUpDataDto, List<UserDto> playerGameDataDtos) {
        printSetUpCompleteMessage(playerGameDataDtos);
        printUserCards(dealerSetUpDataDto);
        printAllUserCards(playerGameDataDtos);
    }

    public void printAskOneMoreCardMessage(UserDto userDto) {
        System.out.printf("\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", userDto.getName());
    }

    public void printPlayerDrawResult(UserDto userDto) {
        printUserCards(userDto);
    }

    public void printDealerDrawResult(int dealerDrawCount) {
        System.out.printf("\n딜러는 16이하라 %d장의 카드를 더 받았습니다.\n\n", dealerDrawCount);
    }

    public void printUserCardsWithScore(UserDto userDto) {
        String name = userDto.getName();
        String cards = String.join(", ", userDto.getCards());
        int score = userDto.getScore();

        System.out.println(name + "카드 : " + cards + " - 결과: " + score);
    }

    private void printSetUpCompleteMessage(List<UserDto> playerGameDataDtos) {
        List<String> playerNames = playerGameDataDtos.stream()
                .map(UserDto::getName)
                .collect(Collectors.toList());

        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.\n", String.join(", ", playerNames));
    }

    private void printAllUserCards(List<UserDto> userDtos) {
        for (UserDto userDto : userDtos) {
            printUserCards(userDto);
        }
    }

    private void printUserCards(UserDto userDto) {
        String name = userDto.getName();
        String cards = String.join(", ", userDto.getCards());

        System.out.println(name + "카드 : " + cards);
    }

    public void printInputPlayerBettingMessage(String name) {
        System.out.printf("\n%s의 배팅 금액은?\n", name);
    }

    public void printFinalResultHeaderMessage() {
        System.out.println("\n## 최종 수익");
    }

    public void printDealerPrizeResult(int dealerPrize) {
        System.out.printf("딜러: %d\n", dealerPrize);
    }

    public void printPlayerPrizeResult(Map<Name, Integer> playerFinalPrizes) {
        for (Entry<Name, Integer> prizeByPlayer : playerFinalPrizes.entrySet()) {
            String name = prizeByPlayer.getKey().getName();
            int playerPrize = prizeByPlayer.getValue();
            System.out.printf("%s: %d\n", name, playerPrize);
        }
    }

    public void printAllUserCardsWithScore(List<UserDto> allPlayerDtos) {
        allPlayerDtos.forEach(this::printUserCardsWithScore);
    }
}
