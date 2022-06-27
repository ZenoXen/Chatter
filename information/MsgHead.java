package information;

public class MsgHead {
    private final String nickname;
    private final String date;

    public MsgHead(String nickname, String date) {
        this.nickname = nickname;
        this.date = date;
    }

    public String toString() {
        return nickname + " " + date;
    }
}
