package information;

public class MsgHead {
    private String nickname;
    private String date;

    public MsgHead(String nickname, String date) {
        this.nickname = nickname;
        this.date = date;
    }

    public String toString() {
        return nickname + " " + date;
    }
}
