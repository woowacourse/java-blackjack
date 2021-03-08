package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Participants {

    private final List<Participant> participants;

    public Participants(final List<Player> players, final Dealer dealer) {
        validateParticipants(players, dealer);
        participants = groupingByParticipants(players, dealer);
    }

    private void validateParticipants(final List<Player> players, final Dealer dealer) {
        if (isPlayersNullAndEmpty(players) || Objects.isNull(dealer)) {
            throw new IllegalArgumentException("참가자 또는 딜러가 존재하지 않습니다.");
        }
    }

    private boolean isPlayersNullAndEmpty(final List<Player> players) {
        return Objects.isNull(players) || players.isEmpty();
    }

    private static List<Participant> groupingByParticipants(final List<Player> playes, final Dealer dealer) {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(playes);
        return participants;
    }

    public Dealer extractDealer() {
        return (Dealer) participants.stream()
                .filter(participant -> participant.getClass().equals(Dealer.class))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Dealer가 존재하지 않습니다!"));
    }

    public List<Player> extractPlayers() {
        return participants.stream()
                .filter(participant -> participant instanceof Player)
                .map(Player.class::cast)
                .collect(Collectors.toList());
    }

    public void dealCardsAllParticipants(Deck deck) {
        for (Participant participant : participants) {
            List<Card> cards = deck.handOutInitCards();
            giveInitialCards(participant, cards);
        }
    }

    private void giveInitialCards(Participant participant, List<Card> cards) {
        for (Card card : cards) {
            participant.receiveCard(card);
        }
    }

    public List<Participant> getParticipants() {
        return new ArrayList<>(participants);
    }
}
