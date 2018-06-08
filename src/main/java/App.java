import models.Team;
import models.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/teams/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Team> teams = Team.getTeams();
            model.put("teams", teams);
            return new ModelAndView(model, "index.hbs");
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
            String newMember = req.queryParams("aMember");
            Member aMember = new Member(newMember);
            newTeam.addMembers(aMember);
            model.put("teams", newTeam);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToFind = Integer.parseInt(req.params("id"));
            Team foundTeam = Team.findById(idOfTeamToFind);
            model.put("team", foundTeam);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newMembers = request.queryParams("teamMember");
            Member newMember = new Member(newMembers);
            int idOfTeamToFind = Integer.parseInt(request.params("id"));
            Team newSquad = Team.findById(idOfTeamToFind);
            newSquad.addMembers(newMember);
            response.redirect("/teams/" + newSquad.getId());
            return null;
        }, new HandlebarsTemplateEngine());

        get("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            Team editTeam = Team.findById(idOfTeamToEdit);
            model.put("editTeam", editTeam);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newTeamName = req.queryParams("teamName");
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            Team editTeam = Team.findById(idOfTeamToEdit);
            editTeam.updateTeam(newTeamName);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
