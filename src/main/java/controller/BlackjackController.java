package controller;

import domain.card.Card;
import domain.card.CardHand;
import domain.card.CardPack;
import domain.game.Gamblers;
import domain.game.Winning;
import domain.participant.Dealer;
import domain.participant.Gambler;
import domain.participant.Player;
import java.util.ArrayList;
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

    public void play() {
        Dealer dealer = new Dealer(new CardHand());
        List<Player> players = createPlayers();
        Gamblers gamblers = new Gamblers(dealer, players);

        CardPack cardPack = new CardPack(Card.allCards());
        cardPack.shuffle();

        gamblers.distributeSetUpCards(cardPack);
        outputView.printSetUpCardDeck(dealer, players);

        gamblers.distributeExtraCardsToPlayers(cardPack, new ViewPlayerAnswer(inputView, outputView));
        gamblers.distributeExtraCardsToDealer(cardPack, new ViewDealerAnswer(outputView));
        outputView.printFinalCardDeck(chainGamblers(dealer, players));

        Map<Winning, Long> dealerWinnings = gamblers.evaluateDealerWinnings();
        Map<Player, Winning> playerWinnings = gamblers.evaluatePlayerWinnings();
        outputView.printGameResult(dealerWinnings, players, playerWinnings);
    }

    private List<Player> createPlayers() {
        return inputView.getPlayerNames()
            .stream()
            .map(name -> new Player(name, new CardHand()))
            .toList();
    }

    private List<Gambler> chainGamblers(Dealer dealer, List<Player> players) {
        List<Gambler> gamblers = new ArrayList<>(players);
        gamblers.addFirst(dealer);
        return gamblers;
    }
}
