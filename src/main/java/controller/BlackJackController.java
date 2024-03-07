package controller;

import java.util.ArrayList;
import java.util.List;
import model.BlackJack;
import model.player.Dealer;
import model.player.Participant;
import model.player.Participants;
import model.player.Player;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;
    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    // TODO: 코드줄 줄이기
    public void run() {
        BlackJack blackJack = createBlackJack();
        blackJack.offerCardToPlayers(2);
        outputView.printStartBlackJack(blackJack.getParticipants(), blackJack.getDealer());

        // TODO : 인덴트 줄이기
        for (Player player : blackJack.getParticipants()) {
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
            blackJack.offerCardToDealer(1);
        }

        outputView.printResult(blackJack.getParticipants(), blackJack.getDealer());

        outputView.printGameResult(blackJack.findResult());

    }

    private BlackJack createBlackJack() {
        List<String> names = inputView.inputParticipantNames();
        List<Participant> players = new ArrayList<>(names.stream()
                .map(Participant::new).toList());
        return new BlackJack(new Participants(players),new Dealer());
    }
}
