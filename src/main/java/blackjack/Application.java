package blackjack;

import blackjack.controller.BlackJackController;

public class Application {

    public static void main(String[] args) {
        //TODO: 각 클래스의 메서드들 읽기쉽게 정렬하기
        //TODO: Map반환할 때 키값을 Name으로 두기
        BlackJackController blackJackController = new BlackJackController();
        blackJackController.run();
    }
}
