package blackjack.controller;

import static blackjack.view.InputView.*;
import static blackjack.view.OutputView.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import blackjack.domain.Game;
import blackjack.domain.PlayStatus;
import blackjack.domain.Record;
import blackjack.domain.RecordFactory;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.deckstrategy.RandomDeck;
import blackjack.domain.participant.DrawCount;
import blackjack.domain.participant.Player;
import blackjack.dto.ParticipantDto;

public class GameController {

    public void play() {
        Game game = initPlay();

        drawPlayerCards(game);
        drawDealerCards(game);

        ParticipantsResult(game);
        playRecord(game);
    }

    private Game initPlay() {
        List<String> names = requestPlayerNames();
        Game game = new Game(new CardDeck(new RandomDeck()), names);

        printInitResult(names);
        printDealerFirstCard(game.dealerFirstCard());
        for (Player player : game.getPlayers()) {
            printPlayerCards(new ParticipantDto(player));
        }
        printEmptyLine();
        return game;
    }

    private void drawPlayerCards(Game game) {
        while (game.findHitPlayer().isPresent()) {
            Player player = game.findHitPlayer().get();
            PlayStatus hitOrStay = requestHitOrStay(player.getName());

            game.drawPlayerCard(player, hitOrStay);

            printPlayerCards(new ParticipantDto(player));
        }
    }

    private void drawDealerCards(Game game) {
        DrawCount drawCount = game.drawDealerCards();
        printDealerDrawCardCount(drawCount);
    }

    private void ParticipantsResult(Game game) {
        printParticipantCardsWithScore(new ParticipantDto(game.getDealer()));
        for (Player player : game.getPlayers()) {
            printParticipantCardsWithScore(new ParticipantDto(player));
        }
    }

    private void playRecord(Game game) {
        RecordFactory recordFactory = new RecordFactory(game.getDealerScore());
        Map<String, Record> map = game.getPlayers().stream()
            .collect(Collectors.toMap(Player::getName, player -> recordFactory.getPlayerRecord(player.getScore()),
                (a, b) -> b, LinkedHashMap::new));

        printDealerRecord(recordFactory.getDealerRecord());
        for (Entry<String, Record> entry : map.entrySet()) {
            printPlayerRecord(entry.getKey(), entry.getValue());
        }
    }
}