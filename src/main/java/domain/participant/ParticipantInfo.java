package domain.participant;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static domain.game.EarningRate.LOSE;
import static domain.participant.Participant.DEALER_NAME;

public class ParticipantInfo {
    private final Map<Participant, ParticipantMoney> participantInfo;

    private ParticipantInfo(final Map<Participant, ParticipantMoney> participantInfo) {
        this.participantInfo = participantInfo;
    }

    public static ParticipantInfo create(final Map<Participant, ParticipantMoney> participantAsset) {
        return new ParticipantInfo(participantAsset);
    }

    public Participant findDealerInfo() {
        return participantInfo.keySet()
                .stream()
                .filter(participant -> DEALER_NAME.isSame(participant.getName()))
                .findFirst()
                .orElseThrow(() -> {
                    throw new IllegalStateException("딜러 정보가 존재하지 않습니다.");
                });
    }

    public List<Participant> findPlayerInfo() {
        return participantInfo.keySet()
                .stream()
                .filter(Predicate.not(participant -> DEALER_NAME.isSame(participant.getName())))
                .collect(Collectors.toUnmodifiableList());
    }

    public void loseAllMoney(final Participant participant) {
        updateDealerMoney(participant);
        final ParticipantMoney participantMoney = participantInfo.get(participant);
        participantInfo.put(participant, LOSE.calculateMoney(participantMoney));
    }

    private void updateDealerMoney(final Participant player) {
        final Participant dealer = findDealerInfo();
        final ParticipantMoney dealerMoney = participantInfo.get(dealer);
        participantInfo.put(dealer, dealerMoney.add(participantInfo.get(player)));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ParticipantInfo that = (ParticipantInfo) o;
        return Objects.equals(participantInfo, that.participantInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participantInfo);
    }

    public Set<Participant> getParticipants() {
        return participantInfo.keySet();
    }

    public Map<Participant, ParticipantMoney> getParticipantInfo() {
        return participantInfo;
    }
}
