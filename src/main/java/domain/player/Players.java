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

    public void giveCardToDealer(Card card) {
        dealer.addCard(card);
    }

    public boolean shouldDealerGetCard() {
        return dealer.getTotalScore() <= 16;
    }

    public void battleAll(GameResult gameResult) {
        participants.forEach(participant -> dealer.battle(participant, gameResult));
    }

    protected List<Participant> getParticipants() {
        return participants;
    }

    public List<String> getAllPlayerNames() {
        List<Player> players = new ArrayList<>();
        players.add(dealer);
        players.addAll(participants);

        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public Player findByName(String name) {
        return participants.stream()
                .filter(participant -> participant.isNameEqualTo(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 참가자 이름입니다."));
    }

    public void giveCardByName(String name, Card card) {
        Player player = findByName(name);
        player.addCard(card);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void addCardByName(String name, Card card) {
        findByName(name).addCard(card);
    }
}
