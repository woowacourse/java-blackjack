package view;

public enum Command {
    YES("y"),
    NO("n"),
    ;

    private String command;

    Command(String command) {
        this.command = command;
    }

    public boolean compareTo(String command) {
        return this.command.equals(command);
    }
}
