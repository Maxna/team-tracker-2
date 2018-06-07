package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName;
    private String description;
    private List<String> memberList = new ArrayList<>();
    private static List<Team> teamList = new ArrayList<>();
    private int id;


    public Team(String teamName, String description) {
        this.teamName = teamName;
        this.description = description;
        this.memberList = memberList;
        teamList.add(this);
        this.id = teamList.size();

    }


    // Start Getters

    public String getTeamName() {
        return teamName;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getMembers() {
        return memberList;
    }

    public int getId() {
        return id;
    }

    public static List<Team> getTeams() {
        return teamList;
    }

    // End Getters

    public void addMembers(String member) {
        if (!member.equals("")) {
            this.memberList.add(member);
        }
    }

    public static Team findById(int id) {
        return teamList.get(id-1);
    }

    public void updateTeam(String teamName) {
        this.teamName = teamName;
    }

    public static void clearAllTeams(){
        teamList.clear();
    }
}

