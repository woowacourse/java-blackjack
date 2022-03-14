package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {

    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 8;

    private final List<Player> participants;
    private final Player dealer;
    private int participantPointer = 0;

    public Players(final List<Player> participants, final Player dealer) {
        validateParticipants(participants);
        this.participants = participants;
        this.dealer = dealer;
    }

    private void validateParticipants(final List<Player> participants) {
        validateSize(participants);
        validateDuplicated(participants);
    }

    private void validateSize(final List<Player> participants) {
        if (participants == null || participants.size() < MIN_SIZE || participants.size() > MAX_SIZE) {
            throw new IllegalArgumentException("[ERROR] 참가자 정보가 잘못 입력되었습니다.");
        }
    }

    private void validateDuplicated(final List<Player> participants) {
        final Set<String> names = participants.stream()
                .map(Player::getName)
                .collect(Collectors.toSet());

        if (names.size() != participants.size()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름은 중복될 수 없습니다.");
        }
    }

    public void initParticipantPointer() {
        participantPointer = 0;
    }

    public boolean competeWithPointParticipant() {
        return isDealerWin(dealer.calculateFinalScore(), pointParticipant().calculateFinalScore());
    }

    private boolean isDealerWin(int dealerScore, int participantScore) {
        return participantScore > Cards.getMaxScore() || (dealerScore <= Cards.getMaxScore() && dealerScore >= participantScore);
    }

    public boolean isDealerAcceptableCard() {
        return dealer.acceptableCard();
    }

    public void addPointParticipantCard(Card card) {
        pointParticipant().addCard(card);
    }

    public boolean isPointParticipantAcceptableCard() {
        return pointParticipant().acceptableCard();
    }

    public Player pointParticipant() {
        if (isParticipantPointerEnd()) {
            throw new RuntimeException("참가자를 불러올 수 없습니다.");
        }
        return participants.get(participantPointer);
    }

    public boolean isParticipantPointerEnd() {
        return participantPointer == participants.size();
    }

    public void moveParticipantPointer() {
        participantPointer++;
    }

    public void addDealerCard(Card card) {
        dealer.addCard(card);
    }

    public String pointParticipantName() {
        return pointParticipant().getName();
    }

    public Player getDealer() {
        return dealer;
    }

    public List<Player> getParticipants() {
        return List.copyOf(participants);
    }
}
