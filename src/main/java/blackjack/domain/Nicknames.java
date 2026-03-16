package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Nicknames {

    private final List<Nickname> value;

    private Nicknames(List<Nickname> nicknames) {
        validate(nicknames);
        this.value = nicknames;
    }

    public static Nicknames from(List<String> playerNames) {
        List<Nickname> nicknames = new ArrayList<>();
        for (String playerName : playerNames) {
            Nickname validNickname = Nickname.from(playerName);
            nicknames.add(validNickname);
        }
        return new Nicknames(nicknames);
    }

    public List<String> getRawNicknames() {
        List<String> nicknames = new ArrayList<>();
        for (Nickname nickname : value) {
            nicknames.add(nickname.getValue());
        }
        return nicknames;
    }

    public Nickname findByNickname(String givenNickname) {
        for (Nickname nickname : value) {
            if (nickname.getValue().equals(givenNickname)) {
                return nickname;
            }
        }
        throw new IllegalArgumentException("일치하는 닉네임이 존재하지 않습니다.");
    }

    private void validate(List<Nickname> nicknames) {
        if (nicknames == null) {
            throw new IllegalArgumentException("닉네임 목록이 존재하지 않습니다.");
        }
        long uniqueCount = nicknames.stream().distinct().count();
        if (nicknames.size() != uniqueCount) {
            throw new IllegalArgumentException("중복된 닉네임이 존재합니다.");
        }
    }
}
