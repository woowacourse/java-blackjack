package model.player;

import model.card.Card;
import model.card.Cards;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Users {
    private final Participants participants;
    private final Dealer dealer;

    public Users(Participants participants, Dealer dealer) {
        validateParticipantIsNotNull(participants);
        validateDealerIsNotNull(dealer);

        this.participants = participants;
        this.dealer = dealer;
    }

    private void validateParticipantIsNotNull(Participants participants) {
        if (participants == null) {
            throw new IllegalArgumentException("참가자 없으면 게임이 진행되지 않습니다.");
        }
    }

    private void validateDealerIsNotNull(Dealer dealer) {
        if (dealer == null) {
            throw new IllegalArgumentException("딜러가 없으면 게임이 진행되지 않습니다.");
        }
    }

    public void decideParticipantsPlay(Predicate<Name> inputForMoreCard,
                                       BiConsumer<Name, Cards> printParticipantsCard, Supplier<Card> selectCard) {
        participants.offerCardToParticipants
                (inputForMoreCard, printParticipantsCard, selectCard);
    }

    public void decideDealerPlay(Runnable runnable, Supplier<Card> selectCard) {
        while (dealer.isHit()) {
            runnable.run();
            dealer.addCard(selectCard.get());
        }
    }

    public Map<Name, Cards> matchParticipantsNameAndCards() {
        return participants.matchParticipantNameAndCards();
    }

    public int findDealerRevenue() {
        return matchNameAndRevenues()
                .values()
                .stream()
                .mapToInt(money -> money)
                .sum() * -1;
    }

    public Map<Name, Integer> matchNameAndRevenues() {
        return participants.matchNameAndRevenues(dealer);
    }

    public List<Name> findParticipantsName() {
        return participants.findParticipantsName();
    }

    public Dealer dealer() {
        return dealer;
    }
}
