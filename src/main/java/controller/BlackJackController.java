package controller;

import domain.GameResult;
import domain.card.Deck;
import domain.cardsGenerator.RandomCardsGenerator;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            Deck deck = new Deck(new RandomCardsGenerator());
            Dealer dealer = Dealer.init();
            List<Player> players = createPlayers();

            deck.handoutCards(dealer, players);
            askNewCardToAllPlayers(deck, players);
            setupDealerCards(dealer, deck);

            showCardsResult(dealer, players);
            showGameResult(dealer, players);
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e);
        }
    }

    private List<Player> createPlayers() {
        List<String> names = inputView.readPlayerNames();
        return names.stream().map(Player::init).toList();
    }

    private void askNewCardToAllPlayers(Deck deck, List<Player> players) {
        for (Player player : players) {
            askNewCardToPlayer(player, deck);
        }
    }

    private void askNewCardToPlayer(Player player, Deck deck) {
        while (!player.isBurst() && inputView.readYesOrNo(player).isYes()) {
            deck.giveCardTo(player);
            outputView.printPlayerCards(player);
        }
    }

    private void setupDealerCards(Dealer dealer, Deck deck) {
        final int count = deck.countDealerDraw(dealer);
        outputView.printDealerDrawCount(count);
    }

    private void showCardsResult(Dealer dealer, List<Player> players) {
        outputView.printCardsAndResult("딜러", dealer.getCards(), dealer.getCardScore());
        for (Player player : players) {
            outputView.printCardsAndResult(player.getName(), player.getCards(), player.getCardScore());
        }
    }

    private void showGameResult(Dealer dealer, List<Player> players) {
        Map<Player, GameResult> playerResult = dealer.getGameResult(players);
        Map<GameResult, Integer> dealerResult = dealer.getResult();
        outputView.printResult(dealerResult, playerResult);
    }
}
