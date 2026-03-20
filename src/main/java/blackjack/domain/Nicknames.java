package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

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
        return value.stream()
            .filter(nickname -> nickname.hasNickname(givenNickname))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("일치하는 닉네임이 존재하지 않습니다."));
    }

    private void validate(List<Nickname> nicknames) {
        if (nicknames == null) {
            throw new IllegalArgumentException("닉네임 목록이 존재하지 않습니다.");
        }
        if (nicknames.isEmpty()) {
            throw new IllegalArgumentException("플레이어 이름은 1명 이상 입력해야 합니다.");
        }
        long uniqueCount = nicknames.stream().distinct().count();
        if (nicknames.size() != uniqueCount) {
            throw new IllegalArgumentException("중복된 닉네임이 존재합니다.");
        }
    }
}
