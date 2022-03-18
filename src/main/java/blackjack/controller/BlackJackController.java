package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDistributor;
import blackjack.domain.card.Cards;
import blackjack.domain.game.GameResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackController {

    private static final int INITIAL_DRAW_CARD_COUNT = 2;

    private final CardDistributor cardDistributor;

    public BlackJackController(CardDistributor cardDistributor) {
        this.cardDistributor = cardDistributor;
    }

    public void run() {
        Dealer dealer = new Dealer(new Name(Dealer.NAME), drawInitialCards());
        Players players = initializePlayers(InputView.inputPlayerNames());
        OutputView.printInitialCards(players, dealer);

        if (!isGameEnd(players, dealer)) {
            playAllPlayers(players);
            playDealer(dealer);
        }

        showGameResult(players, dealer);
    }

    private Players initializePlayers(List<Name> names) {
        List<Player> players = new ArrayList<>();
        for (int count = 0; count < names.size(); count++) {
            Name playerName = names.get(count);
            players.add(new Player(playerName, drawInitialCards(), InputView.inputPlayerMoney(playerName.getValue())));
        }
        return new Players(players);
    }
    
    private Cards drawInitialCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INITIAL_DRAW_CARD_COUNT; i++) {
            cards.add(cardDistributor.distribute());
        }
        return new Cards(cards);
    }

    private boolean isGameEnd(Players players, Dealer dealer) {
        return dealer.isBlackJack() || players.isAllBlackJack();
    }

    private void playAllPlayers(Players players) {
        while (!players.isAllFinished()) {
            playPresentPlayer(players);
            players.passToNextPlayer();
        }
    }

    private void playPresentPlayer(Players players) {
        while (!players.isPresentPlayerFinished()) {
            decideContinueToPlay(players);
            OutputView.printCards(players.findPresentPlayer());
        }
    }

    private void decideContinueToPlay(Players players) {
        boolean isDrawable = InputView.inputWantDraw(players.findPresentPlayer());
        if (isDrawable) {
            players.drawCardPresentPlayer(cardDistributor.distribute());
            return;
        }
        players.makePresentPlayerStay();
    }

    private void playDealer(Dealer dealer) {
        while (!dealer.isFinished()) {
            OutputView.printDealerDrawInfo();
            dealer.drawCard(cardDistributor.distribute());
        }
    }

    private void showGameResult(Players players, Dealer dealer) {
        GameResult gameResult = new GameResult(players, dealer);
        OutputView.printCardsResult(dealer, players);
        OutputView.printGameResult(gameResult);
    }
}
