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
    
    public void startGame() {
        BlackJack blackJack = createBlackJack();
        blackJack.offerCardToPlayers(2);
        outputView.printStartBlackJack(blackJack.getParticipants(), blackJack.getDealer());

        askAndOfferMoreCards(blackJack);
        checkDealerMoreCards(blackJack);

        outputView.printResult(blackJack.getParticipants(), blackJack.getDealer());
        outputView.printGameResult(blackJack.findResult());
    }

    private void checkDealerMoreCards(BlackJack blackJack) {
        while (blackJack.isDealerUnderThreshold()) {
            outputView.printDealerAddCard();
            blackJack.offerCardToDealer(1);
        }
    }

    private void askAndOfferMoreCards(BlackJack blackJack) {
        for (Player player : blackJack.getParticipants()) {
            askAndOfferMoreCard(player, blackJack);
        }
    }

    private void askAndOfferMoreCard(Player player, BlackJack blackJack) {
        while (!player.isOverMaximumSum() && inputView.askOneMoreCard(player.getName())) {
            blackJack.offerCardToPlayer(player.getName(), 1);
            outputView.printPlayerCardMessage(player);
        }
    }

    private BlackJack createBlackJack() {
        List<String> names = inputView.askParticipantNames();
        List<Participant> players = new ArrayList<>(names.stream()
                .map(Participant::new).toList());
        return new BlackJack(new Participants(players),new Dealer());
    }
}
