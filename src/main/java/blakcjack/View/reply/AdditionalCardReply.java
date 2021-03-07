package blakcjack.view.reply;

import java.util.Arrays;
import java.util.Objects;

public enum AdditionalCardReply {
    HIT("y"),
    STAND("n");

    String value;

    AdditionalCardReply(final String value) {
        this.value = value;
    }

    public static AdditionalCardReply from(final String value) {
        if (Objects.isNull(value)) {
            throw new InvalidReplyException();
        }
        final String lowerCaseValue = value.toLowerCase();

        return Arrays.stream(values())
                .filter(reply -> Objects.equals(reply.value, lowerCaseValue))
                .findAny()
                .orElseThrow(InvalidReplyException::new);
    }

    public String getValue() {
        return value;
    }

    public boolean isHit() {
        return equals(HIT);
    }
}
