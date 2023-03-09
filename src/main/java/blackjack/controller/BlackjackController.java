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
import blackjack.view.dto.CardsDto;
import blackjack.view.dto.ParticipantsDto;
import blackjack.view.dto.ResultDto;

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

        startGame(blackjackGame,participants);
        participants.getDealer().reverseAllExceptOne();
        outputView.outputParticipantCards(new ParticipantsDto(participants));
        hitParticipants(blackjackGame,participants);
        ResultGame resultGame = new ResultGame(participants);
        resultGame.calculateResult();
        participants.getDealer().openAllCard();
        outputView.outputCardsAndScore(new ParticipantsDto(participants));
        outputView.outputFinalResult(new ResultDto(resultGame.getPlayersResult()));
    }


    private Participants makeParticipants() {
        final Dealer dealer = new Dealer();
        return new Participants(dealer, inputView.inputPlayers());
    }

    private Deck makeDeck() {
        final DeckMaker deckMaker = new DeckMaker();
        return new Deck(deckMaker.makeDeck(), new RandomCardPicker());
    }

    private void startGame(final BlackjackGame blackjackGame, Participants participants) {
        final List<String> playerNames = participants.getPlayerNames();

        outputView.outputSplitMessage(playerNames);
        blackjackGame.giveTwoCardEveryone(participants);
    }


    private void hitParticipants(final BlackjackGame blackjackGame, Participants participants) {
        hitPlayers(blackjackGame,participants.getPlayers());
        hitDealer(blackjackGame,participants.getDealer());
    }

    private void hitPlayers(final BlackjackGame blackjackGame, List<Player> players){
        for (final Player player : players) {
            hitEachPlayer(blackjackGame, player);
        }
    }

    private void hitEachPlayer(final BlackjackGame blackjackGame, Player player){
        Order order = Order.from(inputView.inputOrderCard(player.getName()));
        while(blackjackGame.isPlayerCanPlay(player,order)){
            outputView.outputPlayerCard(player.getName(), new CardsDto(player.getCards(), player.getTotalScore()));
            order = Order.from(inputView.inputOrderCard(player.getName()));
        }
    }

    private void hitDealer(BlackjackGame blackjackGame, Dealer dealer){
        while(blackjackGame.playDealer(dealer)){
            outputView.outputDealerDrawCard(dealer.getName());
        }
    }
}
