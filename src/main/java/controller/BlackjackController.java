package controller;

import domain.card.CardHand;
import domain.card.CardPack;
import domain.game.Gamblers;
import domain.game.GamblingMoney;
import domain.participant.Dealer;
import domain.participant.Gambler;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import view.GamblerDto;
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
        List<Player> players = createPlayers();
        Gamblers gamblers = new Gamblers(dealer, players);

        gamblers.distributeSetUpCards(cardPack);
        List<GamblerDto> gamblerDtos = players.stream().map(GamblerDto::from).toList();
        outputView.printSetUpCardDeck(dealer.getOpenCard(), gamblerDtos);

        gamblers.distributeExtraCardsToPlayers(cardPack, new ViewPlayerAnswer(inputView, outputView));
        gamblers.distributeExtraCardsToDealer(cardPack, new ViewDealerAnswer(outputView));
        outputView.printFinalCardDeck(chainGamblers(dealer, players));

        GamblingMoney dealerProfit = gamblers.evaluateDealerProfit();
        Map<GamblerDto, GamblingMoney> gamblerDtoProfits = gamblers.evaluatePlayerProfits()
            .entrySet()
            .stream()
            .collect(Collectors.toMap(e -> GamblerDto.from(e.getKey()), Entry::getValue));
        gamblerDtoProfits.put(GamblerDto.from(dealer), dealerProfit);
        outputView.printGamblerProfits(gamblerDtoProfits);
    }

    private List<Player> createPlayers() {
        List<String> playerNames = inputView.getPlayerNames();

        return playerNames.stream()
            .map(playerName -> {
                GamblingMoney bettingMoney = inputView.getBettingMoney(playerName);
                return new Player(playerName, new CardHand(), bettingMoney);
            })
            .toList();
    }

    private List<GamblerDto> chainGamblers(Dealer dealer, List<Player> players) {
        List<Gambler> gamblers = new ArrayList<>(players);
        gamblers.addFirst(dealer);
        return gamblers.stream()
            .map(GamblerDto::from)
            .toList();
    }
}
