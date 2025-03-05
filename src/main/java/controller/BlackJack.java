package controller;

import domain.Dealer;
import domain.Deck;
import domain.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BlackJack {

    public static final String DELIMITER = ",";

    private final List<Player> players = new ArrayList<>();
    private final Dealer dealer = new Dealer();

    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String playersInput = sc.nextLine();
        List<String> playerNames = Arrays.asList(playersInput.split(DELIMITER));

        if (playerNames.size() > 5) {
            throw new IllegalArgumentException("플레이어는 5명까지만 입력해주세요.");
        }

        for (String playerName : playerNames) {
            players.add(new Player(playerName.trim()));
        }

        Deck deck = new Deck();
        deck.shuffle();
        drawTwoCardFromDeck(dealer, deck);
        for (Player player : players) {
            drawTwoCardFromDeck(player, deck);
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("딜러와 ")
                .append(players.stream().map(Player::getName).collect(Collectors.joining(",")))
                .append("에게 2장을 나누었습니다.\n");

        stringBuilder.append(String.format("%s카드: ", dealer.getName()));
        stringBuilder.append(dealer.openOneCard());
        for (Player player : players) {
            printHand(player, stringBuilder);
        }

        System.out.println(stringBuilder);

        stringBuilder = new StringBuilder();
        for (Player player : players) {
            while(true) {
                System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
                String ynInput = sc.nextLine();
                if (ynInput.equals("y")) {
                    player.addCard(deck.draw());
                    printHand(player, stringBuilder);
                    System.out.println(stringBuilder);
                    if(player.isHandBurst()) {
                        System.out.println("Burst!! You Die!!");
                       break;
                    }
                    continue;
                }
                break;
            }
        }
    }

    private static void printHand(Player player, StringBuilder stringBuilder) {
        stringBuilder.append(player.getName())
                .append("카드: ")
                .append(player.openAllCards())
                .append("\n");
    }

    private void drawTwoCardFromDeck(Player player, Deck deck) {
        player.addCard(deck.draw());
        player.addCard(deck.draw());
    }
}
