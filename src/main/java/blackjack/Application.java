package blackjack;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Players;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        Scanner scanner = new Scanner(System.in);
        Dealer dealer = new Dealer();
        Players players = new Players(scanner.nextLine(), dealer);
        System.out.println(players.getDealerNames() + "와 "+ players.getPlayerNames() + "에게 " + "2장씩 나누었습니다.");
        Deck deck = new Deck();

        int count = 2;
        for(int i = 0; i < count; i++) {
            players.giveCards(deck);
        }

    }
}
