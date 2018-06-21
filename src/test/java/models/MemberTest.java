package models;


import org.junit.Test;
import static org.junit.Assert.*;

public class MemberTest {

    @Test
    public void member_CorrectlyInstantiatesMember() throws Exception {
        Member testMember = new Member("jeff", 1);
        assertTrue(testMember instanceof Member);
    }

    @Test
    public void setName_CorrectlySetsMemberName() throws Exception{
        Member testMember = new Member("jeff", 1);
        testMember.setName("Barry");
        assertEquals("Barry", testMember.getName());
    }

    @Test
    public void getName_CorrectlyGetsMemberName() throws Exception{
        Member testMember = new Member("jeff", 1);
        assertEquals("jeff", testMember.getName());
    }

    @Test
    public void setTeamId_CorrectlySetsMemberTeamId() throws Exception{
        Member testMember = new Member("jeff", 1);
        testMember.setTeamId(4);
        assertEquals(4, testMember.getTeamId());
    }

    @Test
    public void getTeamId_CorrectlyGetsMemberTeamId() throws Exception{
        Member testMember = new Member("jeff", 1);
        assertNotEquals(4, testMember.getTeamId());
    }

    @Test
    public void setId_CorrectlySetsMemberId() throws Exception{
        Member testMember = new Member("jeff", 1);
        testMember.setId(2);
        assertEquals(2, testMember.getId());
    }

    @Test
    public void getId_CorrectlyGetsMemberId() throws Exception{
        Member testMember = new Member("jeff", 1);
        testMember.setId(1);
        assertEquals(1, testMember.getId());
    }
}
