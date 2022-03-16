package blackjack.controller;

import blackjack.domain.Record;
import blackjack.domain.card.Deck;
import blackjack.domain.card.ShuffleOrderStrategy;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayerAnswer;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameController {

    private final Deck deck;
    private final Players players;
    private final Dealer dealer;

    public GameController() {
        this.deck = Deck.from(new ShuffleOrderStrategy());
        this.players = initPlayers();
        this.dealer = new Dealer();
    }

    private Players initPlayers() {
        try {
            return new Players(InputView.getPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return initPlayers();
        }
    }

    public void initParticipants() {
        dealer.initCards(deck);
        players.initCards(deck);

        OutputView.printInitResult(players.getNames());
        OutputView.printDealerFirstCard(dealer.openFirstCard());

        final List<Player> allPlayers = players.getValue();
        allPlayers.forEach(OutputView::printCards);
    }

    public void progressPlayerTurns() {
        while (players.findDrawablePlayer().isPresent()) {
            final Player drawablePlayer = players.findDrawablePlayer().get();
            final PlayerAnswer playerAnswer = InputView.getHitOrStay(drawablePlayer.getName());

            final Player player = progressPlayerTurn(drawablePlayer, playerAnswer);
            OutputView.printCards(player);
        }
    }

    private Player progressPlayerTurn(Player player, final PlayerAnswer playerAnswer) {
        if (playerAnswer.isDraw()) {
            player.hit(deck);
        }
        if (!playerAnswer.isDraw()) {
            player.stay();
        }

        return player;
    }

    public void progressDealerTurn() {
        int count = 0;
        while (dealer.canDrawCard()) {
            dealer.hit(deck);
            count++;
        }

        OutputView.printDealerTurnResult(count);
    }

    public void endGame() {
        printAllCards();
        printAllRecords();
    }

    private void printAllCards() {
        OutputView.breakLine();

        OutputView.printCardsAndScore(dealer);
        players.getValue().forEach(OutputView::printCardsAndScore);
    }

    private void printAllRecords() {
        final int dealerScore = dealer.getScore();

        printDealerRecord(dealerScore);
        printPlayerRecords(dealerScore);
    }

    private void printDealerRecord(final int dealerScore) {
        final Map<String, Integer> map = initMap();
        players.getValue().stream()
                .map(Player::getScore)
                .map(playerScore -> Record.of(dealerScore, playerScore))
                .map(playerRecord -> Record.fromOppositeName(playerRecord.getName()))
                .forEach(dealerRecord -> map.put(dealerRecord.getName(), map.get(dealerRecord.getName()) + 1));

        OutputView.printDealerRecord(map);
    }

    private Map<String, Integer> initMap() {
        return Arrays.stream(Record.values())
                .map(Record::getName)
                .collect(Collectors.toMap(
                        recordName -> recordName,
                        recordName -> 0,
                        (a, b) -> a,
                        LinkedHashMap::new));
    }

    private void printPlayerRecords(final int dealerScore) {
        players.getValue().forEach(
                player -> OutputView.printPlayerRecord(player.getName(), Record.of(dealerScore, player.getScore())));
    }
}