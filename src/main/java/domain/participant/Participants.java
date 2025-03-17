package domain.participant;

import domain.card.Deck;
import domain.card.TrumpCard;
import domain.game.GameResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Participants {
    private static final int INIT_CARD_COUNT = 2;
    private static final String PLAYER_NOT_EXIST = "존재하지 않는 플레이어입니다.";
    private static final String DUPLICATE_DEALER_NAME = "플레이어의 이름은 딜러일 수 없습니다.";
    private static final String DUPLICATE_PLAYER_NAME = "플레이어의 이름은 중복될 수 없습니다.";
    private static final String PLAYER_BET_MISMATCH = "플레이어의 이름수와 배팅 수가 일치하지 않습니다.";

    private final List<Player> players;
    private final Dealer dealer;

    public Participants(List<ParticipantName> participantNames, Map<ParticipantName, Bet> playerBets, Deck deck) {
        this.dealer = new Dealer(drawStartCards(deck));
        validatePlayerNames(participantNames);
        validateBetCount(participantNames, playerBets);
        players = createPlayers(participantNames, playerBets, deck);
    }

    private static void validateBetCount(List<ParticipantName> names, Map<ParticipantName, Bet> participantBets) {
        if (names.size() != participantBets.size()) {
            throw new IllegalStateException(PLAYER_BET_MISMATCH);
        }
    }

    private List<TrumpCard> drawInitCard(Deck deck) {
        List<TrumpCard> initCards = new ArrayList<>();
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            initCards.add(deck.drawCard());
        }
        return initCards;
    }

    private List<Player> createPlayers(List<ParticipantName> names, Map<ParticipantName, Bet> playerBets, Deck deck) {
        List<Player> players = new ArrayList<>();
        IntStream.range(0, names.size())
                .forEach(index -> {
                    ParticipantName name = names.get(index);
                    players.add(new Player(name, playerBets.get(name), drawInitCard(deck)));
                });
        return players;
    }

    private void validatePlayerNames(List<ParticipantName> names) {
        validateDuplicateDealerName(names);
        validateUniqueName(names);
    }

    private void validateUniqueName(List<ParticipantName> names) {
        if (names.size() != new HashSet<>(names).size()) {
            throw new IllegalArgumentException(DUPLICATE_PLAYER_NAME);
        }
    }

    private void validateDuplicateDealerName(List<ParticipantName> names) {
        ParticipantName dealerName = dealer.name();
        boolean isDuplicateName = names.stream()
                .anyMatch(name -> name.equals(dealerName));
        if (isDuplicateName) {
            throw new IllegalArgumentException(DUPLICATE_DEALER_NAME);
        }
    }

    private Player findPlayer(ParticipantName name) {
        return players.stream()
                .filter(player -> player.isNameMatch(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(PLAYER_NOT_EXIST));
    }

    public List<GameResult> calculatePlayerResults() {
        List<GameResult> gameResults = new ArrayList<>();
        for (ParticipantName name : getPlayerNames()) {
            List<TrumpCard> playerCards = playerCards(name);
            Score playerScore = calculateCardSum(name);
            gameResults.add(new GameResult(name, playerCards, playerScore));
        }
        return Collections.unmodifiableList(gameResults);
    }

    public List<ParticipantName> getPlayerNames() {
        return players.stream()
                .map(Player::name)
                .toList();
    }

    public GameResult dealerResult() {
        Score dealerScore = dealer.calculateSum();
        List<TrumpCard> dealerCards = dealer.cards();
        return new GameResult(dealerName(), dealerCards, dealerScore);
    }

    public List<TrumpCard> playerCards(ParticipantName name) {
        Player player = findPlayer(name);
        return player.cards();
    }

    public List<TrumpCard> dealerCards() {
        return dealer.cards();
    }

    public Score calculateCardSum(ParticipantName name) {
        Player player = findPlayer(name);
        return player.calculateCardSum();
    }

    public ParticipantName dealerName() {
        return dealer.name();
    }

    public boolean isDealerDrawable() {
        return dealer.isDrawable();
    }

    public void addCard(ParticipantName name, TrumpCard trumpCard) {
        Player player = findPlayer(name);
        player.addCard(trumpCard);
    }

    public void dealDealerCard(TrumpCard trumpCard) {
        dealer.addCard(trumpCard);
    }

    public int getPlayerProfit(ParticipantName name) {
        Player player = findPlayer(name);
        return player.getProfitFromOpponents(dealer.handState());
    }

    public void stayPlayer(ParticipantName name) {
        findPlayer(name).stay();
    }

    public boolean isFinished(ParticipantName name) {
        return findPlayer(name).isFinished();
    }
}
