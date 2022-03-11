package blackjack.controller;

import blackjack.domain.Game;
import blackjack.domain.RecordFactory;
import blackjack.domain.card.CardCount;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Status;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.vo.ParticipantVo;
import java.util.List;

public class GameController {

    public void play() {
        final Game game = createGame();
        init(game);

        progressPlayerTurns(game);
        progressDealerTurn(game);

        endGame(game);
    }

    private Game createGame() {
        try {
            final List<String> names = InputView.requestPlayerNames();
            return new Game(CardFactory.create(), names);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return createGame();
        }
    }

    private void init(final Game game) {
        game.init();

        OutputView.printInitResult(game.getNames());
        OutputView.printDealerFirstCard(game.openCard());
        game.getPlayers().stream()
                .map(ParticipantVo::new)
                .forEach(OutputView::printPlayerCards);
    }

    private void progressPlayerTurns(final Game game) {
        while (game.findHitPlayer().isPresent()) {
            final Player player = game.findHitPlayer().get();
            final Status hitOrStay = getHitOrStay(player);

            game.drawPlayerCard(player, hitOrStay);

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

    private void progressDealerTurn(final Game game) {
        final CardCount cardCount = game.drawDealerCard();
        OutputView.printDealerDrawCardCount(cardCount);
    }

    private void endGame(final Game game) {
        OutputView.printParticipantCards(new ParticipantVo(game.getDealer()));
        game.getPlayers().stream()
                .map(ParticipantVo::new)
                .forEach(OutputView::printParticipantCards);


        final RecordFactory factory = game.getRecordFactory();
        OutputView.printRecord(factory.getDealerRecord(), factory.getAllPlayerRecord());
    }

}