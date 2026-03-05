package service;

import domain.Participants;
import java.util.List;

public class BlackjackService {
    private Participants participants;

    public void saveParticipants(List<String> parsedParticipantsName) {
        participants = new Participants(parsedParticipantsName);
    }
}
