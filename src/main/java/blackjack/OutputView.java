package blackjack;

import java.util.List;

public class OutputView {

    public void printInitDraw(Players players,Dealer dealer){
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n",
                String.join(", ", players.getNames()));

        System.out.printf("딜러카드: %s%n", dealer.getFirstCardNames());

        //플레이어 카드 출력
        for (Player player : players.getPlayers()) {
            System.out.printf("%s카드: %s%n", player.getName(),player.getCardNames());
        }


    }



}
