package blackjack;

import blackjack.domain.HitCommand;
import blackjack.domain.card.CardDeck;
import blackjack.domain.paticipant.Dealer;
import blackjack.domain.paticipant.Participant;
import blackjack.domain.paticipant.Player;
import blackjack.domain.paticipant.Players;
import blackjack.dto.ParticipantCards;
import blackjack.dto.ParticipantScoreResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final Players players;

    public BlackjackGame() {
        this.cardDeck = CardDeck.createNewShuffledCardDeck();
        this.dealer = new Dealer(cardDeck);
        this.players = new Players(InputView.inputPlayerNames(), InputView::inputPlayerBetMoney, cardDeck);
    }

    public void run() {
        printParticipantsFirstCards();
        runPlayerTurn();
        runDealerTurn();
        printAllResults();
        printAllProfits();
    }

    private void printParticipantsFirstCards() {
        OutputView.printParticipantsFirstCards(ParticipantCards.from(dealer), players.players()
                .stream()
                .map(ParticipantCards::from)
                .collect(Collectors.toList()));
    }

    private void runPlayerTurn() {
        if (players.isAllTurnEnd()) {
            return;
        }
        final Player player = runPlayerTurn(inputHitCommand());
        OutputView.printPlayerCards(ParticipantCards.from(player));
        runPlayerTurn();
    }

    private HitCommand inputHitCommand() {
        return HitCommand.from(InputView.inputHitCommand(players.currentTurnPlayerName()));
    }

    private Player runPlayerTurn(final HitCommand hitCommand) {
        if (hitCommand.isNo()) {
            final Player currentPlayer = players.currentTurnPlayer();
            players.stayCurrentTurnPlayer();
            return currentPlayer;
        }
        return players.hitCurrentTurnPlayer(cardDeck.provideCard());
    }

    private void runDealerTurn() {
        if (dealer.isFinished()) {
            return;
        }
        dealer.hit(cardDeck.provideCard());
        OutputView.printDealerHit();
        runDealerTurn();
    }

    private void printAllResults() {
        printAllParticipantCards();
    }

    private void printAllParticipantCards() {
        final List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.players());
        OutputView.printParticipantScoreResults(participants.stream()
                .map(ParticipantScoreResult::from)
                .collect(Collectors.toList()));
    }

    private void printAllProfits() {
        OutputView.printProfitTitle();
        OutputView.printParticipantProfit(dealer.getName(), players.dealerProfit(dealer));
        players.players()
                .forEach(player -> OutputView.printParticipantProfit(player.getName(), player.profit(dealer)));
    }
}
