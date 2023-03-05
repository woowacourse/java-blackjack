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

        hitParticipantsCard(blackjackGame, cards, cardPickerGenerator);

        printResult(participants, blackjackGame);
    }

    private void hitParticipantsCard(final BlackjackGame blackjackGame, final Cards cards, final CardPickerGenerator cardPickerGenerator) {
        List<Player> players = blackjackGame.findPlayers();
        for (Player player : players) {
            hitPlayerCard(player, cards, cardPickerGenerator);
        }
        blackjackGame.dealerHitCard(cards,cardPickerGenerator);
        outputView.hitDealerCount(blackjackGame.findDealer());
    }

    private void printResult(final Participants participants, final BlackjackGame blackjackGame) {
        for(Participant participant : participants.getParticipants()) {
            outputView.printTotalCardsAndScore(participant);
        }
        outputView.printDealerWinORLose(blackjackGame.generateDealerResult());
        outputView.printPlayerWinORLose(blackjackGame.generatePlayersResult());
    }

    private void hitPlayerCard(final Player player, final Cards cards, final CardPickerGenerator cardPickerGenerator) {
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
