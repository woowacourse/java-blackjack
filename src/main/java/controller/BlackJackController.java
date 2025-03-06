package controller;

import domain.Dealer;
import domain.Deck;
import domain.Participant;
import domain.Player;
import java.util.List;

public class BlackJackController {

    public void run() {
        //덱 생성
        Deck deck = new Deck();

        // 딜러, 플레이어 생성
        Dealer dealer = Dealer.init();
        Player player1 = Player.init();
        Player player2 = Player.init();

        List<Participant> participants = List.of(dealer, player1, player2);

        // 2장씩 카드 뽑기
        for (Participant participant : participants) {
            participant.addCard(deck.pick());
            participant.addCard(deck.pick());
        }

        // 플레이어 순서대로 카드를 더 뽑는다
        for (int i = 1; i < participants.size(); i++) {
            Player participant = (Player) participants.get(i);

            do {
                if (participant.isBurst()) {
                    break;
                }
                String input = "y";
                if (input.equals("n")) {
                    break;
                }
                participant.addCard(deck.pick());
            } while (true);
        }

        //딜러가 카드를 더 뽑는다
        while (dealer.hasToDraw()) {
            dealer.addCard(deck.pick());
        }

        // 결과 출력

        // 승패 출력

    }
}
