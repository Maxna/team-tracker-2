package dao;

import models.Team;
import models.Member;
import org.sql2o.*;
import java.util.List;

public class Sql2oTeamDao implements TeamDao {
    private final Sql2o sql2o;

    public Sql2oTeamDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Team name) {
        String sql = "INSERT INTO teams (name) VALUES (:name)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(name)
                    .executeUpdate()
                    .getKey();
            name.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Team> getAll() {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM teams")
                    .executeAndFetch(Team.class);
        }
    }

    @Override
    public Team findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM teams WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Team.class);
        }
    }

    @Override
    public void update(int id, String newName){
        String sql = "UPDATE teams SET team = :team WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", newName)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from teams WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllTeams() {
        String sql = "DELETE from teams";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Member> getAllMembersByTeam(int teamId) {
    try(Connection con = sql2o.open()){
        return con.createQuery("SELECT * FROM members WHERE teamId = :teamId")
                .addParameter("teamId", teamId)
                .executeAndFetch(Member.class);
        }
    }
        
}
