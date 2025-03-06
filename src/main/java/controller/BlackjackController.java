package controller;

import domain.Card;
import domain.Dealer;
import domain.GameManager;
import domain.Player;
import domain.SetUpCardsDTO;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController(new InputView(),
            new OutputView());
        blackjackController.play();
    }

    public void play() {
        Dealer dealer = new Dealer();
        List<Player> players = inputView.getPlayers();
        GameManager gameManager = new GameManager(dealer, players);
        gameManager.shuffle();
        gameManager.distributeSetUpCards();
        SetUpCardsDTO setUpCardsDTO = createSetUpCardsDTO(dealer, players);
        outputView.printSetUpCardDeck(setUpCardsDTO);
    }

    public SetUpCardsDTO createSetUpCardsDTO(Dealer dealer, List<Player> players) {
        Card dealerOpenCard = dealer.getOpenCard();

        Map<String, List<Card>> cards = new LinkedHashMap<>();
        players.forEach(p -> cards.put(p.getName(), p.getCards()));

        return new SetUpCardsDTO(dealerOpenCard, cards);
    }
}
