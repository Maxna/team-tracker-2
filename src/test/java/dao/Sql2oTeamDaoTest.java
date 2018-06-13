package dao;

import models.Team;
import models.Member;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oTeamDaoTest {
    private Sql2oTeamDao teamDao;
    private Sql2oMemberDao memberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oTeamDao(sql2o);
        memberDao = new Sql2oMemberDao(sql2o);
        conn = sql2o.open();
    }

    @Test
    public void addingTeamSetsId() throws Exception {
        Team name = new Team ("epicodus", "school");
        int originalTeamId = name.getId();
        teamDao.add(name);
        assertNotEquals(originalTeamId, name.getId());
    }

    @Test
    public void existingTeamsFoundById() throws Exception{
        Team name = new Team("epicodus", "school");
        int originalTeamId = name.getId();
        teamDao.add(name);
        assertNotEquals(originalTeamId, name.getId());
    }

    @Test
    public void getAllCorrectlyGetsAll() throws Exception{
        Team name = new Team("epicodus", "school");
        teamDao.add(name);
        Team name2 = new Team("students", "school");
        teamDao.add(name2);
        assertEquals(2, teamDao.getAll().size());
    }

    @Test
    public void getAllReturnsNothingIfNoTasks() throws Exception{
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void updateWorksCorrectly() throws Exception{
        Team name = new Team("epicodus", "school");
        teamDao.add(name);
        teamDao.update(name.getId(), "students");
        Team updatedName = teamDao.findById(name.getId());
        assertEquals("students", updatedName.getName());
    }

    @Test
    public void deleteWorksCorrectly() throws Exception{
        Team name = new Team("epicodus", "school");
        teamDao.add(name);
        teamDao.deleteById(name.getId());
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void clearAllTeams() throws Exception{
        Team name = new Team("epicodus", "school");
        Team name1 = new Team("students", "school");
        teamDao.add(name);
        teamDao.add(name1);
        teamDao.clearAllTeams();
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void getAllMembersByTeamWorks() throws Exception{
        Team name = new Team("epicodus", "school");
        teamDao.add(name);
        int teamId = name.getId();
        Member name1 = new Member("mike", 1);
        Member name2 = new Member("jim", 1);
        Member name3 = new Member("ross", 1);
        memberDao.add(name1);
        memberDao.add(name2);
        assertEquals(2, teamDao.getAllMembersByTeam(teamId).size());
        assertTrue(teamDao.getAllMembersByTeam(teamId).contains(name1));
        assertTrue(teamDao.getAllMembersByTeam(teamId).contains(name2));
        assertFalse(teamDao.getAllMembersByTeam(teamId).contains(name3));
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
}