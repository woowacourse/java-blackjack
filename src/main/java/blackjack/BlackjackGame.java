package blackjack;

import blackjack.domain.GameCommand;
import blackjack.domain.GameStatistics;
import blackjack.domain.Participants;
import blackjack.domain.Referee;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackGame {

    private final Deck deck;

    public BlackjackGame(Deck deck) {
        this.deck = deck;
    }

    public void run() {
        Participants participants = createParticipants();
        playTurns(participants);
        judge(participants);
    }

    private Participants createParticipants() {
        Dealer dealer = new Dealer(deck.dealInitialCards());
        List<Player> players = createPlayers();
        Participants participants = new Participants(dealer, players);
        OutputView.showCardNames(participants);
        return participants;
    }

    private List<Player> createPlayers() {
        List<String> playerNames = InputView.readPlayerNames();
        return playerNames.stream()
                .map(name -> new Player(name, deck.dealInitialCards()))
                .toList();
    }

    private void playTurns(Participants participants) {
        participants.playPlayerTurn(this::hitByPlayer);
        participants.playDealerTurn(this::hitByDealer);
        OutputView.showResult(participants);
    }

    private void hitByPlayer(Player player) {
        while (!player.isBust() && wantHit(player)) {
            player.playTurn(deck);
            OutputView.showCardName(player);
        }
    }

    private boolean wantHit(Player player) {
        String input = InputView.readHitOrStand(player.getName());
        GameCommand command = GameCommand.from(input);
        return command.isYes();
    }

    private void hitByDealer(Dealer dealer) {
        while (!dealer.isStand()) {
            dealer.playTurn(deck);
            OutputView.showDealerMessage();
        }
    }

    private void judge(Participants participants) {
        Referee referee = new Referee();
        GameStatistics statistics = referee.judge(participants);
        OutputView.showGameResult(statistics);
    }
}
