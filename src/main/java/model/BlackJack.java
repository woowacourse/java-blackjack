package model;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import model.card.CardSize;
import model.card.Cards;
import model.player.Dealer;
import model.player.Name;
import model.player.Participant;
import model.player.Participants;
import model.player.User;
import view.dto.NameDto;
import view.dto.UserCardDto;

public class BlackJack {

    private final Participants participants;
    private final Dealer dealer;

    public BlackJack(Participants participants, Dealer dealer) {
        validateParticipantIsNotNull(participants);
        validateDealerIsNotNull(dealer);
        this.participants = participants;
        this.dealer = dealer;
    }

    public void validateParticipantIsNotNull(Participants participants) {
        if (participants == null) {
            throw new IllegalArgumentException("참가자 없으면 게임이 진행되지 않습니다.");
        }
    }

    public void validateDealerIsNotNull(Dealer dealer) {
        if (dealer == null) {
            throw new IllegalArgumentException("딜러가 없으면 게임이 진행되지 않습니다.");
        }
    }

    public void decideParticipantsPlay(Function<NameDto, Boolean> isMoreCard, Consumer<UserCardDto> callback) {
        for (Participant participant : participants.getParticipants()) {
            decideParticipantPlay(participant, isMoreCard, callback);
        }
    }

    private void decideParticipantPlay(Participant participant,
                                       Function<NameDto, Boolean> isMoreCard, Consumer<UserCardDto> callback) {
        while (participant.isNotBust() && isMoreCard.apply(new NameDto(participant.getName()))) {//todo dto 생성?
            offerCardToParticipant(participant, CardSize.ONE);
            callback.accept(new UserCardDto(participant.getName(), participant.getCards()));
        }
    }

    public void decideDealerPlay(Runnable runnable) {
        while (dealer.isNotBust()) {
            runnable.run();
            offerCardToDealer(CardSize.ONE);
        }
    }

    private void offerCardToParticipant(Participant participant, CardSize size) {
        if (participants.isExistParticipant(participant)) {
            dealer.offerCardToParticipant(participant, size);
            return;
        }
        throw new IllegalArgumentException("참가자(" + participant.getName() + ")가 존재하지 않습니다.");
    }

    public void offerCardToDealer(CardSize size) {
        dealer.addCards(dealer.takeCardFromDeck(size));
    }

    public Map<Participant, Double> matchParticipantProfit() {
        List<Participant> sumPlayers = participants.getParticipants();
        return sumPlayers.stream()
                .collect(toMap(
                        participant -> participant,
                        participant -> participant.calculateProfit(dealer)
                ));
    }

    public Double getDealerProfit() {
        return participants.sumAllParticipantProfit(dealer) * -1.0;
    }

    public Map<Participant, Double> getParticipantProfits() {
        return participants.calculateParticipantProfit(dealer);
    }

    public Map<Name, Cards> mapToUsersNameAndCards() {
        return participants.getParticipants().stream()
                .collect(toMap(
                        User::getName,
                        User::getCards
                ));
    }

    public List<Name> findParticipantsName() {
        return participants.findParticipantsName();
    }

    public Map<Name, Cards> mapToDealerNameAndCards() {
        return Map.of(dealer.getName(), dealer.getCards());
    }
}
