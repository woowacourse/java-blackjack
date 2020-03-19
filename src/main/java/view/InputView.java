package view;

import domain.card.Deck;
import domain.gamer.Player;

import java.util.*;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final int MONEY_UNIT = 1000;
    private static final int NONE_EXCHANGE = 0;

    public static List<Integer> inputPlayerBettingMoney(List<String> playerNames) {
        List<Integer> playersBettingMoney = new ArrayList<>();

        for(int playerIndex = 0; playerIndex < playerNames.size(); playerIndex++) {
            System.out.println(String.format("%s의 배팅 금액은?",playerNames.get(playerIndex)));
            String playerMoney = scanner.nextLine();
            if(isNotMoney(playerMoney)) {
                throw new IllegalArgumentException("1000원 단위의 배팅 금액을 입력해주세요");
            }
            playersBettingMoney.add(Integer.parseInt(playerMoney));
        }

        return playersBettingMoney;
    }

    public static List<Player> inputParticipantPlayer(Deck deck) {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String playerNamesValue = scanner.nextLine();
        List<Player> players = new ArrayList<>();
        List<String> playerNames = Arrays.asList(playerNamesValue.split(DELIMITER));

        for(String playerName : playerNames) {
            if(isNotAlphabet(playerName)) {
                throw new IllegalArgumentException();
            }
        }
        List<Integer> playerBettingMoneys = inputPlayerBettingMoney(playerNames);
        for(int playerIndex = 0; playerIndex < playerNames.size(); playerIndex++) {
            players.add(new Player(playerNames.get(playerIndex),playerBettingMoneys.get(playerIndex),deck.getInitCards()));
        }
        return players;

    }

    public static String inputGetMoreCard(String name) {
        System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?",name));
        String input = scanner.nextLine();

        if(!isYesOrNo(input)) {
            throw new IllegalArgumentException();
        }

        return input;
    }

    private static boolean isNotMoney(String playerMoney) {
        return playerMoney.chars()
                .anyMatch(c -> !Character.isDigit(c)) || Integer.parseInt(playerMoney) % MONEY_UNIT != NONE_EXCHANGE;
    }

    private static boolean isYesOrNo(String input) {
        return YES.equals(input) || NO.equals(input);
    }

    private static boolean isNotAlphabet(String playerNamesValue) {
        return playerNamesValue.chars()
                .anyMatch(c -> !Character.isAlphabetic(c));
    }
}
