package models;

public class Member {
    private String teamMember;

    public Member (String teamMember) {
        this.teamMember = teamMember;
    }

    public String getNewMembers(){
        return teamMember;
    }
}
