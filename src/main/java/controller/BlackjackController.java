package controller;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import dto.FinalResultDTO;
import domain.game.GameManager;
import domain.game.GameResult;
import domain.participant.Player;
import dto.SetUpCardsDTO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    public void play() {
        Dealer dealer = new Dealer();
        List<Player> players = inputView.getPlayers();
        GameManager gameManager = new GameManager(dealer, players);
        gameManager.shuffle();
        gameManager.distributeSetUpCards();
        SetUpCardsDTO setUpCardsDTO = createSetUpCardsDTO(dealer, players);
        outputView.printSetUpCardDeck(setUpCardsDTO);

        distributeExtraCardToPlayers(gameManager, players);
        distributeExtraCardToDealer(gameManager, dealer);

        List<FinalResultDTO> finalResultDTOS = createFinalResultDTOs(dealer, players);
        outputView.printFinalCardDeck(finalResultDTOS);

        GameResult gameResult = gameManager.evaluateFinalScore();
        outputView.printGameResult(gameResult);
    }

    private void distributeExtraCardToPlayers(GameManager gameManager, List<Player> players) {
        players.forEach(player -> distributeExtraCardToPlayer(gameManager, player));
    }

    private void distributeExtraCardToPlayer(GameManager gameManager, Player player) {
        while (player.canTakeMoreCard() && inputView.getYesOrNo(player.getName())) {
            gameManager.distributeExtraCardToParticipant(player);
            outputView.printTakenMoreCards(player.getName(), player.getCards());
        }
    }

    private void distributeExtraCardToDealer(GameManager gameManager, Dealer dealer) {
        if(dealer.canTakeMoreCard()){
            gameManager.distributeExtraCardToParticipant(dealer);
            outputView.printDealerTake();
        }
    }

    public SetUpCardsDTO createSetUpCardsDTO(Dealer dealer, List<Player> players) {
        Card dealerOpenCard = dealer.getOpenCard();

        Map<String, List<Card>> cards = players.stream()
                .collect(Collectors.toMap(Player::getName, Participant::getCards
                        , (player1, player2) -> player1, LinkedHashMap::new));

        return new SetUpCardsDTO(dealerOpenCard, cards);
    }

    public List<FinalResultDTO> createFinalResultDTOs(Dealer dealer, List<Player> players) {
        List<FinalResultDTO> finalResultDTOs = new ArrayList<>();
        FinalResultDTO dealerFinalResultDTO = new FinalResultDTO("딜러", dealer.getCards(), dealer.calculateScore());
        finalResultDTOs.add(dealerFinalResultDTO);
        for (Player player : players) {
            FinalResultDTO playerFinalResultDTO = new FinalResultDTO(player.getName(), player.getCards(), player.calculateScore());
            finalResultDTOs.add(playerFinalResultDTO);
        }

        return finalResultDTOs;
    }
}
