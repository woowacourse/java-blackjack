import domain.Dealer;
import domain.GameCommand;
import domain.GameStatistics;
import domain.Participant;
import domain.Participants;
import domain.Referee;
import domain.card.Deck;
import java.util.List;
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
        List<String> playerNames = InputView.readPlayerNames();
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

    //TODO: depth 2
    private void determineGameState(Deck deck, Participant participant) {
        while (participant.isHit()) {
            String input = InputView.readHitOrStand(participant.getName());
            GameCommand gameCommand = GameCommand.from(input);
            if (gameCommand.isNo()) {
                participant.changeState();
                break;
            }
            participant.playTurn(deck);
            OutputView.showCardName(participant);
        }
    }

    private void playDealerTurn(Dealer dealer, Deck deck) {
        while (dealer.isHit()) {
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
