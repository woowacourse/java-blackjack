import domain.Dealer;
import domain.GameCommand;
import domain.GameStatistics;
import domain.Participants;
import domain.Referee;
import domain.card.Deck;
import domain.participant.Participant;
import java.util.List;

import util.Parser;
import view.InputView;
import view.OutputView;

public class BlackjackGame {

    public void run() {
        Deck deck = new Deck();
        Participants participants = createParticipants(deck);
        Dealer dealer = new Dealer(deck);

        showCardNames(participants, dealer);
        playTurn(participants, deck, dealer);
        judge(dealer, participants);
    }

    private Participants createParticipants(Deck deck) {
        String inputNames = InputView.readPlayerNames();
        List<String> playerNames = Parser.parseByDelimiter(inputNames);
        return new Participants(playerNames, deck);
    }

    private void showCardNames(Participants participants, Dealer dealer) {
        OutputView.showIntroMessage(participants);
        OutputView.showDealerCardName(dealer);
        OutputView.showPlayerCardName(participants);
    }

    private void playPlayerTurn(Participants participants, Deck deck) {
        participants.getParticipants()
                .forEach(participant -> determineGameState(deck, participant));
    }

    private void determineGameState(Deck deck, Participant participant) {
        String input = InputView.readHitOrStand(participant.getName());
        GameCommand gameCommand = GameCommand.from(input);
        if (gameCommand.isNo()) {
            return;
        }
        participant.playTurn(deck);
        OutputView.showCardName(participant);
        if (!participant.isBust()) {
            determineGameState(deck, participant);
        }
    }

    private void playDealerTurn(Dealer dealer, Deck deck) {
        while (dealer.stay()) {
            OutputView.showDealerMessage();
            dealer.playTurn(deck);
        }
    }

    private void playTurn(Participants participants, Deck deck, Dealer dealer) {
        playPlayerTurn(participants, deck);
        playDealerTurn(dealer, deck);
        OutputView.showResult(dealer, participants);
    }

    private void judge(Dealer dealer, Participants participants) {
        Referee referee = new Referee();
        GameStatistics statistics = referee.judge(dealer, participants);
        OutputView.showGameResult(statistics);
    }
}
