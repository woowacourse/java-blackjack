package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.BlackjackResult;
import blackjack.domain.Cards;
import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.util.CardPickerGenerator;
import blackjack.util.RandomCardPickerGenerator;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> playersName = inputView.readPlayerName();

        Participants participants = Participants.generate(playersName);
        CardPickerGenerator cardPickerGenerator = new RandomCardPickerGenerator();
        Cards cards = Cards.generator(cardPickerGenerator);
        BlackjackGame blackjackGame = new BlackjackGame(participants, cards);

        gameSetting(blackjackGame);
        playersHitCard(blackjackGame);
        dealerHitCard(blackjackGame);
        printResult(blackjackGame);
    }

    private void gameSetting(final BlackjackGame blackjackGame) {
        blackjackGame.settingGame();
        outputView.printParticipants(blackjackGame.getParticipants().getParticipantsName());
        outputView.printParticipantsCard(blackjackGame.getParticipants());
    }

    private void playersHitCard(final BlackjackGame blackjackGame) {
        for(Player player : blackjackGame.getParticipants().extractPlayers()) {
            hitCard(player, blackjackGame.getCards());
        }
    }

    private void hitCard(final Player player, final Cards cards) {
        while (player.decideHit() && inputView.readHitCommand(player.getName()).equals("y")) {
            player.hit(cards.pick());
            outputView.printCurrentCards(player);
        }
        if (player.decideHit()) {
            outputView.printCurrentCards(player);
        }
    }

    private void dealerHitCard(final BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getParticipants().extractDealer();
        while (dealer.decideHit()) {
            outputView.dealerHitMessage();
            dealer.hit(blackjackGame.getCards().pick());
        }
    }

    private void printResult(final BlackjackGame blackjackGame) {
        for(Participant player : blackjackGame.getParticipants().getParticipants()) {
            outputView.printTotalCardsAndScore(player);
        }
        BlackjackResult blackjackResult = blackjackGame.generateBlackjackResult();
        outputView.printDealerWinORLose(blackjackResult.getDealerResult());
        outputView.printPlayerWinORLose(blackjackResult.getPlayerResult());
    }
}
