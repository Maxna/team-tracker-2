package dao;

import models.Team;
import java.util.List;

public interface TeamDao {

    // LIST
    List<Team> getAll();

    // CREATE
    void add(Team team);

    // READ
    Team findByID(int id);

    // UPDATE
    void update(int id, String team);

    // DELETE
    void deleteById(int id);
    void clearAllTeams();
}
