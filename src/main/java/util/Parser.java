package util;

import java.util.List;
import java.util.regex.Pattern;

public class Parser {
    public List<String> parseParticipantsName(String participantsName) {
//        validateParticipantsName(participantsName);
        return List.of(participantsName.split(","));
    }
}
