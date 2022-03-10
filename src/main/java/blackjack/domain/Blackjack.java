package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.dto.*;

import java.util.*;
import java.util.stream.Collectors;

public class Blackjack {

    private static final int CARD_COUNT_OF_FIRST_DISTRIBUTE = 2;
    private static final int MAX_NUMBER_OF_PLAYER = 7;
    private static final String TOO_MANY_PLAYERS = "최대 " + MAX_NUMBER_OF_PLAYER + "명까지 플레이 가능합니다.";
    private static final String NAME_DUPLICATED = "중복되는 이름은 허용되지 않습니다.";

    private final List<Player> players;
    private final Dealer dealer;
    private final Deck deck;
    private final Queue<Player> playersWhoCanHit;

    public Blackjack(String[] names) {
        validateNames(names);
        players = Arrays.stream(names)
                .map(Player::new)
                .collect(Collectors.toList());
        dealer = new Dealer();
        deck = new Deck();
        playersWhoCanHit = new LinkedList<>();
    }

    public void firstDistribute() {
        for (int i = 0; i < CARD_COUNT_OF_FIRST_DISTRIBUTE; i++) {
            players.forEach(player -> player.addCard(deck.draw()));
            dealer.addCard(deck.draw());
        }
        playersWhoCanHit.addAll(players);
    }

    public Player getPlayerWhoCanHit() {
        if (playersWhoCanHit.isEmpty()) {
            return null;
        }
        return playersWhoCanHit.poll();
    }

    public void hit(Player player) {
        player.addCard(deck.draw());
    }

    public void addCardForDealerIfNeed() {
        while (dealer.needMoreCard()) {
            dealer.addCard(deck.draw());
        }
    }

    public boolean didDealerAdded() {
        return dealer.getCards().size() > CARD_COUNT_OF_FIRST_DISTRIBUTE;
    }

    public List<CurrentCardsDTO> generateAllCurrentCardsDTO() {
        List<CurrentCardsDTO> allCurrentCardsDTOs = new ArrayList<>();

        allCurrentCardsDTOs.add(new CurrentCardsDTO(dealer));

        allCurrentCardsDTOs.addAll(players.stream()
                .map(CurrentCardsDTO::new)
                .collect(Collectors.toList()));

        return allCurrentCardsDTOs;
    }

    public List<TotalScoreDTO> generateAllResultDTO() {
        List<TotalScoreDTO> addResultDTOs = new ArrayList<>();
        addResultDTOs.add(new TotalScoreDTO(dealer));
        addResultDTOs.addAll(players.stream()
                .map(TotalScoreDTO::new)
                .collect(Collectors.toList()));
        return addResultDTOs;
    }

    public TotalResultDTO calculateTotalResult() {
        dealer.endTurn();
        players.forEach(Participant::endTurn);
        List<PlayerResultDTO> totalPlayerResult = calculateTotalPlayerResult();
        DealerResultDTO dealerResult = calculateDealerResult(totalPlayerResult);
        return new TotalResultDTO(totalPlayerResult, dealerResult);
    }

    private void validateNames(String[] names) {
        validateLength(names);
        validateDuplication(names);
    }

    private void validateLength(String[] names) {
        if (names.length > MAX_NUMBER_OF_PLAYER) {
            throw new IllegalArgumentException(TOO_MANY_PLAYERS);
        }
    }

    private void validateDuplication(String[] names) {
        Set<String> set = new HashSet<>(Arrays.asList(names));
        if (set.size() != names.length) {
            throw new IllegalArgumentException(NAME_DUPLICATED);
        }
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
