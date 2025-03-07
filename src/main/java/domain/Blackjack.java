package domain;

import domain.dto.DealerResult;
import domain.dto.NameAndCards;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Blackjack {
    private final Players players;
    private final Deck deck;

    public Blackjack(Players players, Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    // 초기 카드 분배
    public void distributeInitialCards() {
        players.distributeInitialCards(deck);
    }

    public NameAndCards openDealerCards() {
        Player dealer = getDealer();
        return new NameAndCards(dealer.getName(), dealer.openInitialCards());
    }

    public List<NameAndCards> openParticipantsCards() {
        return getParticipants().stream()
                .map(participant -> new NameAndCards(participant.getName(), participant.openInitialCards()))
                .toList();
    }

    // 추가 카드 분배
    public NameAndCards addCardToCurrentParticipant(String name) {
        Player participant = players.getPlayerByName(name);
        participant.drawOneCard(deck);
        return new NameAndCards(
                participant.getName(),
                participant.getCards().getCards()
        );
    }

    public boolean addCardToDealerIfLowScore() {
        return getDealer().drawOneCardIfLowScore(deck);
    }

    public NameAndCards getNameAndCardsByName(String name) {
        Player player = players.getPlayerByName(name);
        return new NameAndCards(
                player.getName(),
                player.getCards().getCards()
        );
    }

    public Map<String, Integer> getNameAndSumOfAllPlayers() {
        return players.mapToNameAndSum();
    }

    public NameAndCards getDealerNameAndCards() {
        Player dealer = getDealer();
        return new NameAndCards(dealer.getName(), dealer.getCards().getCards());
    }

    public List<NameAndCards> getParticipantsNameAndCards() {
        return getParticipants().stream()
                .map(participant -> new NameAndCards(participant.getName(), participant.getCards().getCards()))
                .toList();
    }

    public List<String> getParticipantNames() {
        return getParticipants().stream()
                .map(Player::getName)
                .toList();
    }

    private Dealer getDealer() {
        return players.getDealer();
    }

    private List<Participant> getParticipants() {
        return players.getParticipants()
                .stream()
                .map(player -> (Participant) player)
                .toList();
    }

    public DealerResult computeDealerMatchResult() {
        Map<String, MatchResult> participantNameAndMatchResult = computeParticipantsMatchResult();
        Map<MatchResult, Integer> matchResultCount = new LinkedHashMap<>();
        participantNameAndMatchResult.forEach((key, value) -> matchResultCount.put(MatchResult.inverse(value),
                matchResultCount.getOrDefault(MatchResult.inverse(value), 0) + 1));

        return new DealerResult(getDealer().getName(), matchResultCount);
    }

    public Map<String, MatchResult> computeParticipantsMatchResult() {
        Map<String, MatchResult> participantNameAndMatchResult = new LinkedHashMap<>();
        Dealer dealer = getDealer();
        List<Participant> participants = getParticipants();

        for (Participant participant: participants) {
            MatchResult matchResult = calculatePariticipantMatchResult(dealer, participant);
            participantNameAndMatchResult.put(participant.getName(), matchResult);
        }
        return participantNameAndMatchResult;
    }

    private MatchResult calculatePariticipantMatchResult(Dealer dealer, Participant participant) {
        if (participant.getCards().isBurst() && dealer.getCards().isBurst() || participant.getCards().isBurst()) {
            return MatchResult.LOSE;
        }
        if (dealer.getCards().isBurst()) {
            return MatchResult.WIN;
        }
        return MatchResult.compareBySum(participant.getCards().computeOptimalSum(), dealer.getCards().computeOptimalSum());
    }
}
