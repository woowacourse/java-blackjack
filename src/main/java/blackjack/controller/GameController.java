package blackjack.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import blackjack.domain.Game;
import blackjack.domain.Record;
import blackjack.domain.RecordFactory;
import blackjack.domain.card.CardCount;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.PlayStatus;
import blackjack.domain.card.deckstrategy.RandomDeck;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.vo.ParticipantVo;

public class GameController {

    public void play() {
        Game game = initPlay();

        drawPlayerCards(game);
        drawDealerCards(game);

        ParticipantsResult(game);
        playRecord(game);
    }

    private Game initPlay() {
        List<String> names = InputView.requestPlayerNames();
        Game game = new Game(new CardDeck(new RandomDeck()), names);

        OutputView.printInitResult(names);
        OutputView.printDealerFirstCard(game.dealerFirstCard());
        for (Player player : game.getPlayers()) {
            OutputView.printPlayerCards(new ParticipantVo(player));
        }
        OutputView.printEmptyLine();
        return game;
    }

    private void drawPlayerCards(Game game) {
        while (game.findHitPlayer().isPresent()) {
            Player player = game.findHitPlayer().get();
            PlayStatus hitOrStay = InputView.requestHitOrStay(player.getName());

            game.drawPlayerCard(player, hitOrStay);

            OutputView.printPlayerCards(new ParticipantVo(player));
        }
    }

    private void drawDealerCards(Game game) {
        CardCount cardCount = game.drawDealerCards();
        OutputView.printDealerDrawCardCount(cardCount);
    }

    private void ParticipantsResult(Game game) {
        OutputView.printParticipantCards(new ParticipantVo(game.getDealer()));
        for (Player player : game.getPlayers()) {
            OutputView.printParticipantCards(new ParticipantVo(player));
        }
    }

    private void playRecord(Game game) {
        RecordFactory recordFactory = new RecordFactory(game.getDealerScore());
        Map<String, Record> map = game.getPlayers().stream()
            .collect(Collectors.toMap(Player::getName, player -> recordFactory.getPlayerRecord(player.getScore()),
                (a, b) -> b, LinkedHashMap::new));

        OutputView.printDealerRecord(recordFactory.getDealerRecord());
        for (Entry<String, Record> entry : map.entrySet()) {
            OutputView.printPlayerRecord(entry.getKey(), entry.getValue());
        }
    }
}