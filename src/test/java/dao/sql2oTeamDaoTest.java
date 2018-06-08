package dao;

import models.Team;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class sql2oTeamDaoTest {
    private sql2oTeamDao teamDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oTeamDao(sql2o);
        conn = sql2o.open();
    }

    @Test
    public void addingTeamSetsId() throws Exception {
        Team team = new Team ("Epicodus");
        int originalTeamId = Team.getId();
        teamDao.add(team);
        assertNotEquals(originalTeamId, team.getId());
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
}