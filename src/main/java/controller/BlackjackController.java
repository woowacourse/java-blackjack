package controller;

import domain.Card;
import domain.Dealer;
import domain.FinalResultDTO;
import domain.GameManager;
import domain.Player;
import domain.SetUpCardsDTO;
import domain.TakeMoreCardSelector;
import java.util.ArrayList;
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

        gameManager.distributeExtraCards(new TakeMoreCardViewSelector());

        List<FinalResultDTO> finalResultDTOS = createFinalResultDTOs(dealer, players);
        outputView.printFinalCardDeck(finalResultDTOS);
    }

    public SetUpCardsDTO createSetUpCardsDTO(Dealer dealer, List<Player> players) {
        Card dealerOpenCard = dealer.getOpenCard();

        Map<String, List<Card>> cards = new LinkedHashMap<>();
        players.forEach(p -> cards.put(p.getName(), p.getCards()));

        return new SetUpCardsDTO(dealerOpenCard, cards);
    }

    public List<FinalResultDTO> createFinalResultDTOs(Dealer dealer, List<Player> players) {
        List<FinalResultDTO> finalResultDTOs = new ArrayList<>();
        FinalResultDTO finalResultDTO = new FinalResultDTO("딜러", dealer.getCards(), dealer.calculateScore());
        finalResultDTOs.add(finalResultDTO);
        for(Player player : players) {
            FinalResultDTO finalResultDTO1 = new FinalResultDTO(player.getName(), player.getCards(), player.calculateScore());
            finalResultDTOs.add(finalResultDTO1);
        }

        return finalResultDTOs;
    }

    private class TakeMoreCardViewSelector implements TakeMoreCardSelector {

        @Override
        public boolean isYes(String name) {
            return inputView.getYesOrNo(name);
        }

        @Override
        public void takenResult(String name, List<Card> cards) {
            outputView.printTakenMoreCards(name, cards);
        }

        @Override
        public void dealerTakenResult() {
            outputView.printDealerTake();
        }
    }
}
