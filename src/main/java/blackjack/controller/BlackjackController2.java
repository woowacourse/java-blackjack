package blackjack.controller;

import static blackjack.view.InputView.requestPlayerNamesInput;
import static blackjack.view.OutputView2.printBettingResults;
import static blackjack.view.OutputView2.printDealerBlackjackInfo;
import static blackjack.view.OutputView2.printInitialParticipantsCards;

import blackjack.domain.BlackjackGame2;
import blackjack.domain.betting2.BettingResults;
import blackjack.domain.betting2.PlayerBettings;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardStack;
import blackjack.domain.participant2.Participant;
import blackjack.domain.participant2.Participants;
import blackjack.dto2.InitialDistributionDto;
import blackjack.view.InputView;
import blackjack.view.OutputView2;
import java.util.List;

public class BlackjackController2 {

    public BlackjackGame2 initializeGame() {
        final CardStack cardDeck = new CardDeck();
        final List<String> playerNames = requestPlayerNamesInput();

        return new BlackjackGame2(cardDeck, playerNames);
    }

    public PlayerBettings initializeBettings(final BlackjackGame2 game) {
        List<Participant> players = game.getParticipants().getPlayers();

        return PlayerBettings.of(players, InputView::requestBettingAmountInput);
    }

    public void playGame(final BlackjackGame2 game) {
        final InitialDistributionDto dto = InitialDistributionDto.of(game);

        if (game.getParticipants().getDealer().getHand().isBlackjack()){
            printDealerBlackjackInfo(dto);
            return;
        }

        printInitialParticipantsCards(dto);
        distributeAllCards(game);
    }

    private void distributeAllCards(final BlackjackGame2 game) {
        game.drawAllPlayerCards(
                InputView::requestMoreCardInput,
                OutputView2::printPlayerCardDistributionInfo);
        game.drawDealerCards(OutputView2::printDealerExtraCardInfo);
    }

    public void showBettingResults(final BlackjackGame2 game, final PlayerBettings bettings) {
        final Participants participants = game.getParticipants();
        final Participant dealer = participants.getDealer();

        final BettingResults results = new BettingResults(dealer, bettings);
        printBettingResults(results.getValue());
    }
}
