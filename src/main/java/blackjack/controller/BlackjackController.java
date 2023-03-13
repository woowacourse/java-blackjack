package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckMaker;
import blackjack.domain.cardPicker.RandomCardPicker;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.ResultGame;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.dto.CardsDto;
import blackjack.dto.ParticipantsDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Participants participants = makeParticipants();
        final Deck deck = makeDeck();
        final BlackjackGame blackjackGame = new BlackjackGame(deck);

        startGame(blackjackGame, participants);
        hitParticipants(blackjackGame, participants);
        showResult(participants);
    }


    private Participants makeParticipants() {
        Map<String, Integer> players = new LinkedHashMap<>();
        for (String name : inputView.inputPlayers()) {
            players.put(name, inputView.inputBetting(name));
        }
        final Dealer dealer = new Dealer();
        return new Participants(dealer, players);
    }

    private Deck makeDeck() {
        final DeckMaker deckMaker = new DeckMaker();
        return new Deck(deckMaker.makeDeck(), new RandomCardPicker());
    }

    private void startGame(final BlackjackGame blackjackGame, Participants participants) {
        final List<String> playerNames = participants.getPlayerNames();
        outputView.outputSplitMessage(playerNames);
        blackjackGame.giveTwoCardEveryone(participants);
        participants.getDealer().reverseAllExceptOne();
        outputView.outputParticipantCards(new ParticipantsDto(participants));
    }


    private void hitParticipants(final BlackjackGame blackjackGame, Participants participants) {
        hitPlayers(blackjackGame, participants.getPlayers());
        hitDealer(blackjackGame, participants.getDealer());
    }

    private void hitPlayers(final BlackjackGame blackjackGame, List<Player> players) {
        for (final Player player : players) {
            hitEachPlayer(blackjackGame, player);
        }
    }

    private void hitEachPlayer(final BlackjackGame blackjackGame, Player player) {
        while (blackjackGame.isPlayerCanPlay(player, inputView.inputOrderCard(player.getName()))) {
            outputView.outputPlayerCard(player.getName(), new CardsDto(player.getCards(), player.getTotalScore()));
        }
        outputView.outputPlayerCard(player.getName(), new CardsDto(player.getCards(), player.getTotalScore()));
    }

    private void hitDealer(BlackjackGame blackjackGame, Dealer dealer) {
        while (blackjackGame.playDealer(dealer)) {
            outputView.outputDealerDrawCard();
        }
    }

    private void showResult(Participants participants) {
        ResultGame resultGame = new ResultGame(participants);
        resultGame.calculateResult();
        participants.getDealer().openAllCard();
        outputView.outputCardsAndScore(new ParticipantsDto(participants));
        outputView.outputRevenue(new ParticipantsDto(participants));
    }
}
