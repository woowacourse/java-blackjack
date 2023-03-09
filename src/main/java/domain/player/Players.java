package domain.player;

import domain.card.Card;
import domain.gameresult.GameResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final Dealer dealer;
    private final List<Participant> participants;

    public Players(Dealer dealer, List<Participant> participants) {
        this.dealer = dealer;
        this.participants = participants;
    }

    public void giveCardByName(String name, Card card) {
        Player player = findByName(name);
        player.addCard(card);
    }

    public boolean shouldDealerGetCard() {
        return dealer.getTotalScore() <= Dealer.DEALER_MIN_SCORE;
    }

    public GameResult battleAll() {
        GameResult gameResult = new GameResult();
        participants.forEach(participant -> dealer.battle(participant, gameResult));
        return gameResult;
    }

    public Player findByName(String name) {
        return getAllPlayers().stream()
                .filter(player -> player.isNameEqualTo(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 참가자 이름입니다."));
    }

    private List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(dealer);
        players.addAll(participants);
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    protected List<Participant> getParticipants() {
        return participants;
    }

    public List<String> getAllPlayerNames() {
        return getAllPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }
}
