package controller;

import domain.card.CardHand;
import domain.card.CardPack;
import domain.game.Gamblers;
import domain.game.GamblingMoney;
import domain.participant.Dealer;
import domain.participant.Gambler;
import domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

        processSetUpCards(gamblers, cardPack);
        processDistributeExtraCards(gamblers, cardPack);
        processFinalResult(gamblers);
    }

    private List<Player> createPlayers() {
        return inputView.getPlayerNames().stream()
            .map(this::createPlayer)
            .toList();
    }

    private Player createPlayer(final String playerName) {
        GamblingMoney bettingMoney = inputView.getBettingMoney(playerName);
        return new Player(playerName, new CardHand(), bettingMoney);
    }

    private void processSetUpCards(Gamblers gamblers, CardPack cardPack) {
        gamblers.distributeSetUpCards(cardPack);

        Dealer dealer = gamblers.getDealer();
        outputView.printSetUpCardDeck(dealer.getOpenCard(), convertToDtos(gamblers.getPlayers()));
    }

    private void processDistributeExtraCards(Gamblers gamblers, CardPack cardPack) {
        gamblers.distributeExtraCardsToPlayers(cardPack, new ViewPlayerAnswer(inputView, outputView));
        gamblers.distributeExtraCardsToDealer(cardPack, new ViewDealerAnswer(outputView));

        outputView.printFinalCardDeck(convertToDtos(gamblers.getPlayers()));
    }

    private void processFinalResult(Gamblers gamblers) {
        Map<GamblerDto, GamblingMoney> profits = createProfitsDto(gamblers);
        outputView.printGamblerProfits(profits);
    }

    private Map<GamblerDto, GamblingMoney> createProfitsDto(final Gamblers gamblers) {
        Map<Player, GamblingMoney> playerProfits = gamblers.evaluatePlayerProfits();

        LinkedHashMap<GamblerDto, GamblingMoney> profits = new LinkedHashMap<>();
        for (var playerProfit : playerProfits.entrySet()) {
            Player player = playerProfit.getKey();
            GamblingMoney profit = playerProfit.getValue();
            profits.put(GamblerDto.from(player), profit);
        }

        Dealer dealer = gamblers.getDealer();
        profits.putFirst(GamblerDto.from(dealer), gamblers.evaluateDealerProfit());
        return profits;
    }

    private List<GamblerDto> convertToDtos(List<? extends Gambler> gamblers) {
        return gamblers.stream()
            .map(GamblerDto::from)
            .toList();
    }
}
