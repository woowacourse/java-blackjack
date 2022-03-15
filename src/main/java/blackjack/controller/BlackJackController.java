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
import java.util.stream.Collectors;

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

        playAllPlayers(players);
        playDealer(dealer);

        showGameResult(players, dealer);
    }

    private Players initializePlayers(List<Name> names) {
        return new Players(names.stream()
                .map(name -> new Player(name, drawInitialCards()))
                .collect(Collectors.toUnmodifiableList()));
    }

    private Cards drawInitialCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INITIAL_DRAW_CARD_COUNT; i++) {
            cards.add(cardDistributor.distribute());
        }
        return new Cards(cards);
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
            OutputView.printCards(players.getPresentPlayer());
        }
    }

    private void decideContinueToPlay(Players players) {
        boolean isDrawable = wantDraw(players.getPresentPlayer());
        if (isDrawable) {
            players.drawCardPresentPlayer(cardDistributor.distribute());
            return;
        }
        players.makePresentPlayerStay();
    }

    private boolean wantDraw(Player player) {
        try {
            return InputView.inputWantDraw(player.getName());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return wantDraw(player);
        }
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
