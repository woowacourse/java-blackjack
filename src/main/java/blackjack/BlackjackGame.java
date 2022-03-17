package blackjack;

import blackjack.domain.HitCommand;
import blackjack.domain.card.CardDeck;
import blackjack.domain.paticipant.Dealer;
import blackjack.domain.paticipant.Name;
import blackjack.domain.paticipant.Player;
import blackjack.domain.paticipant.Players;
import blackjack.dto.ParticipantCards;
import blackjack.dto.ParticipantScoreResult;
import blackjack.dto.PlayerProfit;
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
                .createPlayers(toNames(InputView.inputPlayerNames()), InputView::inputPlayerBetMoney, cardDeck);
    }

    private List<Name> toNames(final List<String> names) {
        return names.stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    public void run() {
        printParticipantsFirstCards();
        runPlayerTurn();
        runDealerTurn();
        printAllParticipantCards();
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

    private void printAllParticipantCards() {
        OutputView.printPlayerScoreResult(ParticipantScoreResult.from(dealer));
        OutputView.printParticipantScoreResults(players.players()
                .stream()
                .map(ParticipantScoreResult::from)
                .collect(Collectors.toList()));
    }

    private void printAllProfits() {
        OutputView.printProfitTitle();
        OutputView.printParticipantProfit(dealer.getName(), players.dealerProfit(dealer));
        final List<PlayerProfit> playerProfits = players.players()
                .stream()
                .map(player -> PlayerProfit.of(player, dealer))
                .collect(Collectors.toList());
        OutputView.printParticipantProfits(playerProfits);
    }
}
