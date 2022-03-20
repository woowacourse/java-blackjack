package blackjack;

import blackjack.domain.HitCommand;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.ParticipantCards;
import blackjack.dto.ParticipantProfit;
import blackjack.dto.ParticipantScoreResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final Players players;

    public BlackjackGame() {
        this.cardDeck = CardDeck.createNewShuffledCardDeck();
        this.dealer = new Dealer(cardDeck);
        this.players = Players
                .createPlayers(InputView.inputPlayerNames(), InputView::inputPlayerBetMoney, cardDeck);
    }

    public void run() {
        printParticipantsFirstCards();
        runPlayerTurn();
        runDealerTurn();
        printAllParticipantCards();
        printParticipantProfits();
    }

    private void printParticipantsFirstCards() {
        OutputView.printParticipantsFirstCards(ParticipantCards.createDealerFirstCards(dealer), playersCards());
    }

    private List<ParticipantCards> playersCards() {
        return players.players()
                .stream()
                .map(ParticipantCards::from)
                .collect(Collectors.toList());
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

    private void printAllParticipantCards() {
        OutputView.printPlayerScoreResult(ParticipantScoreResult.from(dealer));
        OutputView.printParticipantScoreResults(players.players()
                .stream()
                .map(ParticipantScoreResult::from)
                .collect(Collectors.toList()));
    }

    private void printParticipantProfits() {
        OutputView.printParticipantProfits(
                ParticipantProfit.createDealerProfit(dealer, players),
                players.players()
                        .stream()
                        .map(player -> ParticipantProfit.createPlayerProfit(player, dealer))
                        .collect(Collectors.toList())
        );
    }
}
