package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.dealer.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.PlayerNames;
import blackjack.domain.player.Players;
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
        PlayerNames playerNames = PlayerNames.create(inputView.readNames());
        Players players = Players.create(playerNames, inputView::readBetAmount);
        Dealer dealer = new Dealer();
        CardDeck cardDeck = CardDeck.createShuffledFullCardDeck();

        deal(players, dealer, cardDeck);
        draw(players, dealer, cardDeck);

        players.calculateProfit(dealer);
        outputView.printAllProfit(players);
    }

    private void deal(Players players, Dealer dealer, CardDeck cardDeck) {
        dealer.deal(cardDeck);
        players.deal(cardDeck);
        outputView.printDealToAll(dealer, players.players());
    }

    private void draw(Players players, Dealer dealer, CardDeck cardDeck) {
        drawToPlayers(players, cardDeck);
        drawToDealer(dealer, cardDeck);
        outputView.printAllHandScore(dealer, players.players());
    }

    private void drawToPlayers(Players players, CardDeck cardDeck) {
        players.draw(player -> drawToPlayer(player, cardDeck));
        outputView.printLineSeparator();
    }

    private void drawToPlayer(Player player, CardDeck cardDeck) {
        while (canPlayerHit(player)) {
            player.draw(cardDeck);
            outputView.printDrawToPlayer(player);
        }
    }

    private boolean canPlayerHit(Player player) {
        return player.canHit() && inputView.readDrawDecision(player.getName()).isHit();
    }

    private void drawToDealer(Dealer dealer, CardDeck cardDeck) {
        while (dealer.canHit()) {
            dealer.draw(cardDeck);
            outputView.printDrawToDealer();
        }
    }
}
