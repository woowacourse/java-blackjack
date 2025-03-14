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
import java.util.Map.Entry;
import java.util.function.Function;
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
        Map<Player, GamblingMoney> players = createPlayers();
        Gamblers gamblers = new Gamblers(dealer, players);

        gamblers.distributeSetUpCards(cardPack);
        List<GamblerDto> gamblerDtos = toKeyList(players).stream().map(GamblerDto::from).toList();
        outputView.printSetUpCardDeck(dealer.getOpenCard(), gamblerDtos);

        gamblers.distributeExtraCardsToPlayers(cardPack, new ViewPlayerAnswer(inputView, outputView));
        gamblers.distributeExtraCardsToDealer(cardPack, new ViewDealerAnswer(outputView));
        outputView.printFinalCardDeck(chainGamblers(dealer, toKeyList(players)));

        Map<GamblerDto, Integer> gamblerDtoProfits = gamblers.evaluateProfits().entrySet()
            .stream()
            .collect(Collectors.toMap(e -> GamblerDto.from(e.getKey()), Entry::getValue));
        outputView.printGamblerProfits(gamblerDtoProfits);
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

    private List<GamblerDto> chainGamblers(Dealer dealer, List<Player> players) {
        List<Gambler> gamblers = new ArrayList<>(players);
        gamblers.addFirst(dealer);
        return gamblers.stream()
            .map(GamblerDto::from)
            .toList();
    }

    private <T> List<T> toKeyList(Map<T, ?> map) {
        return new ArrayList<>(map.keySet());
    }
}
