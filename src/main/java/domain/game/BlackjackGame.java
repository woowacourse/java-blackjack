package domain.game;

import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.Deck;
import domain.player.Dealer;
import domain.player.Participant;
import domain.player.Player;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;

public final class BlackjackGame {

    private static final int DEALER_INDEX = 0;

    private final List<Player> players;
    private final Deck deck;

    public BlackjackGame(final List<Player> players, final Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public static BlackjackGame from(final List<String> participantNames, final CardGenerator cardGenerator) {
        validateDuplicate(participantNames);

        final List<Player> players = new LinkedList<>();

        players.add(Dealer.create());
        players.addAll(createParticipants(participantNames));


        return new BlackjackGame(players, Deck.from(cardGenerator));
    }

    public void drawCard() {
        this.players.forEach(player -> {
            player.takeCard(this.deck.dealCard());
            player.takeCard(this.deck.dealCard());
        });
    }

    public void playParticipantsTurn(Predicate<Participant> isHit, Consumer<Participant> consumer) {
        this.getParticipants().forEach(participant -> playParticipantTurn(participant, isHit, consumer));
    }

    public void playDealerTurn(Consumer<Boolean> consumer) {
        final Dealer dealer = getDealer();
        while (dealer.isHit()) {
            consumer.accept(dealer.isHit());
            dealer.takeCard(this.deck.dealCard());
        }
        consumer.accept(dealer.isHit());
    }

    private static void validateDuplicate(final List<String> participantNames) {
        if (isDuplicate(participantNames)) {
            throw new IllegalArgumentException("중복되지 않은 이름만 입력해주세요");
        }
    }

    private static boolean isDuplicate(final List<String> participantNames) {
        final int uniqueNameCount = new HashSet<>(participantNames).size();
        return uniqueNameCount < participantNames.size();
    }

    private static List<Participant> createParticipants(final List<String> participantNames) {
        return participantNames.stream()
                .map(Participant::from)
                .collect(toList());
    }

    private void playParticipantTurn(final Participant participant, final Predicate<Participant> hitOrStay, final Consumer<Participant> print) {
        while (participant.isKeepGaming(hitOrStay.test(participant))) {
            participant.takeCard(deck.dealCard());
            print.accept(participant);
        }
    }

    public Card getDealerCard() {
        return getDealer().getFirstCard();
    }

    public List<Participant> getParticipants() {
        final String dealerName = players.get(DEALER_INDEX).getName();

        return players.stream()
                .dropWhile(player -> player.getName().equals(dealerName))
                .map(player -> (Participant) player)
                .collect(toUnmodifiableList());
    }

    private Dealer getDealer() {
        return (Dealer) players.get(DEALER_INDEX);
    }

}
