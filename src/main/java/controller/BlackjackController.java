package controller;

import domain.card.CardHand;
import domain.card.CardPack;
import domain.game.GamblingMoney;
import domain.game.Gamblers;
import domain.game.Winning;
import domain.participant.Dealer;
import domain.participant.Gambler;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play(CardPack cardPack) {
        Dealer dealer = new Dealer(new CardHand());
        Map<Player, GamblingMoney> players = createPlayers();
        Gamblers gamblers = new Gamblers(dealer, players);

        gamblers.distributeSetUpCards(cardPack);
        outputView.printSetUpCardDeck(dealer, toKeyList(players));

        gamblers.distributeExtraCardsToPlayers(cardPack, new ViewPlayerAnswer(inputView, outputView));
        gamblers.distributeExtraCardsToDealer(cardPack, new ViewDealerAnswer(outputView));
        outputView.printFinalCardDeck(chainGamblers(dealer, toKeyList(players)));

        Map<Winning, Long> dealerWinnings = gamblers.evaluateDealerWinnings();
        Map<Player, Winning> playerWinnings = gamblers.evaluatePlayerWinnings();
        outputView.printGameResult(dealerWinnings, playerWinningsInOrder(toKeyList(players), playerWinnings));
    }

    private Map<Player, GamblingMoney> createPlayers() {
        List<Player> players = inputView.getPlayerNames()
            .stream()
            .map(name -> new Player(name, new CardHand()))
            .toList();

        return players.stream().collect(Collectors.toMap(
                Function.identity(),
                p -> inputView.getBettingMoney(p.getName()),
                (exist, replace) -> exist,
                LinkedHashMap::new)
            );
    }

    private List<Gambler> chainGamblers(Dealer dealer, List<Player> players) {
        List<Gambler> gamblers = new ArrayList<>(players);
        gamblers.addFirst(dealer);
        return gamblers;
    }

    private Map<Player, Winning> playerWinningsInOrder(List<Player> players,
        Map<Player, Winning> playerWinnings) {
        return players.stream()
            .collect(Collectors.toMap(
                Function.identity(),
                playerWinnings::get,
                (exist, replace) -> exist,
                LinkedHashMap::new
            ));
    }

    private <T> List<T> toKeyList(Map<T, ?> map) {
        return new ArrayList<>(map.keySet());
    }
}
