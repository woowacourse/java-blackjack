package blackjack.controller;

import static blackjack.view.InputView.*;
import static blackjack.view.OutputView.*;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;

import blackjack.domain.Game;
import blackjack.domain.PlayRecord;
import blackjack.domain.PlayStatus;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.deckstrategy.ShuffleDeck;
import blackjack.domain.participant.Betting;
import blackjack.domain.participant.DrawCount;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.dto.ParticipantDto;

public class GameController {

    public void play() {
        List<Name> names = getNames();
        Game game = initPlay(names, getBettings(names));

        drawPlayerCards(game);
        drawDealerCards(game);

        finalParticipantsCards(game);
        finalRevenue(game);
    }

    private List<Name> getNames() {
        return requestPlayerNames().stream()
            .map(Name::of)
            .collect(toUnmodifiableList());
    }

    private List<Betting> getBettings(List<Name> names) {
        return names.stream()
            .map(name -> new Betting(name, inputBettingMoney(name)))
            .collect(toUnmodifiableList());
    }

    private Game initPlay(List<Name> names, List<Betting> bettings) {
        Game game = new Game(new CardDeck(new ShuffleDeck()), names, bettings);

        printInitResult(names);
        printDealerFirstCard(game.dealerFirstCard());

        for (Player player : game.getPlayers()) {
            printPlayerCards(convertToDto(player));
        }
        printEmptyLine();
        return game;
    }

    private ParticipantDto convertToDto(Participant participant) {
        return ModelMapper.map(participant);
    }

    /**
     * 플레이어 목록을 순회하여 상태가 HIT 인 플레이어가 존재하지 않을 때까지 반복한다.
     * 카드 합이 21인 경우(블랙잭인 경우 포함) 자동으로 STAY 상태가 되므로 무시한다.
     * 플레이어 목록이 빈 경우 IllegalStateException 을 던진다.
     * @param game initPlay 로 초기화된 game 을 받는다.
     */
    private void drawPlayerCards(Game game) {
        validatePlayersPresent(game.getPlayers());

        while (game.findHitPlayer().isPresent()) {
            Player player = game.findHitPlayer().get();
            PlayStatus hitOrStay = requestHitOrStay(player.getName());

            game.drawPlayerCard(player, hitOrStay);

            printPlayerCards(convertToDto(player));
        }
    }

    private void validatePlayersPresent(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalStateException("플레이어가 존재하지 않습니다.");
        }
    }

    private void drawDealerCards(Game game) {
        DrawCount drawCount = game.drawDealerCards();
        printDealerDrawCardCount(drawCount);
    }

    private void finalParticipantsCards(Game game) {
        printParticipantCardsWithScore(convertToDto(game.getDealer()));
        for (Player player : game.getPlayers()) {
            printParticipantCardsWithScore(convertToDto(player));
        }
    }

    private void finalRevenue(Game game) {
        Map<Name, PlayRecord> recordMap = PlayRecord.createPlayRecords(game.getPlayers(), game.getDealer());

        printFinalRevenues(game.getRevenues(recordMap));
    }
}