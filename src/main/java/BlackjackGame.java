import domain.GameCommand;
import domain.GameStatistics;
import domain.Participants;
import domain.Referee;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackjackGame {

    public void run() {
        Deck deck = new Deck();
        Participants participants = createPlayers(deck);

        OutputView.showCardNames(participants);
//        playTurn(participants, deck);
//        judge(participants);
    }

    private Participants createPlayers(Deck deck) {
        Dealer dealer = new Dealer(deck.dealInitialCards());
        List<String> playerNames = InputView.readPlayerNames();
        List<Player> players = playerNames.stream()
                .map(name -> new Player(new Name(name), deck.dealInitialCards()))
                .toList();
        return new Participants(dealer, players);
    }

//    private void playTurn(Participants participants, Deck deck, Dealer dealer) {
//        playPlayerTurn(participants, deck);
//        playDealerTurn(dealer, deck);
//        OutputView.showResult(dealer, participants);
//    }
//
//    private void playPlayerTurn(Participants participants, Deck deck) {
//        participants.getParticipants()
//                .forEach(player -> determineGameState(deck, player));
//    }

    private void determineGameState(Deck deck, Player player) {
        while (player.isHit()) {
            String input = InputView.readHitOrStand(player.getName());
            GameCommand gameCommand = GameCommand.from(input);
            if (gameCommand.isNo()) {
                player.changeState();
                break;
            }
            player.playTurn(deck);
            OutputView.showCardName(player);
        }
    }

    private void playDealerTurn(Dealer dealer, Deck deck) {
        while (dealer.isHit()) {
            OutputView.showDealerMessage();
            dealer.playTurn(deck);
        }
    }

    private void judge(Dealer dealer, Participants participants) {
        Referee referee = new Referee();
        GameStatistics statistics = referee.judge(dealer, participants);
        OutputView.showGameResult(statistics);
    }
}
