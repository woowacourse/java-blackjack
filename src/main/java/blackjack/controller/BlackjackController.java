package blackjack.controller;

import static blackjack.view.InputView.requestPlayerNamesInput;
import static blackjack.view.OutputView.printBettingResults;
import static blackjack.view.OutputView.printDealerBlackjack;
import static blackjack.view.OutputView.printInitialDistribution;

import blackjack.domain.BlackjackGame;
import blackjack.domain.betting.BettingResults;
import blackjack.domain.betting.PlayerBettings;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardStack;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.dto.InitialDistributionDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    public BlackjackGame initializeGame() {
        final CardStack cardDeck = new CardDeck();
        final List<String> playerNames = requestPlayerNamesInput();

        return new BlackjackGame(cardDeck, playerNames);
    }

    public PlayerBettings initializeBettings(final BlackjackGame game) {
        Participants participants = game.getParticipants();
        List<Participant> players = participants.getPlayers();

        return PlayerBettings.of(players, InputView::requestBettingAmountInput);
    }

    public void playGame(final BlackjackGame game) {
        final InitialDistributionDto dto = InitialDistributionDto.of(game);

        if (game.isBlackjackDealer()){
            printDealerBlackjack(dto);
            return;
        }

        printInitialDistribution(dto);
        distributeAllCards(game);
    }

    private void distributeAllCards(final BlackjackGame game) {
        game.drawAllPlayerCards(
                InputView::requestMoreCardInput, OutputView::printHitResult);
        game.drawDealerCards(OutputView::printDealerExtraCardInfo);
    }

    public void showBettingResults(final BlackjackGame game, final PlayerBettings bettings) {
        final Participants participants = game.getParticipants();
        final Participant dealer = participants.getDealer();

        final BettingResults results = new BettingResults(dealer, bettings);
        printBettingResults(results.getValue());
    }
}
