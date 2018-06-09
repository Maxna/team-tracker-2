package dao;

import models.Team;
import models.Member;

import java.util.List;

public interface TeamDao {

    // LIST
    List<Team> getAll();
    List<Member> getAllMembersByTeam(int teamId);

    // CREATE
    void add(Team team);

    // READ
    Team findById(int id);

    // UPDATE
    void update(int id, String team);

    // DELETE
    void deleteById(int id);
    void clearAllTeams();
}
