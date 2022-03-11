package blackjack.controller;

import blackjack.domain.Game;
import blackjack.domain.Record;
import blackjack.domain.RecordFactory;
import blackjack.domain.card.CardCount;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Status;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.vo.ParticipantVo;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GameController {

    public void play() {
        final Game game = createGame();

        game.init();
        OutputView.printInitResult(game.getNames());
        OutputView.printDealerFirstCard(game.openCard());
        for (Player player : game.getPlayers()) {
            OutputView.printPlayerCards(new ParticipantVo(player));
        }

        while (game.findHitPlayer().isPresent()) {
            final Player player = game.findHitPlayer().get();
            final Status hitOrStay = getHitOrStay(player);

            game.drawPlayerCard(player, hitOrStay);

            OutputView.printPlayerCards(new ParticipantVo(player));
        }

        final CardCount cardCount = game.drawDealerCard();
        OutputView.printDealerDrawCardCount(cardCount);

        OutputView.printParticipantCards(new ParticipantVo(game.getDealer()));
        for (Player player : game.getPlayers()) {
            OutputView.printParticipantCards(new ParticipantVo(player));
        }

        final RecordFactory recordFactory = new RecordFactory(game.getDealerScore());
        final Map<String, Record> map = new LinkedHashMap<>();

        for (Player player : game.getPlayers()) {
            final Record record = recordFactory.getPlayerRecord(player.getScore());
            map.put(player.getName(), record);
        }

        OutputView.printDealerRecord(recordFactory.getDealerRecord());
        for (Entry<String, Record> entry : map.entrySet()) {
            OutputView.printPlayerRecord(entry.getKey(), entry.getValue());
        }
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

    private Status getHitOrStay(final Player player) {
        try {
            return InputView.requestHitOrStay(player.getName());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return getHitOrStay(player);
        }
    }
}