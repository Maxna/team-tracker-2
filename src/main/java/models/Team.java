package models;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private String description;
    private static ArrayList<Members> memberList = new ArrayList<>();
    private int id;
    private static ArrayList<Team> teamList = new ArrayList<>();
    private String member;


    public Team(String teamName, String description) {
        this.teamName = teamName;
        this.description = description;
        memberList.add(this);
        this.id = teamList.size();
        teamList.add(this);

//        this.member = member;
//        this.id = memberList.size();

    }

    // Start Getters

    public String getTeamName() {
        return teamName;
    }

    public String getDescription() {
        return description;
    }

    public static ArrayList<Members> getMembers() {
        return memberList;
    }

    public int getId() {
        return id;
    }

    public static ArrayList<Team> getTeams() {
        return teamList;
    }

    // End Getters

    public void updateMembers(String member) {
        this.memberList = memberList;
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

