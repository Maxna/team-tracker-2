package models;

import java.util.Objects;

public class Member {
    private String member;
    private int id;

    public Member (String member) {
        this.member = member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getMember() {
        return member;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member1 = (Member) o;
        return id == member1.id &&
                Objects.equals(member, member1.member);
    }

    @Override
    public int hashCode() {

        return Objects.hash(member, id);
    }
}
