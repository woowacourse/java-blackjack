package blackjack.domain;

import blackjack.dto.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Blackjack {

    private static final int CARD_COUNT_OF_FIRST_DISTRIBUTE = 2;

    private final List<Player> players;
    private final Dealer dealer;
    private final Deck deck;

    public Blackjack(String[] names) {
        players = Arrays.stream(names)
                .map(Player::new)
                .collect(Collectors.toList());
        dealer = new Dealer();
        deck = new Deck();
    }

    public void firstDistribute() {
        for (int i = 0; i < CARD_COUNT_OF_FIRST_DISTRIBUTE; i++) {
            players.forEach(player -> player.addCard(deck.draw()));
            dealer.addCard(deck.draw());
        }
    }

    public void addCardForDealerIfNeed() {
        while (dealer.needMoreCard()) {
            dealer.addCard(deck.draw());
        }
    }

    public boolean didDealerAdded() {
        return dealer.getCards().size() > CARD_COUNT_OF_FIRST_DISTRIBUTE;
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public List<CurrentCardsDTO> generateAllCurrentCardsDTO() {
        List<CurrentCardsDTO> allCurrentCardsDTOs = new ArrayList<>();

        allCurrentCardsDTOs.add(new CurrentCardsDTO(dealer));

        allCurrentCardsDTOs.addAll(players.stream()
                .map(CurrentCardsDTO::new)
                .collect(Collectors.toList()));

        return allCurrentCardsDTOs;
    }

    public CurrentCardsDTO generateCurrentCardsDTOByName(String name) {
        return new CurrentCardsDTO(findPlayerByName(name));
    }

    public List<TotalScoreDTO> generateAllResultDTO() {
        List<TotalScoreDTO> addResultDTOs = new ArrayList<>();
        addResultDTOs.add(new TotalScoreDTO(dealer));
        addResultDTOs.addAll(players.stream()
                .map(TotalScoreDTO::new)
                .collect(Collectors.toList()));
        return addResultDTOs;
    }

    public String nameOfNotBustPlayer() {
        return players.stream()
                .filter(p -> !p.isBust())
                .findFirst()
                .map(Player::getName)
                .orElse(null);
    }

    public void hitByName(String name) {
        findPlayerByName(name).addCard(deck.draw());
    }

    public boolean isNotBust(String name) {
        return !findPlayerByName(name).isBust();
    }

    public TotalResultDTO calculateTotalResult() {
        List<PlayerResultDTO> totalPlayerResult = calculateTotalPlayerResult();
        DealerResultDTO dealerResult = calculateDealerResult(totalPlayerResult);
        return new TotalResultDTO(totalPlayerResult, dealerResult);
    }

    private Player findPlayerByName(String name) {
        return players.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .get();
    }

    private List<PlayerResultDTO> calculateTotalPlayerResult() {
        List<PlayerResultDTO> totalPlayerResult = new ArrayList<>();
        for (Player player : players) {
            totalPlayerResult.add(new PlayerResultDTO(player.getName(), player.isWin(dealer.getScore())));
        }

        return totalPlayerResult;
    }

    private DealerResultDTO calculateDealerResult(List<PlayerResultDTO> totalPlayerResult) {
        int loseCount = (int) totalPlayerResult.stream().filter(PlayerResultDTO::isWin).count();
        int winCount = totalPlayerResult.size() - loseCount;

        return new DealerResultDTO(dealer.getName(), winCount, loseCount);
    }
}
