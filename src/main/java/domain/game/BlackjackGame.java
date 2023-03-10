package domain.game;

import domain.card.CardGenerator;
import domain.card.Deck;
import domain.player.Dealer;
import domain.player.Participant;
import domain.player.Player;
import util.HitOrStay;
import util.Notice;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;

public final class BlackjackGame {

    private static final int DEALER_INDEX = 0;
    private static final int INIT_SCORE = 0;

    private final List<Player> players;
    private final Deck deck;

    public BlackjackGame(final List<Player> players, final Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public static BlackjackGame from(final List<String> participantNames, final CardGenerator cardGenerator) {
        validateDuplicate(participantNames);

        final List<Player> players = new LinkedList<>();

        players.add(Dealer.create(0));
        players.addAll(createParticipants(participantNames));


        return new BlackjackGame(players, Deck.from(cardGenerator));
    }

    public void drawCards() {
        this.players.forEach(player -> {
            player.takeCard(this.deck.dealCard());
            player.takeCard(this.deck.dealCard());
        });
    }

    public void playParticipantsTurn(HitOrStay hitOrStay, Notice<Participant> notice) {
        this.getParticipants().forEach(participant -> playParticipantTurn(participant, hitOrStay, notice));
    }

    public void playDealerTurn(Notice<Boolean> notice) {
        final Dealer dealer = getDealer();

        while (dealer.canHit()) {
            notice.print(dealer.canHit());
            dealer.takeCard(this.deck.dealCard());
        }
        notice.print(dealer.canHit());
    }

    public GameResult judgeResult() {
        return GameResult.of(getDealer(), getParticipants());
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
                .map(name -> Participant.of(name, INIT_SCORE))
                .collect(toList());
    }

    private void playParticipantTurn(final Participant participant, final HitOrStay hitOrStay, final Notice<Participant> notice) {
        while (participant.isHit(hitOrStay.isHit(participant))) {
            participant.takeCard(deck.dealCard());
            notice.print(participant);
        }
    }

    public List<Participant> getParticipants() {
        final String dealerName = getDealer().getName();

        return players.stream()
                .dropWhile(player -> player.getName().equals(dealerName))
                .map(player -> (Participant) player)
                .collect(toUnmodifiableList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private Dealer getDealer() {
        return (Dealer) players.get(DEALER_INDEX);
    }
}
