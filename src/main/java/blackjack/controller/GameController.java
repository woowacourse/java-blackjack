package blackjack.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

public class GameController {

    public void play() {
        final Game game = initPlay();

        drawPlayerCards(game);
        drawDealerCards(game);

        ParticipantsResult(game);
        playRecord(game);
    }

    private Game initPlay() {
        final List<String> names = InputView.requestPlayerNames();
        final Game game = new Game(CardFactory.create(), names);

        game.init();

        OutputView.printInitResult(names);
        OutputView.printDealerFirstCard(game.openCard());
        final List<Player> players = game.getPlayers();
        for (Player player : players) {
            OutputView.printPlayerCards(new ParticipantVo(player));
        }
        OutputView.printEmptyLine();
        return game;
    }

    private void drawPlayerCards(Game game) {
        while (game.findHitPlayer().isPresent()) {
            final Player player = game.findHitPlayer().get();
            final Status hitOrStay = InputView.requestHitOrStay(player.getName());

            game.drawPlayerCard(player, hitOrStay);

            OutputView.printPlayerCards(new ParticipantVo(player));
        }
    }

    private void drawDealerCards(Game game) {
        final CardCount cardCount = game.drawDealerCard();
        OutputView.printDealerDrawCardCount(cardCount);
    }

    private void ParticipantsResult(Game game) {
        OutputView.printParticipantCards(new ParticipantVo(game.getDealer()));
        for (Player player : game.getPlayers()) {
            OutputView.printParticipantCards(new ParticipantVo(player));
        }
    }

    private void playRecord(Game game) {
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
}