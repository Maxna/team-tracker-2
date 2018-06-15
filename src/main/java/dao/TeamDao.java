package dao;

import models.Team;
import models.Member;

import java.util.List;

public interface TeamDao {

    // LIST
    List<Team> getAll();
    List<Member> getAllMembersByTeam(int teamId);

    // CREATE
    void add(Team name);

    // READ
    Team findById(int id);

    // UPDATE
    void update(int id, String name);

    // DELETE
    void deleteById(int id);
    void clearAllTeams();
}
