package srimani7.playground.chitchat;

public class ChatUser {
    private final String name;
    private String other;

    public ChatUser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
