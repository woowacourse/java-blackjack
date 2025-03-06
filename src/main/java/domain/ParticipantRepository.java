package domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParticipantRepository {

    private static final ParticipantRepository INSTANCE = new ParticipantRepository();

    private final Map<String, Participant> repository = new LinkedHashMap<>();

    {
        Participant dealer = Participant.createDealer();
        repository.put(dealer.name(), dealer);
    }

    private ParticipantRepository() {
    }

    public static ParticipantRepository getInstance() {
        return INSTANCE;
    }

    public Participant getDealer() {
        return repository.get("딜러");
    }

    public Participant getByName(String name) { // TODO : 존재하지 않을 경우 예외처리
        return repository.get(name);
    }

    public void addAll(List<Participant> participants) { // TODO : 중복 될 경우 예외처리
        participants.forEach(
                participant -> repository.put(participant.name(), participant)
        );
    }

    public List<Participant> getAll() {
        return repository.keySet().stream()
                .map(repository::get)
                .toList();
    }

    public List<Participant> getAllPlayer() {
        return getAll().stream()
                .filter(Participant::isPlayer)
                .toList();
    }

    public void initialize() {
        repository.clear();
        Participant dealer = Participant.createDealer();
        repository.put(dealer.name(), dealer);
    }
}
