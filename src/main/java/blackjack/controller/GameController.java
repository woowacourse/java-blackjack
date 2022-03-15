package blackjack.controller;

import static blackjack.view.InputView.*;
import static blackjack.view.OutputView.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import blackjack.domain.Game;
import blackjack.domain.PlayRecord;
import blackjack.domain.PlayStatus;
import blackjack.domain.RecordFactory;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.deckstrategy.RandomDeck;
import blackjack.domain.participant.DrawCount;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.dto.ParticipantDto;

public class GameController {

    public void play() {
        Game game = initPlay();

        drawPlayerCards(game);
        drawDealerCards(game);

        participantsResult(game);
        playRecord(game);
    }

    private Game initPlay() {
        List<String> names = requestPlayerNames();
        Game game = new Game(new CardDeck(new RandomDeck()), names);

        printInitResult(names);
        printDealerFirstCard(game.dealerFirstCard());
        for (Player player : game.getPlayers()) {
            printPlayerCards(Objects.requireNonNull(convertToDto(player)));
        }
        printEmptyLine();
        return game;
    }

    private ParticipantDto convertToDto(Participant participant) {
        return ModelMapper.map(participant);
    }

    private void drawPlayerCards(Game game) {
        while (game.findHitPlayer().isPresent()) {
            Player player = game.findHitPlayer().get();
            PlayStatus hitOrStay = requestHitOrStay(player.getName());

            game.drawPlayerCard(player, hitOrStay);

            printPlayerCards(convertToDto(player));
        }
    }

    private void drawDealerCards(Game game) {
        DrawCount drawCount = game.drawDealerCards();
        printDealerDrawCardCount(drawCount);
    }

    private void participantsResult(Game game) {
        printParticipantCardsWithScore(convertToDto(game.getDealer()));
        for (Player player : game.getPlayers()) {
            printParticipantCardsWithScore(convertToDto(player));
        }
    }

    private void playRecord(Game game) {
        RecordFactory recordFactory = new RecordFactory(game.getDealerScore());
        Map<Name, PlayRecord> map = game.getPlayers().stream()
            .collect(Collectors.toMap(Player::getName, player -> recordFactory.getPlayerRecord(player.getScore()),
                (recordA, recordB) -> recordB, LinkedHashMap::new));

        printDealerRecord(recordFactory.getDealerRecord());
        for (Entry<Name, PlayRecord> entry : map.entrySet()) {
            printPlayerRecord(entry.getKey(), entry.getValue());
        }
    }
}