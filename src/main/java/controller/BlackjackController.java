package controller;

import java.util.List;
import model.Card;
import model.CardDispenser;
import model.CardGenerator;
import model.CardValue;
import model.Cards;
import model.Dealer;
import model.Player;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        List<String> names = InputView.readPlayerNames();
        OutputView.printCardOpen(names);
        CardGenerator cardGenerator = new CardGenerator();

        Cards deck = Cards.createDeck();
        CardDispenser dispenser = new CardDispenser(deck);

        // 딜러 카드 2장 뽑기
        Dealer dealer = new Dealer();
        dispenser.dispense(dealer, 2);

        List<Player> players = names.stream()
                .map(Player::new)
                .toList();
        players.forEach(player -> dispenser.dispense(player, 2));

        // 딜러와 참가자들 카드 목록 출력
        OutputView.printCardByPlayer(dealer);
        OutputView.printCardByPlayers(players);

        // 카드를 더 받을건지 플레이어에게 물어보는
        players.forEach(player -> {
            boolean isFirst = true;
            while (true) {
                String input = InputView.readMoreCard(player.getName());
                Continuation command = Continuation.from(input);
                if (command.isStop()) {
                    if (isFirst) {
                        OutputView.printCardByPlayer(player);
                    }
                    break;
                }
                dispenser.dispense(player, 1);
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
        OutputView.printCardByPlayerWithScore(dealer, sum);
        for (Player player : players) {
            int playerSum = calculateSum(player.getCards());
            OutputView.printCardByPlayerWithScore(player, playerSum);
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
