package models;

import org.junit.Test;
import static org.junit.Assert.*;

public class TeamTest {

    @Test
    public void Team_InstantiatesNewInstanceCorrectly_true() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red");
        assertTrue(testTeam instanceof Team);
    }

    @Test
    public void getName_CorrectlyGetsTeamName() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red");
        assertEquals("Blue", testTeam.getTeamName());
    }

    @Test
    public void getDescription_CorrectlyGetsDescription() throws Exception {
        Team testTeam = new Team("Blue", "They sure aren't Red");
        assertEquals("They sure aren't Red", testTeam.getDescription());
    }

//    @Test
//    public void getMembers_CorrectlyGetsTeamMembers() throws Exception {
//        Team testTeam = new Team("Blue", "They sure aren't Red");
//        Member members = new Member("jeff");
//        assertEquals("jeff", testTeam.getTeamMember());
//    }

//    @Test
//    public void getMembers_CorrectlyGetsTeamMembersSize() throws Exception {
//        Team testTeam = new Team("Blue", "They sure aren't Red");
//        Member member = new Member("jeff");
//        testTeam.addMembers(member);
//        assertEquals(1, testTeam.getMembers().size());
//    }
//
//    @Test
//    public void getId_CorrectlyGetsId() throws Exception {
//        Team testTeam = new Team("Blue", "They sure aren't Red");
//        assertEquals(1, testTeam.getId());
//    }
//
//    @Test
//    public void getAll_CorrectlyGetsAllTeams() throws Exception {
//        Team testTeam = new Team("Blue", "They sure aren't Red");
//        Team team = new Team("Red", "They sure aren't Blue");
//        assertTrue(Team.getTeams().contains(testTeam));
//        assertTrue(Team.getTeams().contains(team));
//    }
//
//    @Test
//    public void findById_FindsCorrectTeamById() throws Exception {
//        Team testTeam = new Team("Blue", "They sure aren't Red");
//        Team team = new Team("Red", "They sure aren't Blue");
//        assertEquals(1, Team.findById(testTeam.getId()).getId());
//        assertEquals(2, Team.findById(team.getId()).getId());
//    }
//
//    @Test
//    public void updateTeam_CorrectlyChangesTeamName() throws Exception {
//        Team testTeam = new Team("Blue", "They sure aren't Red");
//        int oldId = testTeam.getId();
//        String oldDescription = testTeam.getDescription();
//        String oldTeamName = testTeam.getTeamName();
////        String oldMember = testTeam.getMembers();
//
//        testTeam.updateTeam("Green");
//
//        assertEquals(oldId, testTeam.getId());
//        assertEquals(oldDescription, testTeam.getDescription());
//        assertNotEquals(oldTeamName, testTeam.getTeamName());
////        assertEquals(oldMember, testTeam.getMembers());
//    }

}
