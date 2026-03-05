package controller;

import java.util.List;
import model.Card;
import model.CardGenerator;
import model.CardValue;
import model.Participant;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        List<String> names = InputView.readPlayerNames();
        OutputView.printCardOpen(names);
        CardGenerator cardGenerator = new CardGenerator();

        // 딜러 카드 2장 뽑기
        Participant dealer = new Participant("딜러");
        for (int i = 0; i < 2; i++) {
            Card card = cardGenerator.generateCard();
            dealer.addCard(card);
        }

        List<Participant> players = names.stream()
                .map(Participant::new)
                .toList();

        players.forEach(player -> {
            for (int i = 0; i < 2; i++) {
                Card card = cardGenerator.generateCard();
                player.addCard(card);
            }
        });

        OutputView.printCardByDealer(dealer);
        OutputView.printCardByPlayers(players);

        players.forEach(player -> {
            boolean isFirst = true;
            while(true) {
                String input = InputView.readMoreCard(player);
                Continuation command = Continuation.from(input);
                if (command.isStop()) {
                    if (isFirst) {
                        OutputView.printCardByPlayer(player);
                    }
                    break;
                }

                Card card = cardGenerator.generateCard();
                player.addCard(card);
                isFirst = false;

                if (command.isContinue()) {
                    OutputView.printCardByPlayer(player);
                }
            }
        });

        // 딜러의 카드 더 받기 판정
        int sum = calculateSum(dealer.getCards());
        // TODO: 네이밍 리팩토링
        int base = 16;
        while (sum <= base) {
            OutputView.printToOpenNewCard(dealer.getName(), base);
            Card card = cardGenerator.generateCard();
            dealer.addCard(card);
            sum = calculateSum(dealer.getCards());
        }

        OutputView.printBlank();
        OutputView.printCardByParticipantsWithScore(dealer, sum);
        for(Participant player : players) {
            int playerSum = calculateSum(player.getCards());
            OutputView.printCardByParticipantsWithScore(player, playerSum);
        }

    }

    private int calculateSum(List<Card> cards) {
        int sum = cards.stream()
                .mapToInt(card -> {
                    int value = card.value().getValue();
                    return Math.min(value, 10);
                })
                .sum();

        for (Card card : cards) {
            if (card.value() == CardValue.ACE && sum + 10 <= 21) {
                sum += 10;
            }
        }
        return sum;
    }
}
