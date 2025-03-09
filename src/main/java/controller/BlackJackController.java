package controller;

import domain.GameResult;
import domain.card.Deck;
import domain.card.cardsGenerator.RandomCardsGenerator;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
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
            Players players = createPlayers();

            deck.handoutCards(dealer, players);
            printInitialParticipantsHand(dealer, players);
            askNewCardToAllPlayers(deck, players);
            setupDealerCards(dealer, deck);

            showCardsResult(dealer, players);
            showGameResult(dealer, players);
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e);
        }
    }

    private Players createPlayers() {
        List<String> names = inputView.readPlayerNames();
        List<Player> players = names.stream().map(Player::init).toList();
        return new Players(players);
    }

    private void printInitialParticipantsHand(Dealer dealer, Players players) {
        outputView.printParticipantsHand(dealer, players);
    }

    private void askNewCardToAllPlayers(Deck deck, Players players) {
        for (Player player : players.getPlayers()) {
            askNewCardToPlayer(player, deck);
        }
    }

    private void askNewCardToPlayer(Player player, Deck deck) {
        while (!player.isBurst() && !player.isBlackJack() && inputView.readYesOrNo(player).isYes()) {
            deck.giveCardTo(player);
            outputView.printPlayerCards(player);
        }
    }

    private void setupDealerCards(Dealer dealer, Deck deck) {
        final int count = deck.countDealerDraw(dealer);
        outputView.printDealerDrawCount(count);
    }

    private void showCardsResult(Dealer dealer, Players players) {
        outputView.printDealerCardsAndResult(dealer);
        outputView.printPlayersCardAndSum(players);
    }

    private void showGameResult(Dealer dealer, Players players) {
        Map<Player, GameResult> playerResult = dealer.getGameResult(players);
        Map<GameResult, Integer> dealerResult = dealer.getResult();
        outputView.printResult(dealerResult, playerResult);
    }
}
