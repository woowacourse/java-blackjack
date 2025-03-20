package controller;

import domain.card.Card;
import domain.game.EarningResult;
import domain.game.PlayerBets;
import domain.participant.Dealer;
import domain.participant.Participant;
import dto.FinalResultDTO;
import domain.game.GameManager;
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
        PlayerBets playerBets = placeBets(players);
        GameManager gameManager = new GameManager(dealer, players);

        dealSetUpCards(gameManager);
        displaySetUpCards(dealer, players);

        playTurns(gameManager);
        displayFinalResult(dealer, players);
        evaluateAndDisplayEarnings(gameManager, playerBets);
    }

    private void displaySetUpCards(Dealer dealer, List<Player> players) {
        SetUpCardsDTO setUpCardsDTO = createSetUpCardsDTO(dealer, players);
        outputView.printSetUpCardDeck(setUpCardsDTO);
    }

    private PlayerBets placeBets(List<Player> players) {
        return new PlayerBets(players.stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> inputView.getBetMoney(player.getName()),
                        (player1, player2) -> player1,
                        LinkedHashMap::new
                )));
    }

    private void playTurns(GameManager gameManager) {
        distributeExtraCardToPlayers(gameManager);
        distributeExtraCardToDealer(gameManager);
    }

    private void distributeExtraCardToPlayers(GameManager gameManager) {
        gameManager.getPlayerNames().forEach(name -> distributeExtraCardToPlayer(gameManager, name));
    }

    private void distributeExtraCardToPlayer(GameManager gameManager, String name) {
        while (gameManager.canPlayerTakeMoreCard(name) && inputView.getYesOrNo(name)) {
            gameManager.distributeExtraCardToPlayer(name);
            outputView.printTakenMoreCards(name, gameManager.getPlayerCards(name));
        }
    }

    private void distributeExtraCardToDealer(GameManager gameManager) {
        if (gameManager.canDealerTakeMoreCard()) {
            gameManager.distributeExtraCardToDealer();
            outputView.printDealerTake();
        }
    }

    private void displayFinalResult(Dealer dealer, List<Player> players) {
        List<FinalResultDTO> finalResultDTOS = createFinalResultDTOs(dealer, players);
        outputView.printFinalCardDeck(finalResultDTOS);
    }

    private void evaluateAndDisplayEarnings(GameManager gameManager, PlayerBets playerBets) {
        EarningResult earningResult = playerBets.evaluateEarning(gameManager.getPlayers(), gameManager.getDealer());
        outputView.printFinalEarning(earningResult);
    }

    private void dealSetUpCards(GameManager gameManager) {
        gameManager.distributeSetUpCards();
    }

    public SetUpCardsDTO createSetUpCardsDTO(Dealer dealer, List<Player> players) {
        Card dealerOpenCard = dealer.getOpenCard();

        Map<String, List<Card>> cards = players.stream()
                .collect(Collectors.toMap(Player::getName, player -> player.getHands().getCards()
                        , (player1, player2) -> player1, LinkedHashMap::new));

        return new SetUpCardsDTO(dealerOpenCard, cards);
    }

    public List<FinalResultDTO> createFinalResultDTOs(Dealer dealer, List<Player> players) {
        List<FinalResultDTO> finalResultDTOs = new ArrayList<>();
        FinalResultDTO dealerFinalResultDTO = new FinalResultDTO("딜러", dealer.getHands().getCards(), dealer.calculateScore());
        finalResultDTOs.add(dealerFinalResultDTO);
        for (Player player : players) {
            FinalResultDTO playerFinalResultDTO = new FinalResultDTO(player.getName(), player.getHands().getCards(), player.calculateScore());
            finalResultDTOs.add(playerFinalResultDTO);
        }

        return finalResultDTOs;
    }
}
