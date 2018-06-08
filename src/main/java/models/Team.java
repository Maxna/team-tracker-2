package models;

import java.util.Objects;

public class Team {
    private String teamName;
    private String description;
    private int id;


    public Team(String teamName, String description) {
        this.teamName = teamName;
        this.description = description;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id &&
                Objects.equals(teamName, team.teamName) &&
                Objects.equals(description, team.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(teamName, description, id);
    }



    //    public void addMembers(Member member) {
//
//            members.add(member);
//    }
//
//    public static Team findById(int id) {
//        return teamList.get(id-1);
//    }
//
//    public void updateTeam(String teamName) {
//        this.teamName = teamName;
//    }
//
//    public static void clearAllTeams(){
//        teamList.clear();
//    }
}

