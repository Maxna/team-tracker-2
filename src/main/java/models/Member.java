package models;

import java.util.Objects;

public class Member {

    private String teamMember;
    private int id;

    public Member (String teamMember) {
        this.teamMember = teamMember;
    }

    public void setTeamMember(String teamMember) {
        this.teamMember = teamMember;
    }

    public String getTeamMember() {
        return teamMember;
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
        Member member = (Member) o;
        return id == member.id &&
                Objects.equals(teamMember, member.teamMember);
    }

    @Override
    public int hashCode() {

        return Objects.hash(teamMember, id);
    }
}
