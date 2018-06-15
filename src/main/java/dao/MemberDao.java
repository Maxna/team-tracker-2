package dao;

import models.Member;
import java.util.List;

public interface MemberDao {
    // LIST
    List<Member> getAll();

    // CREATE
    void add(Member name);

    // READ
    Member findById(int id);

    // UPDATE
    void update(int id, String newMember, int teamId);

    // DELETE
    void deleteById(int id);
    void clearAllMembers();
}
