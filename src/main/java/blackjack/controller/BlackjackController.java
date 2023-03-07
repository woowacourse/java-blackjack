package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckMaker;
import blackjack.domain.cardPicker.RandomCardPicker;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.ResultGame;
import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.Order;
import blackjack.view.OutputView;

import java.util.HashMap;
import java.util.List;

public class BlackjackController {

    private static final String DEALER_NAME = "딜러";
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Participants participants = makeParticipants();
        final Deck deck = makeDeck();
        final BlackjackGame blackjackGame = new BlackjackGame(participants, deck);

        startGame(blackjackGame);
        outputView.outputParticipantCards(makeParticipantsList(participants));
        hitParticipants(blackjackGame);
        showAllCardsAndScore(blackjackGame);
        showAllResult(blackjackGame);
    }

    private HashMap<String, List<List<String>>> makeParticipantsList(Participants participants) {
        HashMap<String, List<List<String>>> participantsMap = new HashMap<>();
        participantsMap.put(DEALER_NAME, participants.getDealer().getOneCard());
        participants.getPlayers().forEach(player -> participantsMap.put(player.getName(), player.getCardNames()));
        return participantsMap;
    }

    private Participants makeParticipants() {
        final Dealer dealer = new Dealer();
        return new Participants(dealer, inputView.inputPlayers());
    }

    private Deck makeDeck() {
        final DeckMaker deckMaker = new DeckMaker();
        return new Deck(deckMaker.makeDeck(), new RandomCardPicker());
    }

    private void startGame(final BlackjackGame blackjackGame) {
        final Participants participants = blackjackGame.getParticipants();
        final List<String> playerNames = participants.getPlayerNames();

        outputView.outputSplitMessage(playerNames);
        blackjackGame.giveTwoCardEveryone();
    }


    private void hitParticipants(final BlackjackGame blackjackGame) {
        hitPlayers(blackjackGame);
        hitDealer(blackjackGame);
    }

    private void hitPlayers(final BlackjackGame blackjackGame) {
        final List<Player> players = blackjackGame.getParticipants().getPlayers();

        for (final Player player : players) {
            hitEachPlayer(blackjackGame, player);
            outputView.changeLine();
        }
    }

    private void hitEachPlayer(final BlackjackGame blackjackGame, final Player player) {
        while (player.isNotBust() && isMoreHit(player)) {
            blackjackGame.drawCard(player);
            outputView.outputPlayerCard(player.getName(), player.getCardNames());
        }
    }

    private boolean isMoreHit(final Player player) {
        final Order order = Order.from(inputView.inputOrderCard(player.getName()));
        return order.isYES();
    }

    private void hitDealer(final BlackjackGame blackjackGame) {
        final Dealer dealer = blackjackGame.getParticipants().getDealer();

        while (dealer.isNotBust()) {
            outputView.outputDealerDrawCard(dealer.getName());
            blackjackGame.drawCard(dealer);
            outputView.changeLine();
        }
    }

    private void showAllCardsAndScore(final BlackjackGame blackjackGame) {
        final Participants participants = blackjackGame.getParticipants();

        showDealerCardsAndScore(participants);
        showPlayersCardsAndScore(participants);
    }

    private void showDealerCardsAndScore(final Participants participants) {
        final Dealer dealer = participants.getDealer();
        outputView.outputPlayerCard(dealer.getName(), dealer.getCardNames());
        outputView.outputScore(dealer.getTotalScore());
    }

    private void showPlayersCardsAndScore(final Participants participants) {
        final List<Player> players = participants.getPlayers();

        for (final Player player : players) {
            outputView.outputPlayerCard(player.getName(), player.getCardNames());
            outputView.outputScore(player.getTotalScore());
        }
    }

    private void showAllResult(BlackjackGame blackjackGame) {
        outputView.outputResult();
        Participants participants = blackjackGame.getParticipants();
        Dealer dealer = participants.getDealer();
        ResultGame resultGame = new ResultGame(participants);

        resultGame.calculateResult();

        showDealerResult(dealer, resultGame);
        showPlayersResult(participants.getPlayers(), resultGame);
    }

    private void showDealerResult(final Dealer dealer, final ResultGame resultGame) {
        outputView.outputDealerResult(dealer.getName(),
                resultGame.getDealerCount(WinTieLose.WIN),
                resultGame.getDealerCount(WinTieLose.TIE),
                resultGame.getDealerCount(WinTieLose.LOSE));
    }

    private void showPlayersResult(final List<Player> players, final ResultGame resultGame) {
        for (Player player : players) {
            outputView.outputPlayerResult(player.getName(), resultGame.getPlayerResult(player).getValue());
        }
    }
}
