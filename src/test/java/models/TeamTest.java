package models;

import org.junit.Test;
import static org.junit.Assert.*;

public class TeamTest {

    @Test
    public void Team_InstantiatesNewInstanceCorrectly_true() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red", "Jon Jonson");
        assertEquals(true, testTeam instanceof Team);
    }

    @Test
    public void getName_CorrectlyGetsTeamName() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red", "Jon Jonson");
        assertEquals("Blue", testTeam.getTeamName());
    }

    @Test
    public void getDescription_CorrectlyGetsDescription() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red", "Jon Jonson");
        assertEquals("They sure aren't Red", testTeam.getDescription());
    }

    @Test
    public void getMember_CorrectlyGetsMember() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red", "Jon Jonson");
        assertEquals("Jon Jonson", testTeam.getMember());
    }

    @Test
    public void getId_CorrectlyGetsId() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red", "Jon Jonson");
        assertEquals(1, testTeam.getId());
    }

    @Test
    public void getAll_CorrectlyGetsAllTeams() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red", "Jon Jonson");
        Team team = new Team("Red", "They sure aren't Blue", "Don Donson");
        assertTrue(Team.getAll().contains(testTeam));
        assertTrue(Team.getAll().contains(team));
    }

    @Test
    public void findById_FindsCorrectJobById() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red", "Jon Jonson");
        Team team = new Team("Red", "They sure aren't Blue", "Don Donson");
        assertEquals(1, Team.findById(testTeam.getId()).getId());
        assertEquals(2, Team.findById(team.getId()).getId());
    }
}
