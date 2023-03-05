package blackjack.controller;

import blackjack.domain.*;
import blackjack.util.CardPickerGenerator;
import blackjack.util.RandomCardPickerGenerator;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> playersName = inputView.readPlayerName();

        Participants participants = Participants.generate(playersName);
        Cards cards = Cards.generator();
        CardPickerGenerator cardPickerGenerator = new RandomCardPickerGenerator();
        BlackjackGame blackjackGame = new BlackjackGame(participants, cards);

        gameSetting(participants, cardPickerGenerator, blackjackGame);

        playersHitCard(blackjackGame, cards, cardPickerGenerator);

        dealerHitCard(blackjackGame, cards, cardPickerGenerator);

        printResult(participants, blackjackGame);

    }

    private void playersHitCard(final BlackjackGame blackjackGame, final Cards cards, final CardPickerGenerator cardPickerGenerator) {
        List<Player> players = blackjackGame.findPlayers();
        for(int i = 0, end =players.size(); i < end; i++) {
            hitCard(players.get(i), cards, cardPickerGenerator);
        }
    }

    private void printResult(final Participants participants, final BlackjackGame blackjackGame) {
        for(Participant participant : participants.getParticipants()) {
            outputView.printTotalCardsAndScore(participant);
        }
        outputView.printDealerWinORLose(blackjackGame.generateDealerResult());
        outputView.printPlayerWinORLose(blackjackGame.generatePlayersResult());
    }

    private void dealerHitCard(final BlackjackGame blackjackGame, final Cards cards, final CardPickerGenerator cardPickerGenerator) {
        Dealer dealer = blackjackGame.findDealer();
        while (dealer.decideHit()) {
            outputView.dealerHitMessage();
            dealer.hit(cards.pick(cardPickerGenerator));
        }
    }

    private void hitCard(final Player player, final Cards cards, final CardPickerGenerator cardPickerGenerator) {
        while (player.decideHit() && inputView.readHitCommand(player.getName()).equals("y")) {
            player.hit(cards.pick(cardPickerGenerator));
            outputView.printCurrentCards(player);
        }
        if (player.decideHit()) {
            outputView.printCurrentCards(player);
        }
    }

    private void gameSetting(final Participants participants, final CardPickerGenerator cardPickerGenerator, final BlackjackGame blackjackGame) {
        blackjackGame.initFirstHit(cardPickerGenerator);
        outputView.printParticipants(participants.getParticipantsName());
        outputView.printParticipantsCard(blackjackGame.findDealer(), blackjackGame.findPlayers());
    }
}
