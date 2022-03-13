package blackjack.controller;

import blackjack.domain.RecordFactory;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Status;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.vo.ParticipantVo;

public class GameController {

    public void play() {
        final CardFactory cardFactory = CardFactory.create();
        final Dealer dealer = new Dealer();
        final Players players = enterPlayers();

        init(dealer, players, cardFactory);

        progressPlayerTurns(players, cardFactory);

        progressDealerTurn(dealer, cardFactory);

        endGame(dealer, players);
    }

    private Players enterPlayers() {
        try {
            return new Players(InputView.requestPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return enterPlayers();
        }
    }

    private void init(final Dealer dealer, final Players players, final CardFactory cardFactory) {
        dealer.prepareGame(cardFactory);
        players.prepareGame(cardFactory);

        OutputView.printInitResult(players.getNames());
        OutputView.printDealerFirstCard(dealer.openFirstCard());
        players.getValue().stream()
                .map(ParticipantVo::new)
                .forEach(OutputView::printPlayerCards);
    }

    private void progressPlayerTurns(final Players players, final CardFactory cardFactory) {
        while (players.findHitPlayer().isPresent()) {
            final Player player = players.findHitPlayer().get();
            final Status status = getHitOrStay(player);

            if (status == Status.HIT) {
                player.hit(cardFactory);
            }
            if (status == Status.STAY) {
                player.stay();
            }

            OutputView.printPlayerCards(new ParticipantVo(player));
        }
    }

    private Status getHitOrStay(final Player player) {
        try {
            return InputView.requestHitOrStay(player.getName());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return getHitOrStay(player);
        }
    }

    private void progressDealerTurn(final Dealer dealer, final CardFactory cardFactory) {
        if (!dealer.canDrawCard()) {
            OutputView.printDealerNotDrawMessage();
            return;
        }

        do {
            dealer.hit(cardFactory);
            OutputView.printDealerDrawMessage();
        } while (dealer.canDrawCard());
    }

    private void endGame(final Dealer dealer, final Players players) {
        OutputView.breakLine();
        OutputView.printParticipantCards(new ParticipantVo(dealer));
        players.getValue().stream()
                .map(ParticipantVo::new)
                .forEach(OutputView::printParticipantCards);

        final RecordFactory factory = new RecordFactory(dealer.getScore(), players.getValue());
        OutputView.printRecord(factory.getDealerRecord(), factory.getAllPlayerRecord());
    }
}