package controller;

import domain.Dealer;
import domain.Deck;
import domain.GameResult;
import domain.Player;
import domain.RandomCardsGenerator;
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
        //덱 생성
        Deck deck = new Deck(new RandomCardsGenerator());
        Dealer dealer = Dealer.init();
        List<Player> players = createPlayers();

        distributeCards(deck, dealer, players);
        askNewCard(deck, dealer, players);

        // 결과 출력
        outputView.printCardsAndResult("딜러", dealer.getCards(), dealer.getCardScore());
        for (Player player : players) {
            outputView.printCardsAndResult(player.getName(), player.getCards(), player.getCardScore());
        }

        // 승패 출력
        Map<Player, GameResult> playerResult = dealer.getGameResult(players);
        Map<GameResult, Integer> dealerResult = dealer.getResult();
        outputView.printResult(dealerResult, playerResult);
    }

    private List<Player> createPlayers() {
        List<String> names = inputView.readPlayerNames();
        return names.stream().map(Player::init).toList();
    }

    private void distributeCards(Deck deck, Dealer dealer, List<Player> players) {
        deck.giveCardTo(dealer);
        dealer.addCard(deck.pick());

        for (Player player : players) {
            deck.giveCardTo(player);
            deck.giveCardTo(player);
        }
        outputView.printParticipantCardsInfo(dealer, players, 2);
    }

    private void askNewCard(Deck deck, Dealer dealer, List<Player> players) {
        for (Player player : players) {
            while (!player.isBurst() && !inputView.readYesOrNo(player).isNo()) {
                deck.giveCardTo(player);
                outputView.printPlayerCards(player);
            }
        }
        final int count = deck.countDealerDraw(dealer);
        outputView.printDealerDrawCount(count);
    }
}
