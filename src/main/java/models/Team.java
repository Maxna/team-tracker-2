package models;

import java.util.Objects;

public class Team {
    private String team;
    private String description;
    private int id;


    public Team(String team, String description) {
        this.team = team;
        this.description = description;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
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
        Team team1 = (Team) o;
        return id == team1.id &&
                Objects.equals(team, team1.team) &&
                Objects.equals(description, team1.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(team, description, id);
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

