package controller;

import java.util.List;
import model.Card;
import model.CardGenerator;
import model.Participant;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        List<String> names = InputView.readPlayerNames();
        OutputView.printCardOpen(names);

        // 딜러 카드 2장 뽑기
        Participant dealer = new Participant("딜러");
        for (int i = 0; i < 2; i++) {
            Card card = CardGenerator.generateCard();
            dealer.addCard(card);
        }

        List<Participant> players = names.stream()
                .map(Participant::new)
                .toList();

        players.forEach(player -> {
            for (int i = 0; i < 2; i++) {
                Card card = CardGenerator.generateCard();
                player.addCard(card);
            }
        });

        OutputView.printCardByDealer(dealer);
        OutputView.printCardByPlayer(players);
    }
}
