package blackjack;

import blackjack.domain.Player;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        Scanner scanner = new Scanner(System.in);
        Player player = Player.create(scanner.nextLine());
    }
}
