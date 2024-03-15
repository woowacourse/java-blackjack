package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.game.PlayersResult;
import blackjack.domain.participant.Dealer2;
import blackjack.domain.participant.Player2;
import blackjack.domain.participant.Players2;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class GameManager {

    private final InputView inputView;
    private final OutputView outputView;

    public GameManager(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Players2 players = Players2.create(inputView.readNames());
        Dealer2 dealer = new Dealer2();

        CardDeck cardDeck = CardDeck.createShuffledFullCardDeck();

        deal(players, dealer, cardDeck);
        draw(players, dealer, cardDeck);
        judge(players, dealer);
    }

    private void deal(Players2 players, Dealer2 dealer, CardDeck cardDeck) {
        dealer.deal(cardDeck);
        players.deal(cardDeck);
        outputView.printDealToAll(dealer, players);
    }

    private void draw(Players2 players, Dealer2 dealer, CardDeck cardDeck) {
        drawToPlayers(players, cardDeck);
        drawToDealer(dealer, cardDeck);
        outputView.printParticipantsHandScore(dealer, players);
    }

    private void drawToPlayers(Players2 players, CardDeck cardDeck) {
        players.draw(player -> drawToPlayer(player, cardDeck));
        outputView.printLineSeparator();
    }

    private void drawToPlayer(Player2 player, CardDeck cardDeck) {
        while (canPlayerHit(player)) {
            player.draw(cardDeck);
            outputView.printDrawToPlayer(player);
        }
    }

    private boolean canPlayerHit(Player2 player) {
        return player.canHit() && inputView.readDrawDecision(player.getName()).isHit();
    }

    private void drawToDealer(Dealer2 dealer, CardDeck cardDeck) {
        while (dealer.canHit()) {
            dealer.draw(cardDeck);
            outputView.printDrawToDealer();
        }
    }

    private void judge(Players2 players, Dealer2 dealer) {
        PlayersResult playersResult = players.judge(dealer);
        outputView.printResult(playersResult);
    }
}
