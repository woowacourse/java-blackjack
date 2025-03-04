package domain;

import java.util.HashMap;
import java.util.Map;

public class ParticipantRepository {

    private static final ParticipantRepository INSTANCE = new ParticipantRepository();

    private final Map<String, Participant> repository = new HashMap<>();

    {
        repository.put("딜러", new Participant("딜러", Cards.createEmpty()));
    }

    private ParticipantRepository() {
    }

    public static ParticipantRepository getInstance() {
        return INSTANCE;
    }

    public Participant getByName(String name) {
        return repository.get(name);
    }
}
