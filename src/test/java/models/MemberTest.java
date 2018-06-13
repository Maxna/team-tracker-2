package models;


import org.junit.Test;
import static org.junit.Assert.*;

public class MemberTest {

    @Test
    public void member_CorrectlyInstantiatesMember() throws Exception {
        Member name = new Member("jeff", 1);
        assertTrue(name instanceof Member);
    }

    @Test
    public void getMember_CorrectlyGetsMember() throws Exception{
        Member name = new Member("jeff", 1);
        assertEquals("jeff", name.getName());
    }

}
