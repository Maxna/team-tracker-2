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
        String connectionstring = "jdbc:h2:~/team-tracker.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionstring, "", "");
        Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);
        Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/about", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "about-us.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String teamName = req.queryParams("teamName");
            String description = req.queryParams("description");
            Team newTeam = new Team(teamName, description);
            teamDao.add(newTeam);
            int newTeamId = newTeam.getId();
            String newMember = req.queryParams("aMember");
            Member aMember = new Member(newMember, newTeamId);
            memberDao.add(aMember);
            model.put("teams", newTeam);
            res.redirect("/");
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
//            Team newTeam = teamDao.findById(idOfTeamToFind);
//            teamDao.add(newTeam);
            memberDao.add(aMember);
            response.redirect("/teams/" + idOfTeamToFind);
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
            String newTeamName = req.queryParams("teamName");
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            Team editTeam = teamDao.findById(idOfTeamToEdit);
            teamDao.update(idOfTeamToEdit, newTeamName);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

//        get("/teams/:teamId/members/:memberId", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfMemberToFind = Integer.parseInt(req.params("memberId"));
//            Member foundMember = memberDao.findById(idOfMemberToFind);
//            int idOfTeamToFind = Integer.parseInt(req.params("teamId"));
//            Team foundTeam = teamDao.findById(idOfTeamToFind);
//            model.put("member", foundMember);
//            model.put("team", foundTeam);
//            return new ModelAndView(model, "edit-member.hbs");
//        }, new HandlebarsTemplateEngine());

    }
}
