import models.Team;
import models.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.sql2o.Sql2o;
import dao.*;
import dao.Sql2oTeamDao;
import dao.Sql2oMemberDao;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.Spark.*;
import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/team-tracker.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);
        Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            List<Member> members = memberDao.getAll();
            model.put("members", members);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/about", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "about-us.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String teamName = req.queryParams("teamName");
            String description = req.queryParams("description");
            Team newTeam = new Team(teamName, description);
            teamDao.add(newTeam);
            int newTeamId = newTeam.getId();
            String newName = req.queryParams("name");
            Member newMember = new Member(newName, newTeamId);
            memberDao.add(newMember);
            model.put("teams", newTeam);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            teamDao.clearAllTeams();
            res.redirect("/");
            halt();
            return null;
        }, new HandlebarsTemplateEngine());

        get("/teams/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToFind = Integer.parseInt(req.params("id"));
            Team foundTeam = teamDao.findById(idOfTeamToFind);
            model.put("team", foundTeam);
            List<Member> allMembersForATeam = teamDao.getAllMembersByTeam(idOfTeamToFind);
            model.put("members", allMembersForATeam);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newMember = request.queryParams("name");
            int idOfTeamToFind = Integer.parseInt(request.params("id"));
            Member aMember = new Member(newMember, idOfTeamToFind);
            memberDao.add(aMember);
            response.redirect("/teams/" + idOfTeamToFind);
            halt();
            return null;
        }, new HandlebarsTemplateEngine());

        get("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            Team editTeam = teamDao.findById(idOfTeamToEdit);
            model.put("editTeam", editTeam);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            String newTeamName = req.queryParams("teamName");
            String newTeamDescription = req.queryParams("description");
            teamDao.update(idOfTeamToEdit, newTeamName, newTeamDescription);
            res.redirect("/");
            halt();
            return null;
        }, new HandlebarsTemplateEngine());

        get("/teams/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToDelete = Integer.parseInt(req.params("id")) ;
            teamDao.deleteById(idOfTeamToDelete);
            res.redirect("/");
            halt();
            return null;
        }, new HandlebarsTemplateEngine());

//        post("/teams/:id/members/:id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfMemberToFind = Integer.parseInt(req.params("id"));
//            Member foundMember = memberDao.findById(idOfMemberToFind);
//            int idOfTeamToFind = Integer.parseInt(req.params("id"));
//            Team foundTeam = teamDao.findById(idOfTeamToFind);
//            model.put("member", foundMember);
//            model.put("team", foundTeam);
//            return new ModelAndView(model, "edit-member.hbs");
//        }, new HandlebarsTemplateEngine());

//        get("/teams/:id/members/:teamId", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfMemberToFind = Integer.parseInt(req.params("id"));
//            Member foundMember = memberDao.findById(idOfMemberToFind);
//            int idOfTeamToFind = Integer.parseInt(req.params("teamId"));
//            Team foundTeam = teamDao.findById(idOfTeamToFind);
//            model.put("member", foundMember);
//            model.put("team", foundTeam);
//            return new ModelAndView(model, "edit-member.hbs");
//        }, new HandlebarsTemplateEngine());
//
        get("/teams/:teamId/members/:memberId/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfMemberToDelete = Integer.parseInt(req.params("memberId"));
            memberDao.deleteById(idOfMemberToDelete);
            int idOfTeamToFind = Integer.parseInt(req.params("teamId"));
            res.redirect("/teams/" + idOfTeamToFind);
            halt();
            return null;
        }, new HandlebarsTemplateEngine());

        get("/members/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfMemberToFind = Integer.parseInt(req.params("id"));
            Member foundMember = memberDao.findById(idOfMemberToFind);
            model.put("member", foundMember);
            return new ModelAndView(model, "edit-member.hbs");
        }, new HandlebarsTemplateEngine());

        post("/members/:id", (req, res) -> {
            String newName = req.queryParams("name");
            int idOfMemberToEdit = Integer.parseInt(req.params("id"));
            int teamIdOfMember = memberDao.findById(idOfMemberToEdit).getTeamId();
            memberDao.update(idOfMemberToEdit, newName, teamIdOfMember);
            res.redirect("/teams/" + teamIdOfMember);
            halt();
            return null;
        }, new HandlebarsTemplateEngine());

    }
}
