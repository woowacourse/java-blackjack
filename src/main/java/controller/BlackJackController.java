package controller;

import controller.dto.CardScoreDto;
import domain.Dealer;
import domain.Deck;
import domain.GameResult;
import domain.Money;
import domain.Player;
import domain.TrumpCard;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
        Deck deck = createDeck();
        Dealer dealer = createDealer(deck);
        List<Player> players = createPlayers(deck);

        displayFirstTwoCards(players, dealer);

        executePlayersHit(players);

        executeDealerHit(dealer);

        displayCardResult(players, dealer);

        executeBetting(players, dealer);

        displayGameResult(players, dealer);
    }

    private static Deck createDeck() {
        return Deck.create();
    }

    private static Dealer createDealer(Deck deck) {
        return Dealer.initiallizeDealer(deck);
    }

    private void displayFirstTwoCards(List<Player> players, Dealer dealer) {
        Map<String, List<TrumpCard>> playerCards = createPlayerCards(players);
        TrumpCard dealerFirstCard = dealer.retrieveFirstCard();
        outputView.printInitialCards(playerCards, dealerFirstCard);
    }

    private Map<String, List<TrumpCard>> createPlayerCards(List<Player> players) {
        return players.stream()
                .collect(Collectors.toMap(Player::getName, player -> player.getHand().getCards()));
    }

    private List<Player> createPlayers(Deck deck) {
        List<String> playerNames = inputView.readPlayerNames();
        List<Player> players = new ArrayList<>();
        Map<String, Integer> money = inputView.readPlayerBetting(playerNames);
        return money.entrySet()
                .stream()
                .map(entry -> Player.of(entry.getKey(), deck, Money.makeMoneyInt(entry.getValue())))
                .collect(Collectors.toList());
    }

    private void executePlayersHit(List<Player> players) {
        for (Player player : players) {
            executePlayerHit(player);
        }
    }

    private void executePlayerHit(Player player) {
        while (!player.isBust() &&
                inputView.readProcessHit(player.getName())) {
            player.addCard();
            outputView.printPlayerCards(player.getName(), player.getCards());
        }
    }

    private void executeDealerHit(Dealer dealer) {
        int dealerHitCount = dealer.processDealerHit();
        outputView.printDealerHitInfo(dealerHitCount);
    }

    private void displayCardResult(List<Player> players, Dealer dealer) {
        Map<String, CardScoreDto> playerCardScoreDto = convertPlayerCardScoreDto(players);
        CardScoreDto dealerCardScoreDto = convertDealerCardScoreDto(dealer);
        outputView.printCardsResult(playerCardScoreDto, dealerCardScoreDto);
    }

    private Map<String, CardScoreDto> convertPlayerCardScoreDto(List<Player> players) {
        return players.stream()
                .collect(Collectors.toMap(Player::getName, player -> {
                    List<TrumpCard> playerCards = player.getCards();
                    int totalScore = player.getTotalScore();

                    return new CardScoreDto(
                            playerCards,
                            totalScore);
                }));
    }

    private CardScoreDto convertDealerCardScoreDto(Dealer dealer) {
        List<TrumpCard> cards = dealer.getCards();
        int totalScore = dealer.getTotalScore();

        return new CardScoreDto(cards, totalScore);
    }

    private void executeBetting(List<Player> players, Dealer dealer) {
        players.forEach(player -> {
            GameResult gameResult = GameResult.from(player, dealer);
            player.processBetting(gameResult.getRate());
            dealer.processBetting(-player.getEarnMoney());
        });
    }

    private void displayGameResult(List<Player> players, Dealer dealer) {
        outputView.printGameResult(dealer.getTotalMoney(), players);
    }
}
