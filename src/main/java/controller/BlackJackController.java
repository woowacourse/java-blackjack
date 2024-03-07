package controller;

import java.util.ArrayList;
import java.util.List;
import model.BlackJack;
import model.card.Cards;
import model.player.Dealer;
import model.player.Participant;
import model.player.Player;
import model.player.Players;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;
    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }
    
    public void run() {
        BlackJack blackJack = createBlackJack();
        blackJack.offerCardToPlayers(2);
        outputView.printStartBlackJack(blackJack.findPlayers(), blackJack.findDealer());

        for (Player player : blackJack.findPlayers()) {
            while (!player.isOverMaximumSum()) {
                String name = player.getName();
                if (!inputView.inputEnd(name)) {
                    break;
                }
                blackJack.offerCardToPlayer(name, 1);
                outputView.printPlayerCardMessage(player);
            }
        }

        while (blackJack.isDealerUnderThreshold()) {
            outputView.printDealerAddCard();
            blackJack.offerCardToPlayer("딜러", 1);
        }

        outputView.printResult(blackJack.findPlayers(), blackJack.findDealer());


    }

    private BlackJack createBlackJack() {
        List<String> names = inputView.inputParticipantNames();
        List<Player> players = new ArrayList<>(names.stream()
                .map(name -> (Player) new Participant(name)).toList());
        players.add(new Dealer());
        return new BlackJack(new Players(players, new Cards()));
    }
}
