package blackjack;

import blackjack.domain.Deck;
import blackjack.domain.Players;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        Scanner scanner = new Scanner(System.in);
        Players players = new Players(scanner.nextLine());
        System.out.println("딜러와 "+ players.getNames() + "에게 " + "2장씩 나누었습니다.");
        Deck deck = new Deck();
        players.giveCards(deck);
    }
}
